package org.nrepl;

import clojure.java.api.Clojure;
import clojure.lang.IFn;

public final class AppWithCustomMiddleware {
    public static void main(String[] args) {
        System.out.println("nREPL server with custom middleware example");
        IFn require = Clojure.var("clojure.core", "require");
        require.invoke(Clojure.read("nrepl.server"));
        require.invoke(Clojure.read("nrepl.middleware.time"));

        int port = 7888;
        IFn defaultHandler = Clojure.var("nrepl.server", "default-handler");
        IFn timeMiddleware = Clojure.var("nrepl.middleware.time", "current-time");
        App.instance = new App(port, defaultHandler.invoke(timeMiddleware));
    }
}