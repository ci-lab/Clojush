;; odd.clj
;; an example problem for clojush, a Push/PushGP system written in Clojure
;; Lee Spector, lspector@hampshire.edu, 2010

(ns examples.odd
  (:require [clojush]))

;;;;;;;;;;;;
;; The "odd" problem: take a positive integer input and push a Boolean indicating
;; whether or not the input is an odd number. There are many ways to compute this
;; and PushGP sometimes finds unusual methods.

(clojush/define-registered in 
  (fn [state] (clojush/push-item (clojush/stack-ref :auxiliary 0 state) :integer state)))

(defn run [args]
  (let [argmap (-> args
		   (assoc :error-function (fn [program]
					    (doall
					     (for [input (range 10)]
					       (let [state (clojush/run-push program
									     (clojush/push-item input :auxiliary
												(clojush/push-item input :integer
														   (clojush/make-push-state))) )
						     top-bool (clojush/top-item :boolean state)]
						 (if (not (= top-bool :no-stack-item))
						   (if (= top-bool (odd? input)) 0 1)
						   1000))))))
		   (assoc :atom-generators (concat (clojush/registered-nonrandom)
						   (list (fn [] (rand-int 100))
							 'in))))]
    (clojush/pushgp-map argmap)))