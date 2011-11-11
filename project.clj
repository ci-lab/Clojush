(defproject clojush "0.0.2"
  :description "The Push programming language and the PushGP genetic programming system implemented in Clojure.
                See http://hampshire.edu/lspector/push.html"
  :dependencies [[org.clojure/clojure "1.3.0"]
		 [org.clojure/math.numeric-tower "0.0.1"]]
  :dev-dependencies [[swank-clojure "1.3.2" :exclusions [org.clojure/clojure]]]
  :main clojush)