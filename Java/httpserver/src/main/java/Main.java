import server.HttpServer;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {

        HttpServer server = HttpServer.init("localhost", 8080);
        try {
            server.run();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
