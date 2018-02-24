package server;

import java.util.Map;

public class HttpRequestHeader {

    private final Map<String, HttpHeaderLine> headers;

    public HttpRequestHeader(Map<String, HttpHeaderLine> headers) {
        this.headers = headers;
    }

    public Map<String, HttpHeaderLine> getHeaders() {
        return headers;
    }
}
