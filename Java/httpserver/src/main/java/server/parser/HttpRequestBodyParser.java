package server.parser;

import server.HttpMessageBody;
import server.HttpParser;

public class HttpRequestBodyParser implements HttpParser<String, HttpMessageBody> {

    @Override
    public HttpMessageBody parse(String line) {
        // 面倒なのでそのまま
        return new HttpMessageBody(line);
    }
}
