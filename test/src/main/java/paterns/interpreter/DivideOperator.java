package paterns.interpreter;

/**
 * User: zinchenko
 * Date: 30.12.13
 */
public class DivideOperator implements Operator{
    public Integer calculate(Integer a, Integer b) {
        return a/b;
    }
}
