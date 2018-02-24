package server.parser;

import org.assertj.core.data.Index;
import org.junit.Test;
import server.HttpHeaderLine;

import static org.assertj.core.api.Assertions.assertThat;


public class HttpRequestHeaderLineParserTest {

    private static HttpRequestHeaderLineParser target = new HttpRequestHeaderLineParser();

    @Test
    public void parse() throws Exception {
        final String headerLine = "Accept: image/gif, image/jpeg, */*";
        final HttpHeaderLine httpHeaderLine = target.parse(headerLine);
        assertThat(httpHeaderLine).as("キーが取得出来ているか").returns("Accept", HttpHeaderLine::getKey);
        assertThat(httpHeaderLine.getValues()).as("")
                .hasSize(3)
                .contains("image/gif", Index.atIndex(0))
                .contains("image/jpeg", Index.atIndex(1))
                .contains("*/*", Index.atIndex(2));
    }

}