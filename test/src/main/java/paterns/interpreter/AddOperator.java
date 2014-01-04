package paterns.interpreter;

/**
 * User: zinchenko
 * Date: 29.12.13
 */
public class AddOperator implements Operator{

    public Integer calculate(Integer a, Integer b) {
        return a + b;
    }

}
