package server.api;

import server.api.impl.HttpRequestParserImpl;

import java.io.InputStream;

public interface HttpRequestParser {

    static HttpRequestParser get(){
        return new HttpRequestParserImpl();
    }

    boolean isParsed();
    HttpRequest parse(InputStream input);
}
