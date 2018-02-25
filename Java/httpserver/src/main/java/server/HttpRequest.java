package server;

import java.util.Map;

public class HttpRequest {

    private HttpRequestLine requestLine;
    private Map<String, HttpHeaderLine> headers;
    private HttpMessageBody body;

    private HttpRequest(final Builder builder) {
        this.requestLine = builder.requestLine;
        this.headers = builder.headers;
        this.body = builder.body;
    }

    public static Builder builder(){
        return new Builder();
    }

    public static class Builder {
        private HttpRequestLine requestLine;
        private Map<String, HttpHeaderLine> headers;
        private HttpMessageBody body;

        public Builder setRequestLine(HttpRequestLine requestLine) {
            this.requestLine = requestLine;
            return this;
        }

        public Builder setHeaders(Map<String, HttpHeaderLine> headers) {
            this.headers = headers;
            return this;
        }

        public Builder setBody(HttpMessageBody body) {
            this.body = body;
            return this;
        }

        public HttpRequest build() {
            return new HttpRequest(this);
        }
    }

    public HttpRequestLine getRequestLine() {
        return requestLine;
    }

    public Map<String, HttpHeaderLine> getHeaders() {
        return headers;
    }

    public HttpMessageBody getBody() {
        return body;
    }

    @Override
    public String toString() {
        return "HttpRequest{" + "requestLine=" + requestLine + ", headers=" + headers + ", body=" + body + '}';
    }
}
