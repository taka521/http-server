package server;

/**
 * パーサーインターフェース
 *
 * @param <I> 入力値となる型
 * @param <R> 出力値となる型
 */
@FunctionalInterface
public interface HttpParser<I, R> {

    /**
     * 入力値を元に、指定された型にパースします。
     *
     * @param in 入力値
     *
     * @return 入力値をパースした値
     */
    R parse(final I in);

}
