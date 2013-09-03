package laba2;

import interfaces.task2.FractionNumber;
import interfaces.task2.FractionNumberOperation;

/**
 * Операции с дробями.
 * 
 * @author zinchenko
 * 
 */
public class FractionNumberOperationImpl implements FractionNumberOperation {

    /**
     * Сумма дробей.
     * 
     * @param fn1
     *            первая дробь
     * @param fn2
     *            вторая дробь
     * 
     */
    @Override
    public FractionNumber add(final FractionNumber fn1, final FractionNumber fn2) {

        int generalDivisor = fn1.getDivisor() * fn2.getDivisor();
        int newDividend1 = fn2.getDivisor() * fn1.getDividend();
        int newDividend2 = fn1.getDivisor() * fn2.getDividend();
        int newDividend = newDividend1 + newDividend2;

        FractionNumber fn = new FractionNumberImpl(newDividend, generalDivisor);

        return fn;
    }

    /**
     * Произведение дробей.
     * 
     * @param fn1
     *            первая дробь
     * @param fn2
     *            вторая дробь
     * 
     */
    @Override
    public FractionNumber mul(final FractionNumber fn1, final FractionNumber fn2) {

        int newDividend = fn1.getDividend() * fn2.getDividend();
        int newDivisor = fn1.getDivisor() * fn2.getDivisor();

        FractionNumber fn = new FractionNumberImpl(newDividend, newDivisor);

        return fn;
    }

    /**
     * Деление дробей.
     * 
     * @param fn1
     *            первая дробь
     * @param fn2
     *            вторая дробь
     * 
     */
    @Override
    public FractionNumber div(final FractionNumber fn1, final FractionNumber fn2) {

        if (fn2.getDividend() == 0) {
            throw new ArithmeticException();
        }

        int newDividend = fn1.getDividend() * fn2.getDivisor();
        int newDivisor = fn1.getDivisor() * fn2.getDividend();

        FractionNumber fn = new FractionNumberImpl(newDividend, newDivisor);

        return fn;
    }

    /**
     * Разница дробей.
     * 
     * @param fn1
     *            первая дробь
     * @param fn2
     *            вторая дробь
     * 
     */
    @Override
    public FractionNumber sub(final FractionNumber fn1, final FractionNumber fn2) {
        int generalDivisor = fn1.getDivisor() * fn2.getDivisor();
        int newDividend1 = fn2.getDivisor() * fn1.getDividend();
        int newDividend2 = fn1.getDivisor() * fn2.getDividend();
        int newDividend = newDividend1 - newDividend2;

        FractionNumber fn = new FractionNumberImpl(newDividend, generalDivisor);

        return fn;
    }

}
