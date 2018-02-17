package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.CharBuffer;
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
             BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

            String line = reader.readLine();
            StringBuffer header = new StringBuffer();
            int contentLength = 0;

            // Content-Lengthの値を取得
            while (line != null && !line.isEmpty()) {
                if (line.startsWith("Content-Length")) {
                    contentLength = Integer.parseInt(line.split(":")[1].trim());
                }
                header.append(line).append("\n");
                line = reader.readLine();
            }

            // 空行を発見したら、以降はボディ部になる

            StringBuffer body = new StringBuffer();

            // ヘッダの Content-Length からボディ部を読み込むバイト数を取得し、
            // 指定されたバイス数のデータを取得する。
            if (contentLength > 0) {
                char[] c = new char[contentLength];
                reader.read(c);
                body.append(c);
            }

            System.out.println(header);
            System.out.println(body);

        }

        logger.info("=== server stop === ");
    }

}
