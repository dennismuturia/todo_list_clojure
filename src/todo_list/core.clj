;;Include the ring namespase and remove the previous code for hello world
(ns todo-list.core
  (:require [ring.adapter.jetty :as jetty])

;;Include the main that will contain to run Jetty and Ring
(defn -main
  "The start of the ring web service"
  [port-number]
  (jetty/run-jetty
    (fn [request] {:status 200
                  :body "<h2>Hello clojure</h2>"
                  :headers {}})
                  {:port (Integer. port-number)}))
