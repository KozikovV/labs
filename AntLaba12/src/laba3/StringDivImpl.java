package laba3;

import interfaces.task3.StringDiv;
import interfaces.task3.StringUtils;

/**
 * 
 * @author zinchenko
 * 
 */
public class StringDivImpl implements StringDiv {

    /**
     * Делит числа представленные строками.
     * 
     * @param s1
     *            первое число
     * @param s2
     *            второе число
     * 
     */
    @Override
    public double div(final String s1, final String s2) {
        if (s2 == null) {
            throw new NullPointerException();
        }
        
        if (s2 == "0") {
            throw new ArithmeticException();
        }
        
        StringUtils stringUtils = new StringUtilsImpl();

        double d1 = stringUtils.parseDouble(s1);
        double d2 = stringUtils.parseDouble(s2);

        if (d2 == 0) {
            throw new IllegalArgumentException();
        }

        return d1 / d2;
    }

}
