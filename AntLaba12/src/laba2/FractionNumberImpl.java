package laba2;

import interfaces.task2.FractionNumber;

/**
 * Дробь
 * 
 * @author zinchenko
 * 
 */
public class FractionNumberImpl implements FractionNumber {

    private int dividend = 0;
    private int divisor = 1;

    /**
     * Конструктор по умолчанию.
     */
    public FractionNumberImpl() {

    }

    /**
     * Конструктор c параметрами.
     * 
     * @param divident
     *            числитель
     * @param divisor
     *            знаменатель
     */
    public FractionNumberImpl(final int dividend, final int divisor) {
        setDividend(dividend);
        setDivisor(divisor);
    }

    /**
     * Получить числитель.
     * 
     * @return числитель
     */
    @Override
    public int getDividend() {
        return dividend;
    }

    /**
     * Получить знаменатель.
     * 
     * @return знаменатель
     */
    @Override
    public int getDivisor() {
        return divisor;
    }

    /**
     * Установить числитель.
     * 
     * @param числитель
     */
    @Override
    public void setDividend(final int arg0) {

        dividend = arg0;

    }

    /**
     * Установить знаменатель.
     * 
     * @return знаменатель
     */
    @Override
    public void setDivisor(final int arg0) {
        if (arg0 == 0) {
            throw new IllegalArgumentException("The value can not be zero");
        }

        divisor = arg0;

    }

    /**
     * Представление дроби в виде строки.
     * 
     * @return строка дроби
     */
    @Override
    public String toStringValue() {

        StringBuilder b = new StringBuilder();
        b.append(dividend);
        b.append("/");
        b.append(divisor);

        return b.toString();
    }

    /**
     * Значение дроби.
     * 
     * @return значение
     */
    @Override
    public double value() {

        double value = (double) dividend / (double) divisor;

        return value;

    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + dividend;
        result = prime * result + divisor;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        FractionNumberImpl other = (FractionNumberImpl) obj;
        if (dividend != other.dividend) {
            return false;
        }
        if (divisor != other.divisor) {
            return false;
        }
        return true;
    }

}
