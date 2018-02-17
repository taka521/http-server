package server.parser;

import server.api.HttpRequest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class HttpRequestParser {

    private final HttpRequestImpl request;

    public HttpRequestParser(InputStream input) {
        request = new HttpRequestImpl();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(input))) {
            readHeader(reader);
            readBody(reader);
        } catch (IOException e) {
            // Nothing to do.
        }
    }

    /** Httpリクエストを取得します。 */
    public HttpRequest getHttpRequest() {
        return request;
    }

    /** リクエストヘッダの読み込み */
    private void readHeader(BufferedReader reader) throws IOException {
        String line = reader.readLine();
        StringBuilder sb = new StringBuilder();

        while (line != null && !line.isEmpty()) {
            if (line.startsWith("Content-Length")) {
                request.contentLength = Integer.parseInt(line.split(":")[1].trim());
            }
            sb.append(line).append("\n");
            line = reader.readLine();
        }

        request.header = sb.toString();
    }

    /** リクエストボディの読み込み */
    private void readBody(BufferedReader reader) throws IOException {
        if (request.contentLength > 0) {
            char[] c = new char[request.contentLength];
            reader.read(c);
            request.body = String.valueOf(c);
        }
    }

    private static class HttpRequestImpl implements HttpRequest {

        private int contentLength = -1;
        private String header;
        private String body;

        @Override
        public String getHeaderText() {
            return this.header;
        }

        @Override
        public String getBodyText() {
            return this.body;
        }
    }

}
