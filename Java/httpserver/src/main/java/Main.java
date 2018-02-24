import server.HttpServer;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        try {
            HttpServer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
