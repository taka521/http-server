package server.handler;

import server.HttpHandler;
import server.HttpMessageBody;
import server.HttpRequest;
import server.HttpResponse;
import server.HttpStatus;

public class GetHandler implements HttpHandler {

    @Override
    public HttpResponse handle(final HttpRequest request) {
        // リクエストのボディ部に "OK!!" を付加して返却するだけ。
        final HttpMessageBody body = new HttpMessageBody(request.getBody().getMessage() + "\r\nOK!!");
        return new HttpResponse("HTTP/1.1", HttpStatus.OK, body);
    }
}
