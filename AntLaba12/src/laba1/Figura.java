package laba1;

/**
 * @author zinchenko
 */

public abstract class Figura {

    /**
     * Отрисовка фигуры.
     */
    public abstract void print();

    /**
     * Перемещение фигуры.
     * 
     * @param dx
     *            величина смещения вдольоси Х.
     * @param dy
     *            величина смещения вдольоси Y.
     */

    public abstract void move(double dx, double dy);

    /**
     * Масштабирование фигуры.
     * 
     * @param k
     *            коэффициент масштабирования.
     */
    public abstract void scale(double k);

    /**
     * Вычисление длины отрезка по двум точкам.
     * 
     * @param p1
     *            первая точка отрезка.
     * @param p2
     *            вторая точка отрезка.
     * @return длина отрезка.
     */
    public static double countSizeSide(final Point p1, final Point p2) {

        double size = 0;

        double dx = Math.abs(p1.getX()) - Math.abs(p2.getX());
        dx = Math.abs(dx);

        double dy = Math.abs(p1.getY()) - Math.abs(p2.getY());
        dy = Math.abs(dy);

        size = Math.pow(dx, 2) + Math.pow(dy, 2);
        size = Math.sqrt(size);

        return size;
    }

}
