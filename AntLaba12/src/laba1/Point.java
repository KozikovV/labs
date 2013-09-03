package laba1;

/**
 * 
 * @author zinchenko
 * 
 */
public class Point {

    private double x;
    private double y;

    /**
     * Создание новой точки в начале системы координат.
     */
    public Point() {
        super();
    }

    /**
     * Создане новой точки с заданными координатами.
     * 
     * @param x
     *            координата х.
     * @param y
     *            координата y.
     */
    public Point(final double x, final double y) {
        super();
        this.x = x;
        this.y = y;
    }

    /**
     * Получение х.
     * 
     * @return x координата х
     */
    public double getX() {
        return x;
    }

    /**
     * Установка х.
     * 
     * @param x
     *            координата х
     */
    public void setX(final double x) {
        this.x = x;
    }

    /**
     * Получение y.
     * 
     * @return x координата y
     */
    public double getY() {
        return y;
    }

    /**
     * Установка y.
     * 
     * @param y
     *            координата y
     */
    public void setY(final double y) {
        this.y = y;
    }

    /**
     * Перемещение точки.
     * 
     * @param dx
     *            значение смещения вдоль оси х
     * @param dy
     *            значение смещения вдоль оси y
     */
    public void move(final double dx, final double dy) {
        x += dx;
        y += dy;
    }

    /**
     * Представление точки в виде строки.
     * 
     * @return строку
     */
    @Override
    public String toString() {
        // return "(" + String.format(arg0, arg1) + ", " + y + ")";
        return String.format("(%.2f, %.2f)", x, y);
    }

}
