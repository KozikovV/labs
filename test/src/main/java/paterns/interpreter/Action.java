package paterns.interpreter;

/**
 * User: zinchenko
 * Date: 30.12.13
 */
public class Action {

    private Operator operator;

    private Integer value;

    private Action(Operator operator, Integer value) {
        this.operator = operator;
        this.value = value;
    }

    public static Action create(Operator operator, Integer value) {
        return new Action(operator, value);
    }

    public static Action add(Integer value) {
        return new Action(Oper.add(), value);
    }

    public static Action divide(Integer value){
        return new Action(Oper.divide(), value);
    }

    public Integer result(Integer value){
        return operator.calculate(value, this.value);
    }

}
