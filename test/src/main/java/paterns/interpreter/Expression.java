package paterns.interpreter;

/**
 * User: zinchenko
 * Date: 29.12.13
 */
public interface Expression extends Calculator{

    public Expression add(Integer value);

    public Expression divide(Integer value);

    public Integer result();

//    public Expression addExpr();

}
