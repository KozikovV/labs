package laba1;

/**
 * Класс для работы с матрицами.
 * 
 * @author zinchenko
 * 
 */
public class Matrix {

    private double[][] m;

    /**
     * Создание новой матрицы на основе двухмерного массива.
     * Строки массива должны быть одинаковой длинны.
     * 
     * @param m
     *            двухмерный масив.
     */
    public Matrix(final double[][] m) {
        if (!checkNewMatrix(m)) {
            throw new IllegalArgumentException();
        }

        this.m = m;
    }

    /**
     * Суммирование матриц.
     * 
     * @param om
     *            добавляющаяся мтрица
     */
    public void adding(Matrix om) {
        if (!checkForAdding(om)) {
            throw new IllegalArgumentException();
        }

        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[0].length; j++) {
                m[i][j] += om.m[i][j];
            }
        }

    }

    /**
     * Произведение текущей матрицы на матрицу передаваемую в качестве
     * параметра.
     * 
     * @param om
     *            матрица для произведения.
     * @return новая матрица. Результат произведения.
     */
    public Matrix product(Matrix om) {
        if (!checkForProduct(om)) {
            throw new IllegalArgumentException();
        }

        double[][] d = new double[m.length][];
        for (int i = 0; i < d.length; i++) {
            d[i] = new double[om.m[0].length];
        }

        Matrix res = new Matrix(d);

        for (int i = 0; i < res.m.length; i++) {
            for (int j = 0; j < res.m[i].length; j++) {
                res.m[i][j] = countC(i, j, om);
            }
        }

        return res;
    }

    /**
     * Транспонирование.
     * 
     * @return новая транспонированная матрица.
     */
    public Matrix transp() {

        double[][] d = new double[m[0].length][];
        for (int i = 0; i < d.length; i++) {
            d[i] = new double[m.length];
        }

        Matrix tm = new Matrix(d);

        for (int i = 0; i < tm.m.length; i++) {
            for (int j = 0; j < tm.m[0].length; j++) {
                tm.m[i][j] = m[j][i];
            }
        }

        return tm;
    }

    /**
     * Вычисляется сумма произведений по строке текущей матрицы и столбцу другой
     * матрецы.
     * 
     * @param i
     *            текущий номер стрки.
     * @param j
     *            текущий номер столбца.
     * @param om
     *            матрица на которую умножается текущая матрица
     * @return сумма произведений
     */
    private double countC(int i, int j, final Matrix om) {
        double c = 0;
        for (int k = 0; k < m[0].length; k++) {
            c += m[i][k] * om.m[k][j];

        }
        return c;
    }

    /**
     * Проверка правильности матрицы передаваемой в аргумент для вычисления
     * суммы матриц.
     * 
     * @param om
     *            матрица.
     * @return {@code true} если проверка прошла успешно, {@code false} в
     *         противном случае.
     */
    private boolean checkForAdding(final Matrix om) {
        if (this.m.length != om.m.length) {
            return false;
        }

        for (int i = 0; i < m.length; i++) {
            if (m[i].length != om.m[i].length) {
                return false;
            }
        }
        return true;
    }

    /**
     * Проверка правильности матрицы передаваемой в аргумент для вычисления
     * произведения матриц.
     * 
     * @param om
     *            матрица.
     * @return {@code true} если проверка прошла успешно, {@code false} в
     *         противном случае.
     */
    private boolean checkForProduct(final Matrix om) {
        int nColumnFirst = m[0].length;
        int nRowsSecond = om.m.length;

        if (nColumnFirst != nRowsSecond) {
            return false;
        }

        return true;
    }

    /**
     * Проверка правильности двухмерного массива на основании которого создается
     * новая матрица.
     * 
     * @param d
     *            двухмерный масив.
     * @return {@code true} если проверка прошла успешно, {@code false} в
     *         противном случае.
     */
    private boolean checkNewMatrix(final double[][] d) {

        int nColumn = d[0].length;
        for (int i = 1; i < d.length; i++) {
            if (d[i].length != nColumn) {
                return false;
            }
        }

        return true;
    }

}
