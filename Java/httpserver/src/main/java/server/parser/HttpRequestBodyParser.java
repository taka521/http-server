package server.parser;

import server.HttpParser;
import server.HttpRequestBody;

public class HttpRequestBodyParser implements HttpParser<String, HttpRequestBody> {

    @Override
    public HttpRequestBody parse(String line) {
        // 面倒なのでそのまま
        return new HttpRequestBody(line);
    }
}
