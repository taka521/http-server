package server.parser;

import server.HttpMethod;
import server.HttpParser;
import server.HttpRequestLine;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * リクエストラインのパーサ
 */
public class HttpRequestLineParser implements HttpParser<String, HttpRequestLine> {

    @Override
    public HttpRequestLine parse(final String line) {
        List<String> parsed = Stream.of(line.split(" ")).map(String::trim).collect(Collectors.toList());
        return new HttpRequestLine(HttpMethod.of(parsed.get(0)), parsed.get(1), parsed.get(2));
    }
}
