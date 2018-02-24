package server;

public class HttpResponse {

    public final String httpVersion;
    public final HttpStatus status;
    public final String messageBody;

    public HttpResponse(String httpVersion, HttpStatus status, String messageBody) {
        this.httpVersion = httpVersion;
        this.status = status;
        this.messageBody = messageBody;
    }
}
