(ns cli.core
  (:require [clojure.tools.cli :as cli]))

(def cli-options
  [["-n" "--number PORT" "A number"
    :parse-fn (partial js/parseInt)
    :validate [#(< 0 % 0x10000) "Must be a number between 0 and 65536"]]])

(defn init [& args]
  (let [{{number :number} :options} (cli/parse-opts args cli-options)

        result (if (-> number
                (mod 2)
                (= 0))
          "EVEN"
          "ODD")]
    (print (str "Your number was " result))))
