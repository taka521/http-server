package server;

import server.parser.HttpRequestParser;
import sun.tools.jconsole.Worker;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Logger;

public class HttpServer {

    private static final Logger logger = Logger.getLogger(HttpServer.class.getName());
    private static final int PORT = 8080;
    private static final String CRLF = "\r\n";

    private static ExecutorService service = Executors.newCachedThreadPool();

    public static void start() throws IOException {

        logger.info("==== server start ====");

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            Socket socket;
            while (true) {
                socket = serverSocket.accept();
                service.execute(new Worker(socket));
            }
        } catch (IOException e) {
            logger.warning(e.getMessage());
        }

        logger.info("==== server stop ====");
    }

    private static class Worker implements Runnable {

        private final Socket socket;

        Worker(final Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            if (socket == null) return;

            try (final BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                 final BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()))) {

                new HttpRequestParser().parse(reader).ifPresent(request -> {
                    System.out.println("[ThreadName:" + Thread.currentThread().getName() + "]");
                    System.out.println(request);

                    try {
                        writer.write("HTTP/1.1 200 OK" + CRLF);
                        writer.write(CRLF);
                        writer.write(request.getBody().getMessage() + CRLF);
                        writer.write("OK!!");
                        writer.write(CRLF);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                });
            } catch (IOException e) {
                System.out.println(e);
            }
        }
    }

}
