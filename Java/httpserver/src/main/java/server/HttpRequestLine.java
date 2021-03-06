package server;

public class HttpRequestLine {

    private final HttpMethod method;
    private final String url;
    private final String httpVersion;

    public HttpRequestLine(HttpMethod method, String url, String httpVersion) {
        this.method = method;
        this.url = url;
        this.httpVersion = httpVersion;
    }

    public HttpMethod getMethod() {
        return method;
    }

    public String getUrl() {
        return url;
    }

    public String getHttpVersion() {
        return httpVersion;
    }

    @Override
    public String toString() {
        return "HttpRequestLine{" + "method=" + method + ", url='" + url + '\'' + ", httpVersion='" + httpVersion + '\'' + '}';
    }
}
