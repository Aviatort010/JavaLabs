package functions.meta;

import functions.Function;

/**
 * Класс - Масштабирование функции.
 *
 * @author Aviatort010
 * @see functions.Function
 */
public class Scale implements Function {
    Function a;
    double x = 1, y = 1;

    /**
     * Конструктор масштабирования функции.
     *
     * @param a масштаюируемая функция.
     * @param x масштаб по оси X.
     * @param y масштаб по оси Y.
     */
    public Scale(Function a, double x, double y) {
        this.a = a;
        this.x = x;
        this.y = y;
    }

    /**
     * Возвращает минимально допустимое значение масштабированной функции.
     *
     * @return scaleX * funcA.min().
     */
    public double getLeftDomainBorder() {
        return x * a.getLeftDomainBorder();
    }

    /**
     * Возвращает максимально допустимое значение масштабированной функции.
     *
     * @return scaleX * funcA.max().
     */
    public double getRightDomainBorder() {
        return x * a.getRightDomainBorder();
    }

    /**
     * Возвращает значение масштабированной функции.
     *
     * @param x аргумент функции.
     * @return scaleY * funcA(x).
     */
    public double getFunctionValue(double x) {
        return y * a.getFunctionValue(x);
    }
}
