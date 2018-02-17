package server.api.impl;

import server.api.HttpRequest;
import server.api.HttpRequestParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class HttpRequestParserImpl implements HttpRequestParser {

    private final HttpRequestImpl request;
    private boolean parsed = false;

    public HttpRequestParserImpl() {
        request = new HttpRequestImpl();
    }

    @Override
    public HttpRequest parse(InputStream input) {
        if(this.parsed) return request;

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(input))) {
            readHeader(reader);
            readBody(reader);
            this.parsed = true;
        } catch (IOException e) {
            // Nothing to do.
        }
        return this.request;
    }

    @Override
    public boolean isParsed() {
        return this.parsed;
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
