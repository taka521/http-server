package server;

/**
 * Httpメソッドの列挙体
 */
public enum HttpMethod {
    GET,
    POST,
    PUT,
    DELETE;

    /**
     * メソッドの文字列から、{@link HttpMethod}の列挙値を取得します。
     * <p>引数の値が何れのメソッドにもヒットしない場合、{@link HttpMethod#GET} を返却します。
     *
     * @param value メソッド文字列
     *
     * @return 引数と一致するHttpMethod
     */
    public static HttpMethod of(final String value) {
        for (HttpMethod method : HttpMethod.values()) {
            if (method.name().equalsIgnoreCase(value)) return method;
        }
        // 何れのメソッドでもない場合、GETとして扱う
        return GET;
    }
}
