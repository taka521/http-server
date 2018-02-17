package server;

import server.api.HttpRequest;
import server.api.HttpRequestParser;
import server.api.impl.HttpRequestParserImpl;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Logger;

/**
 * HttpServer
 */
public class HttpServer {

    /** ロガー */
    private static final Logger logger = Logger.getLogger(HttpServer.class.getName());

    /** ホスト名 */
    private final String host;

    /** ポート番号 */
    private final int port;

    /** サーバが実行中か */
    private boolean running = false;

    private HttpServer(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public static HttpServer init(String host, int port) {
        return new HttpServer(host, port);
    }

    public void run() throws IOException {

        logger.info("=== server start ===");

        try (ServerSocket server = new ServerSocket(port);
             Socket socket = server.accept();
             InputStream input = socket.getInputStream()) {

            HttpRequest request = HttpRequestParser.get().parse(input);
            System.out.println(request.getHeaderText());
            System.out.println(request.getBodyText());
        }

        logger.info("=== server stop === ");
    }

}
