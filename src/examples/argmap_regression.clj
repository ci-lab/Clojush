(ns examples.argmap-regression
  (:require [clojush]
	    [clojure.math.numeric-tower :as math]))

;; argmap_regression.clj
;; an example problem for clojush, a Push/PushGP system written in Clojure
;; Lee Spector, lspector@hampshire.edu, 2010

;; This is the same as simple_regression except passes all of its arguments 
;; in a map. This is just a demo of pushgp-map.

;;;;;;;;;;;;
;; Integer symbolic regression of x^3 - 2x^2 - x (problem 5 from the 
;; trivial geography chapter) with minimal integer instructions and an 
;; input instruction that uses the auxiliary stack.

(clojush/define-registered in 
  (fn [state] (clojush/push-item (clojush/stack-ref :auxiliary 0 state) :integer state)))

(defn run [args]
  (clojush/pushgp-map
   (-> args
       (assoc :error-function (fn [program]
				(doall
				 (for [input (range 10)]
				   (let [state (clojush/run-push program 
								 (clojush/push-item input :auxiliary 
										    (clojush/push-item input :integer 
												       (clojush/make-push-state))))
					 top-int (clojush/top-item :integer state)]
				     (if (number? top-int)
				       (math/abs (- top-int 
					       (- (* input input input) 
						  (* 2 input input) input)))
				       1000))))))
       (assoc :atom-generators (list (fn [] (rand-int 10))
				     'in
				     'integer_div
				     'integer_mult
				     'integer_add
				     'integer_sub))
       (assoc :tournament-size 3))))