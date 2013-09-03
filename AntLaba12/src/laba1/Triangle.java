package laba1;

/**
 * Треугольник
 * 
 * @author zinchenko
 * 
 */
public class Triangle extends Figura {

    private static final int NUMBER = 3;

    protected Point[] points;

    /**
     * Конструктор без параметров.
     */
    public Triangle() {
        super();
    }

    /**
     * Конструктор с параметрами.
     * 
     * @param point0
     *            точка0
     * @param point1
     *            точка1
     * @param point2
     *            точка2
     */
    public Triangle(final Point point0, final Point point1, final Point point2) {

        points = new Point[NUMBER];

        points[0] = point0;
        points[1] = point1;
        points[2] = point2;

    }

    /**
     * Рисование.
     */
    @Override
    public void print() {

        StringBuilder b = new StringBuilder();

        b.append("Triangle ");

        for (int i = 0; i < NUMBER; i++) {
            b.append(points[i]);
        }

        double perimeter = 0;
        perimeter += Figura.countSizeSide(points[0], points[NUMBER - 1]);
        for (int i = 0; i < NUMBER - 1; i++) {
            perimeter += Figura.countSizeSide(points[i], points[i + 1]);
        }

        b.append(String.format("  perimeter = %.2f", perimeter));

        System.out.println(b.toString());

    }

    /**
     * Перемещение.
     * 
     * @param dx
     *            величина перемещения по оси х
     * @param dy
     *            величина перемещения по оси y
     * 
     */
    @Override
    public void move(final double dx, final double dy) {

        for (int i = 0; i < NUMBER; i++) {
            points[i].move(dx, dy);
        }

    }

    /**
     * Масштабирование фигуры.
     * 
     * @param k
     *            коэффициент масштабирования.
     */
    @Override
    public void scale(final double k) {

        for (int i = 1; i < NUMBER; i++) {
            points[i].move(-points[0].getX(), -points[0].getY());
        }

        for (int i = 1; i < NUMBER; i++) {
            double newX = points[i].getX() * k;
            double newY = points[i].getY() * k;
            points[i].setX(newX);
            points[i].setY(newY);
        }

        for (int i = 1; i < NUMBER; i++) {
            points[i].move(points[0].getX(), points[0].getY());
        }

    }

}
