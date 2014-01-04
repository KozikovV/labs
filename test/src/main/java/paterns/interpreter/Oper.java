package paterns.interpreter;

/**
 * User: zinchenko
 * Date: 29.12.13
 */
public class Oper {

    public static Operator add(){
        return new AddOperator();
    }

    public static Operator divide(){
        return new DivideOperator();
    }

}
