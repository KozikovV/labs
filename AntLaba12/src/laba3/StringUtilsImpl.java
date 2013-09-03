package laba3;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import interfaces.task3.StringUtils;

/**
 * 
 * @author zinchenko
 * 
 */
public class StringUtilsImpl implements StringUtils {

    /**
     * Функция сравнивет символы первой строки с символами во второй строке и
     * выводит в результирующую сторку все символы которые есть в первой, но их
     * нет во второй.
     * 
     * @param s1
     *            первая строка
     * @param s2
     *            вторая строка
     * @return результирующая строка
     * 
     */
    @Override
    public String compareWords(final String s1, final String s2) {
        StringBuilder sb = new StringBuilder();

        char[] c2 = s2.toCharArray();

        for (int i = 0; i < s1.length(); i++) {
            char c = s1.charAt(i);
            if (!isContains(c, c2)) {
                sb.append(c);
            }

        }

        return sb.toString();
    }

    /**
     * Проверяет содержит ли массив {@code с2} символ {@code с}.
     * 
     * @param c
     *            символ
     * @param c2
     *            массив
     * @return {@code true} если содержит {@code false} иначе.
     */
    private boolean isContains(final char c, final char[] c2) {
        for (int i = 0; i < c2.length; i++) {
            if (c2[i] == c) {
                return true;
            }
        }
        return false;
    }

    /**
     * Обратная строки.
     * 
     * @param s
     *            строка для инвертации.
     * @return инвентированная строка.
     * 
     */
    @Override
    public String invert(final String s) {
        if (s == null) {
            return "";
        }
        return new StringBuilder(s).reverse().toString();
    }

    /**
     * Парсит строковое значение вещественного числа в double.
     * 
     * @param s
     *            строковое представление вещественного числа.
     * @return число
     */
    @Override
    public double parseDouble(String s) {

        // s = "1.23e.11";

        String regexWithE = "(^[-]?\\d*[.]?\\d+){1}(([e]{1}[-+]{1}\\d+){1}|[ \\t\\n\\x0B\\f\\r]{1}|$)";

        Pattern p = Pattern.compile(regexWithE);
        Matcher matcher = p.matcher(s);

        if (matcher.find()) {
            String doubleAsString = matcher.group();
            double d = Double.parseDouble(doubleAsString);
            return d;
        } else {
            throw new IllegalArgumentException(new Error());
        }

    }

}
