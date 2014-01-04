package paterns.interpreter;

/**
 * User: zinchenko
 * Date: 29.12.13
 */
public class Expr {

    public static Expression base(){
        return new BaseExpression();
    }

//    public static Expression bracket(Expression expression){
//        return new BracketExpression();
//    }

//    public static Expression create(Constant firstConstant){
//        return new RootExpression(firstConstant);
//    }
}
