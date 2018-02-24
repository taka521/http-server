package server.parser;

import server.HttpHeaderLine;
import server.HttpParser;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Httpヘッダの1行をパースするパーサーです。
 */
public class HttpRequestHeaderLineParser implements HttpParser<String, HttpHeaderLine> {

    @Override
    public HttpHeaderLine parse(final String line) {
        final List<String> splitted = Stream.of(line.split(":"))
                .map(s -> s.split(","))
                .flatMap(Stream::of)
                .map(String::trim)
                .collect(Collectors.toList());

        final String key = splitted.get(0);
        splitted.remove(0); // キーは不要なので削除する
        return new HttpHeaderLine(key, splitted);
    }
}
