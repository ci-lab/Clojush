;; gaussian_mutation_demo.clj
;; an example problem for clojush, a Push/PushGP system written in Clojure
;; Lee Spector, lspector@hampshire.edu, 2011

(ns examples.gaussian-mutation-demo
  (:require [clojush]
	    [clojure.math.numeric-tower :as math]))

;;;;;;;;;;;;
;; Floating point symbolic regression of a polynomial that uses non-integral 
;; constants, but with an ephemeral random constant generator that can
;; only produce integral values. For example, the polynomial includes 1.23
;; while the ephemeral random constant generator can only produce constants
;; like 1.0 and 2.0. While it would be possible to produce 1.23 through
;; arithmetic manipulation of integral values, it is probably easier to 
;; produce it through gaussian mutations.

(clojush/define-registered in 
  (fn [state] (clojush/push-item (clojush/stack-ref :auxiliary 0 state) :float state)))

(defn run [args]
  (clojush/pushgp-map
   (-> args
       (assoc :error-function (fn [program]
				(doall
				 (for [input (range -1.0 1.0 0.1)]
				   (let [state (clojush/run-push program 
                                      (clojush/push-item input :auxiliary 
                                        (clojush/push-item input :float
                                          (clojush/make-push-state))))
                              top-float (clojush/top-item :float state)
                              invalid-output (or (not (number? top-float))
                                               (= (:termination state) :abnormal))]
                          (if invalid-output
                            1000
                            (math/abs (- top-float
                                   (+ (* 1.23 input input)
				      0.73)))))))))
       (assoc :atom-generators (concat 
				'(float_div float_mult float_sub float_add
					    float_rot float_swap float_dup float_pop)
				(list 
				 (fn [] (* 1.0 (- (rand-int 21) 10)))
				 'in)))
       (assoc :mutation-probability 0.2)
       (assoc :crossover-probability 0.2)
       (assoc :simplification-probability 0.2)
       (assoc :reproduction-simplifications 10)
       (assoc :gaussian-mutation-probability 0.3)
       (assoc :gaussian-mutation-per-number-mutation-probability 1.0)
       (assoc :gaussian-mutation-standard-deviation 0.1)
       (assoc :error-threshold 1.0)
       (assoc :population-size 5000))))