(ns nrepl.middleware.time
  (:require [nrepl.misc :refer (response-for)]
            [nrepl.transport :as t]))

(defn current-time
  [h]
  (let [start-time (System/currentTimeMillis)]
    (println "server start time: " start-time)
    (fn [{:keys [op transport] :as msg}]
      (if (= "time?" op)
        (t/send transport (response-for msg :status :done :time (- (System/currentTimeMillis)
                                                                   start-time)))
        (h msg)))))