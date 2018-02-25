package server;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class HttpHeaderLine {

    private final String key;
    private final List<String> values;

    public HttpHeaderLine(final String key, final List<String> values) {
        this.key = key;
        this.values = new ArrayList<>(values);
    }

    public String getKey() {
        return key;
    }

    public List<String> getValues() {
        return new ArrayList<>(values);
    }

    @Override
    public String toString() {
        return key + ": " +  values.stream().collect(Collectors.joining(", "));
    }
}
