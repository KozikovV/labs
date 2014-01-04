package paterns.interpreter;

import java.util.ArrayList;
import java.util.List;

/**
 * User: zinchenko
 * Date: 30.12.13
 */
public class BaseExpression implements Expression{

    List<Action> actions;

    public BaseExpression() {
        actions = new ArrayList<Action>();
        actions.add(Action.add(0));
    }

    public Expression add(Integer value){
        actions.add(Action.add(value));
        return this;
    }

    public Expression divide(Integer value) {
        actions.add(Action.divide(value));
        return this;
    }

    public Integer result() {
        Integer value = 0;
        for (Action action: actions){
            value = action.result(value);
        }
        return value;
    }

    public Integer calculate(Integer a, Integer b) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
