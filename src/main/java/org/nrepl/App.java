package org.nrepl;

import clojure.java.api.Clojure;
import clojure.lang.IFn;

public class App {
    public static App instance;
    private Object server;

    public App(int port, Object handler) {
        IFn start = Clojure.var("nrepl.server", "start-server");
        server = start.invoke(
            Clojure.read(":port"), Clojure.read(Integer.toString(port)),
            Clojure.read(":handler"), handler);
        System.out.println("nrepl server started on port " + port);
    }

    public App(int port) {
        this(port, Clojure.var("nrepl.server", "default-handler").invoke());
    }

    public void shutdown() {
        if (server != null) {
            IFn stop = Clojure.var("nrepl.server", "stop-server");
            stop.invoke(server);
        }
        System.exit(0);
    }

    public static void main(String[] args) {
        System.out.println("nREPL server example");
        IFn require = Clojure.var("clojure.core", "require");
        require.invoke(Clojure.read("nrepl.server"));
        int port = 7888;
        instance = new App(port);
    }
}
