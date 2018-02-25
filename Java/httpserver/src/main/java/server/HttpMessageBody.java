package server;

public class HttpMessageBody {

    private String message;

    public HttpMessageBody(final String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "HttpMessageBody{" + "message='" + message + '\'' + '}';
    }
}
