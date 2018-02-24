package server.parser;

import server.HttpHeaderLine;
import server.HttpParser;
import server.HttpRequest;
import server.HttpRequestBody;
import server.HttpRequestLine;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class HttpRequestParser implements HttpParser<BufferedReader, Optional<HttpRequest>> {

    private static final HttpParser<String, HttpRequestLine> REQUEST_LINE_PARSE = new HttpRequestLineParser();
    private static final HttpParser<String, HttpHeaderLine> HEADER_LINE_PARSER = new HttpRequestHeaderLineParser();
    private static final HttpParser<String, HttpRequestBody> REQUEST_BODY_PARSER = new HttpRequestBodyParser();

    @Override
    public Optional<HttpRequest> parse(final BufferedReader in) {
        try {
            // リクエストラインのパース
            String line = in.readLine();
            final HttpRequestLine requestLine = REQUEST_LINE_PARSE.parse(line);

            // リクエストヘッダのパース
            line = in.readLine();
            final Map<String, HttpHeaderLine> headers = new HashMap<>();
            HttpHeaderLine headerLine = null;
            while (line != null && !line.isEmpty()) {
                headerLine = HEADER_LINE_PARSER.parse(line);
                headers.put(headerLine.getKey(), headerLine);
                line = in.readLine();
            }

            // リクエストボディのパース
            StringBuilder sb = new StringBuilder();
            if(headers.containsKey("Content-Length")){
                int contentLength = Integer.parseInt(headers.get("Content-Length").getValues().get(0));
                char[] c = new char[contentLength];
                in.read(c);
                sb.append(c);
            }
            final HttpRequestBody body = new HttpRequestBody(sb.toString());

            // Httpリクエストの返却
            return Optional.of(
                    HttpRequest.builder().setRequestLine(requestLine).setHeaders(headers).setBody(body).build());

        } catch (IOException e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }

}
