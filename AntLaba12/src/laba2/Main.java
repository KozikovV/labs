package laba2;

import interfaces.task2.FractionNumber;
import interfaces.task2.FractionNumberOperation;

public class Main {

    /**
     * @param args
     */
    public static void main(String[] args) {

        // Create

        FractionNumber fn11 = new FractionNumberImpl(1, 3);
        System.out.println("fn1 =  " + fn11.toStringValue());
        System.out.println("fn1 =  " + fn11.value());

        try {
            fn11.setDivisor(0);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }

        System.out.println();

        // Add

        System.out.println("add");

        FractionNumberOperation fno = new FractionNumberOperationImpl();

        FractionNumber fn21 = new FractionNumberImpl(2, 3);
        FractionNumber fn22 = new FractionNumberImpl(5, 6);
        System.out.println("fn21 = " + fn21.toStringValue());
        System.out.println("fn22 = " + fn22.toStringValue());

        FractionNumber fn2r = fno.add(fn21, fn22);
        System.out.println("fn2r = " + fn2r.toStringValue());

        System.out.println();

        // Sub

        System.out.println("sub");

        FractionNumber fn31 = new FractionNumberImpl(2, 3);
        FractionNumber fn32 = new FractionNumberImpl(5, 6);
        System.out.println("fn31 = " + fn31.toStringValue());
        System.out.println("fn32 = " + fn32.toStringValue());

        FractionNumber fn3r = fno.sub(fn31, fn32);
        System.out.println("fn3r = " + fn3r.toStringValue());

        System.out.println();

        // Mul

        System.out.println("mul");

        FractionNumber fn41 = new FractionNumberImpl(2, 3);
        FractionNumber fn42 = new FractionNumberImpl(5, 6);
        System.out.println("fn41 = " + fn41.toStringValue());
        System.out.println("fn42 = " + fn42.toStringValue());

        FractionNumber fn4r = fno.mul(fn41, fn42);
        System.out.println("fn4r = " + fn4r.toStringValue());

        System.out.println();

        // Div

        System.out.println("div");

        FractionNumber fn51 = new FractionNumberImpl(2, 3);
        FractionNumber fn52 = new FractionNumberImpl(5, 6);
        System.out.println("fn51 = " + fn51.toStringValue());
        System.out.println("fn52 = " + fn52.toStringValue());

        FractionNumber fn5r = fno.div(fn51, fn52);
        System.out.println("fn5r = " + fn5r.toStringValue());
        
        try {
            fno.div(fn51, new FractionNumberImpl(0, 1));
        } catch (ArithmeticException e) {
            e.printStackTrace();
        }

        System.out.println();

    }

}
