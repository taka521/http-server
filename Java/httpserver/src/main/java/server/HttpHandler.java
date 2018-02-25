package server;

@FunctionalInterface
public interface HttpHandler {
    HttpResponse handle(final HttpRequest request);
}
