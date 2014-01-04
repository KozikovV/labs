package paterns.interpreter;

/**
 * User: zinchenko
 * Date: 29.12.13
 */
public class Client {
    public Integer expression1(Integer a, Integer b, Integer c){
        return Expr.base().add(a).add(b).divide(c).result();
    }
}
