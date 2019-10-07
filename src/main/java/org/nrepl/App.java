package org.nrepl;

import clojure.java.api.Clojure;
import clojure.lang.IFn;

public final class App {
    private static Object server;
    private static int lifetime;

    private App() {
    }

    public static Object startServer(int port) {
        IFn require = Clojure.var("clojure.core", "require");
        require.invoke(Clojure.read("nrepl.server"));
        IFn start = Clojure.var("nrepl.server", "start-server");
        return start.invoke(Clojure.read(":port"), Clojure.read(Integer.toString(port)));
    }

    public static void shutdown() {
        if (server != null) {
            IFn require = Clojure.var("clojure.core", "require");
            require.invoke(Clojure.read("nrepl.server"));
            IFn stop = Clojure.var("nrepl.server", "stop-server");
            stop.invoke(server);
        }
        System.exit(0);
    }

    public static int getLifetime() {
        return lifetime;
    }

    public static void main(String[] args) {
        int port = 7888;
        server = startServer(port);
        System.out.println("nrepl server started on port " + port);
        while (true) {
            try {
                Thread.sleep(1000);
                lifetime += 1;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
