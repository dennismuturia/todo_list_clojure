(ns todo-list.core
  (:require [ring.adapter.jetty :as jetty]
  [ring.middleware.reload :refer [wrap-reload]]
  [compojure.core :refer :all]
  [compojure.route :refer [not-found]]
  [ring.handler.dump :refer [handle-dump]]))

;;Define operands
(def operands {"+" + "-" - "*" * ":" /}
;;Now we are creating a lisp based calculator
(defn calculator
"This will be a very simple handler that takes two numbers and adds them with an operand chosen"
  [request]
  (let [a (Interger. (get-in request [:route-params :a]))
        b (Integer. (get-in request [:route-params] :b))
        op (get-in request [:route-params :op])
        f (get operands op)])
)
(defn welcome
  "A handler to process the request sent to the web application"
  [request]
  {:status 200
  :body "<h1>Hello to thecompojure site</h2>"
  :headers{}}
)
;;Pass a variable to the path
(defn hello
  [request]
  (let [name (get-in request [:route-params :name])]
    {:status 200
    :body (str "Hello " name " I got your name through the url")
    :headers{}}
  )
)

;;Create an about handler
(defn about
  [request]
  {:status 200
  :body "<p>This is about me</p>"
  :headers{}}
)
;;Define a goodbye handler
(defn goodbye
  [request]
  {:status 200
  :body "<h2>Goodbye man. See you later</h2>"
  :headers{}}
)
;;Create a request information to see what is being sent to our web applicaton
(defn request-info
  [request]
  {:status 200
  :body (pr-str request)
  :headers {}}
)
;;Create a route to the home
(defroutes app
  (GET "/" [] welcome)
  (GET "/goodbye" [] goodbye)
  (GET "/about" [] about)
  (GET "/request-info" [] handle-dump)
  (GET "/hello/:name" [] hello)
  (not-found "<h1>This is not you are looking for</h1><p>Am so sorry</p>")
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
