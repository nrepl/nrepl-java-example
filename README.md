# nREPL Java Example

This project contains a simple example of how to embed [nREPL](https://github.com/nrepl/nrepl) into a Java application.

## Requirements

- [Maven](https://maven.apache.org/)
- [Clojure CLI](https://clojure.org/guides/getting_started)

## How To Use

Open a terminal and run:

    mvn exec:java

You should see a message saying that nrepl is started on port 7888. Next, open another terminal and run:

    clj -Sdeps '{:deps {nrepl {:mvn/version "0.7.0-alpha2"}}}' -m nrepl.cmdline --connect --host localhost --port 7888

This will create a client connection to the nrepl server.

Once connected, you can interact with the running application via the Clojure REPL. Some things to try:

- `(org.nrepl.App/getLifetime)` will tell you how many seconds the app has been running
- `(org.nrepl.App/shutdown)` will shutdown the nrepl server (and disconnect your client)