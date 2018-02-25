package server.parser;

import server.HttpParser;
import server.HttpResponse;

public class HttpResponseParser implements HttpParser<HttpResponse, String> {

    private static final String SEPARATOR = " ";
    private static final String CRLF = "\r\n";

    @Override
    public String parse(final HttpResponse response) {
        final StringBuilder builder = new StringBuilder();

        // レスポンス行
        builder.append(response.httpVersion).append(SEPARATOR);
        builder.append(response.status.code()).append(SEPARATOR).append(response.status.message());
        builder.append(CRLF);

        // レスポンスヘッダ
        builder.append("Content-Type: text/html").append(CRLF);
        builder.append(CRLF);

        // レスポンスボディ
        builder.append(response.messageBody.getMessage());
        builder.append(CRLF);

        return builder.toString();
    }
}
