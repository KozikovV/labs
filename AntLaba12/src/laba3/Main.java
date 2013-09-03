package laba3;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import interfaces.task3.StringDiv;
import interfaces.task3.StringUtils;

/**
 * 
 * @author zinchenko
 * 
 */
public class Main {

    /**
     * @param args
     *            аргументы
     */
    public static void main(final String[] args) {

        String string = "12563";

        // String regex = "\\d+[ \\t\\n\\x0B\\f\\r]{1}|[r]{1}";

        String regex = "\\d+([ \\t\\n\\x0B\\f\\r]{1}|[r]{1}|$)";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(string);

        String res = null;
        boolean b = matcher.find();
        if (b) {
            res = matcher.group();
        }

        // [-]?\\d*[.]?\\d*([e][-+]\\d)?

        // String regexWithE = "[-]?\\d*[.]?\\d+([e]?[-+]?\\d?)?";
        // String regexWithE =
        // "[-]?\\d*[.]?\\d+([e]{1}[-+]{1}\\d+){1}|[ \\t\\n\\x0B\\f\\r]{1}|$";
        // String regexWithE =
        // "[-]?\\d*[.]?\\d+([e]{1}[-+]{1}\\d+)?|[ \\t\\n\\x0B\\f\\r]{1}";
        String regexWithE = "(^[-]?\\d*[.]?\\d+){1}(([e]{1}[-+]{1}\\d+){1}|[ \\t\\n\\x0B\\f\\r]{1}|$)";

        Pattern p1 = Pattern.compile(regexWithE);

        String s1 = "1.2563e+1";
        String s2 = "1.2563e-1";
        String s3 = "1.2563 1234";
        String s4 = "1.2563";
        String s5 = "12563";

        String s6 = "1.23e.11";
        String s7 = "qwe";

        Matcher m1 = p1.matcher(s1);
        Matcher m2 = p1.matcher(s2);
        Matcher m3 = p1.matcher(s3);
        Matcher m4 = p1.matcher(s4);
        Matcher m5 = p1.matcher(s5);
        Matcher m6 = p1.matcher(s6);
        Matcher m7 = p1.matcher(s7);

        boolean fm1 = m1.find();
        boolean fm2 = m2.find();
        boolean fm3 = m3.find();
        boolean fm4 = m4.find();
        boolean fm5 = m5.find();
        boolean fm6 = m6.find();
        boolean fm7 = m7.find();

        System.out.println();

        String ds1 = m1.group();
        String ds2 = m2.group();
        String ds3 = m3.group();
        String ds4 = m4.group();
        String ds5 = m5.group();
        // String ds6 = m6.group();
        // String ds7 = m7.group();

        // compareWords

        StringUtils su = new StringUtilsImpl();

        String s11 = "Ascfg";
        String s12 = "Axccccdhs";

        String s1r = su.compareWords(s11, s12);

        System.out.println("s1r = " + s1r);

        // invert

        String s21 = "Ascfg";

        String s2r = su.invert(s21);

        System.out.println("s2r = " + s2r);

        // parseDouble

        String s31 = "1.2563 1234";

        try {
            System.out.println("String = " + s31 + " double = "
                    + su.parseDouble(s31));
        } catch (Exception e) {
            e.printStackTrace();
        }

        String s32 = "1.2563";

        try {
            System.out.println("String = " + s32 + " double = "
                    + su.parseDouble(s32));
        } catch (Exception e) {
            e.printStackTrace();
        }

        String s62 = "1.23e.1";

        try {
            System.out.println("String = " + s62 + " double = "
                    + su.parseDouble(s62));
        } catch (IllegalArgumentException e) {
            System.out.println("String = " + s62
                    + "catch IllegalArgumentException");
        }

        String s102 = "qwe";

        try {
            System.out.println("String = " + s102 + " double = "
                    + su.parseDouble(s102));
        } catch (IllegalArgumentException e) {
            System.out.println("String = " + s102
                    + " catch IllegalArgumentException");
        }

        String s72 = "1.2563e+1";

        try {
            System.out.println("String = " + s72 + " double = "
                    + su.parseDouble(s72));
        } catch (IllegalArgumentException e) {
            System.out.println("catch IllegalArgumentException");
        }

        String s82 = "1.2563e-1";

        try {
            System.out.println("String = " + s82 + " double = "
                    + su.parseDouble(s82));
        } catch (IllegalArgumentException e) {
            System.out.println("catch IllegalArgumentException");
        }

        String s92 = "12563";

        try {
            System.out.println("String = " + s92 + " double = "
                    + su.parseDouble(s92));
        } catch (IllegalArgumentException e) {
            System.out.println("catch IllegalArgumentException");
        }

        // div----------------------------------------------------

        String s41 = "12.34";
        String s42 = "6.34";

        StringDiv div = new StringDivImpl();
        try {
            System.out.println("Strings = " + s41 + "  " + s42 + " double = "
                    + div.div(s41, s42));
        } catch (Exception e) {
            e.printStackTrace();
        }

        String s51 = "12.34";
        String s52 = "0.0";

        try {
            System.out.println("Strings = " + s51 + "  " + s52 + " double = "
                    + div.div(s51, s52));
        } catch (Exception e) {
            System.out.println("catch IllegalArgumentException");
        }

        // --

        try {
            div.div("1", null);
        } catch (NullPointerException e) {
            System.out.println("catch NullPointerException");
        }

    }

}
