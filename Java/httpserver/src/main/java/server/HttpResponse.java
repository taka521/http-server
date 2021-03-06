package server;

public class HttpResponse {

    public final String httpVersion;
    public final HttpStatus status;
    public final HttpMessageBody messageBody;

    public HttpResponse(String httpVersion, HttpStatus status, HttpMessageBody messageBody) {
        this.httpVersion = httpVersion;
        this.status = status;
        this.messageBody = messageBody;
    }
}
