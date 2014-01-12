package zinchenko;

/**
 * User: zinchenko
 * Date: 12.01.14
 */
public class MinusParam implements Parametrized {
    @Override
    public Integer action(Integer a, Integer b) {
        return a - b;
    }
}
