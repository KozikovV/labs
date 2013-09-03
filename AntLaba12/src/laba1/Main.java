package laba1;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.net.ssl.TrustManagerFactorySpi;

import org.w3c.dom.ranges.RangeException;

public class Main {

    /**
     * @param args
     */
    public static void main(String[] args) {

        // 1
        double[][] m = { { 1.0, -2.0, 3.0 }, { 5.0, 1.0, -3.0 } };
        double[][] om = { { 2.0, 3.0, 4.0 }, { 1.0, 3.0, 5.0 } };
        Matrix matrix = new Matrix(m);
        matrix.adding(new Matrix(om));

        double[][] m2 = { { 1.0, 2.0, 3.0 }, { 4.0, 5.0, 6.0 } };
        double[][] om2 = { { 1.0, 1.0, 1.0 }, { 1.0, 2.0, 4.0 },
                { 1.0, 3.0, 9.0 } };
        Matrix matrix2 = new Matrix(m2);
        Matrix matrix3 = new Matrix(om2);
        Matrix res2 = matrix2.product(matrix3);

        Matrix matrix4 = new Matrix(m2);
        Matrix tm = matrix4.transp();

        // 2,3

        Figura figura0 = new Quadrangle(new Point(0, 0), new Point(0, 1),
                new Point(1, 1), new Point(1, 0));
        figura0.print();
        figura0.move(3, 2);
        figura0.print();
        figura0.scale(2);
        figura0.print();

        int size = 30;
        double maxSizeOfSide = 5;
        double maxSizeOfTable = 100;
        List<Figura> figuras = new ArrayList<Figura>(size);

        Random random = new Random();
        for (int i = 0; i < size; i++) {

            int type = random.nextInt(2);
            Figura figura = null;
            Point[] points = null;
            switch (type) {
            case 0:

                int n0 = 4;
                points = new Point[n0];

                points[0] = new Point(maxSizeOfTable * random.nextDouble(),
                        maxSizeOfTable * random.nextDouble());

                for (int j = 1; j < n0; j++) {

                    double x = maxSizeOfSide * random.nextDouble()
                            + points[0].getX();
                    double y = maxSizeOfSide * random.nextDouble()
                            + points[0].getY();

                    points[j] = new Point(x, y);
                }

                figura = new Quadrangle(points[0], points[1], points[2],
                        points[3]);

                figuras.add(figura);

                break;
            case 1:
                int n1 = 3;
                points = new Point[n1];

                points[0] = new Point(maxSizeOfTable * random.nextDouble(),
                        maxSizeOfTable * random.nextDouble());

                for (int j = 1; j < n1; j++) {

                    double x = maxSizeOfSide * random.nextDouble()
                            + points[0].getX();
                    double y = maxSizeOfSide * random.nextDouble()
                            + points[0].getY();

                    points[j] = new Point(x, y);
                }

                figura = new Triangle(points[0], points[1], points[2]);

                figuras.add(figura);

                break;
            }

        }

        for (Figura f : figuras) {
            f.print();
        }

        for (int i = 0; i < figuras.size(); i++) {

            Figura f = figuras.get(i);

            if (f instanceof Quadrangle) {
                System.out.println("index " + i + " have Quadrangle");
            } else if (f instanceof Triangle) {
                System.out.println("index " + i + " have Triangle");
            } else {
                throw new RuntimeException("Don't know so figura.");
            }
        }

    }
}
