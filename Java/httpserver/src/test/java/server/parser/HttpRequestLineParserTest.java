package server.parser;

import org.junit.Test;
import server.HttpMethod;
import server.HttpRequestLine;

import static org.assertj.core.api.Assertions.assertThat;

public class HttpRequestLineParserTest {

    private final HttpRequestLineParser target = new HttpRequestLineParser();

    @Test
    public void testParse() {
        final String testValue = "GET /hoge HTTP/1.1";
        final HttpRequestLine requestLine = target.parse(testValue);

        assertThat(requestLine).as("適切なリクエストラインの場合、期待通りにパースできるか")
                .returns(HttpMethod.GET, HttpRequestLine::getMethod)
                .returns("/hoge", HttpRequestLine::getUrl)
                .returns("HTTP/1.1", HttpRequestLine::getHttpVersion);

    }
}
