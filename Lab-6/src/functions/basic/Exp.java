package functions.basic;

import functions.Function;

/**
 * Класс - функция Экспоненты.
 *
 * @author Aviatort010
 * @see functions.Function
 */
@SuppressWarnings("unused")
public class Exp implements Function {
    /**
     * Возвращает наименьшее значение области определения Экспоненты.
     *
     * @return минимально возможное значение экспоненты.
     */
    public double getLeftDomainBorder() {
        return Double.MIN_EXPONENT;
    }

    /**
     * Возвращает наибольшее значение области определения Экспоненты.
     *
     * @return максимально возможное значение экспоненты.
     */
    public double getRightDomainBorder() {
        return Double.MAX_EXPONENT;
    }

    /**
     * Возвращает значение экспоненты в данной точке.
     *
     * @param x аргумент функции.
     * @return значение экспоненты.
     */
    public double getFunctionValue(double x) {
        return Math.exp(x);
    }

}
