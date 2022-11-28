package functions.meta;

import functions.Function;

/**
 * Класс - Смещение функции.
 *
 * @author Aviatort010
 * @see functions.Function
 */
public class Shift implements Function {
    Function a;
    double x = 0, y = 0;

    /**
     * Конструктор смещения функции.
     *
     * @param a смещаемая функция.
     * @param x смещение по оси X.
     * @param y смещение по оси Y.
     */
    public Shift(Function a, double x, double y) {
        this.a = a;
        this.x = x;
        this.y = y;
    }

    /**
     * Возвращает минимально допустимое значение смещённой функции.
     *
     * @return shiftX + funcA.min().
     */
    public double getLeftDomainBorder() {
        return x + a.getLeftDomainBorder();
    }

    /**
     * Возвращает максимально допустимое значение смещённой функции.
     *
     * @return shiftX + funcA.max().
     */
    public double getRightDomainBorder() {
        return x + a.getRightDomainBorder();
    }

    /**
     * Возвращает значение смещённой функции.
     *
     * @param x аргумент функции.
     * @return shiftY + funcA(x).
     */
    public double getFunctionValue(double x) {
        return y + a.getFunctionValue(x);
    }
}
