package server.api;

import java.util.HashMap;
import java.util.Map;

public class HttpHeader {

    private Map<String, String> headers = new HashMap<>();

    public String get(String fieldName) {
        return this.headers.get(fieldName);
    }

    public void push(String fieldName, String value) {
        this.headers.put(fieldName, value);
    }

    public int getContentLength() {
        return Integer.parseInt(this.headers.getOrDefault("Content-Length", "0"));
    }

    public boolean isChankedTransfer() {
        return this.headers.getOrDefault("Transfer-Encoding", "-").equals("chunked");
    }

}
