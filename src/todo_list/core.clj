(ns todo-list.core
  (:require [ring.adapter.jetty :as jetty]
  [ring.middleware.reload :refer [wrap-reload]]
  [compojure.core :refer :all]
  [compojure.route :refer [not-found]]))


(defn welcome
  "A handler to process the request sent to the web application"
  [request]
  {:status 200
  :body "<h1>Hello to thecompojure site</h2>"
  :headers{}}
)

;;Create a route to the home
(defroutes app
  (GET "/" [] welcome)
  (not-found "<h1>This is not you are looking for</h1><p>Am so sorry</p>")
)
;;A goodbye routr
(defroutes goodbye
)

(defn -main
"The start of the webservice"
  [port-number]
  (jetty/run-jetty app
    {:port (Integer. port-number)}
  )
)

;;This is only used in development
(defn -dev-main
  [port-number]
  (jetty/run-jetty
    (wrap-reload #'app)
    {:port (Integer. port-number)}))
