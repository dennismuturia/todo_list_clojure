(ns todo-list.core
  (:require [ring.adapter.jetty :as jetty]))

;;Include the main that will contain to run Jetty and Ring
(defn -main
  "The start of the ring web service"
  [port-number]
  (jetty/run-jetty
    (fn [request] {:status 200
                  :body "<h2>Hello clojure</h2> <p>This is just a start to the place</p>"
                  :headers {}})
                  {:port (Integer. port-number)}))

(defn welcome
  "A handler to process the request sent to the web application"
  [request]
  (if ("/" (:uri request))
  {:status 200
  :body "<h1>Hello Clojure world </h1> <p> Welcome to my application </p>"
  :headers {}}
  {:status 404
  :body "<h1>Sorry the page cant be found</h1>
    <p>Page not found</p>"
    :headers {}}))
