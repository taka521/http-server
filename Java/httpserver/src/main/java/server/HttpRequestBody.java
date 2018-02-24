package server;

public class HttpRequestBody {

    private String message;

    public HttpRequestBody(final String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "HttpRequestBody{" + "message='" + message + '\'' + '}';
    }
}
