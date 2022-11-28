package functions.meta;

import functions.Function;


/**
 * Класс - Возведение функции в степень.
 *
 * @author Aviatort010
 * @see functions.Function
 */
public class Power implements Function {
    Function a;
    double b;

    /**
     * Конструктор возведения в степень.
     *
     * @param a функция.
     * @param b степень-число.
     */
    public Power(Function a, double b) {
        this.a = a;
        this.b = b;
    }

    /**
     * Возвращает минимальное допустимое значение функции в степени.
     *
     * @return funcA.min().
     */
    public double getLeftDomainBorder() {
        return a.getLeftDomainBorder();
    }

    /**
     * Возвращает максимально допустимое значение функции в степени.
     *
     * @return funcA.max().
     */
    public double getRightDomainBorder() {
        return a.getRightDomainBorder();
    }

    /**
     * Возвращает значение функции возведённое в степень.
     *
     * @param x аргумент функции.
     * @return funcA(x)^power.
     */
    public double getFunctionValue(double x) {
        return Math.pow(a.getFunctionValue(x), b);
    }
}
