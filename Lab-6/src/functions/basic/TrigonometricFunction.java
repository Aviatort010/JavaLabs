package functions.basic;

import functions.Function;

/**
 * Класс описывающий Тригонометрические функции.
 *
 * @author Aviatort010
 */
public class TrigonometricFunction implements Function {
    /**
     * Возвращает минимально возможное значение тригонометрической функции.
     *
     * @return Отрицательная Бесконечность.
     */
    public double getLeftDomainBorder() {
        return Double.NEGATIVE_INFINITY;
    }

    /**
     * Возвращает максимально возможное значение тригонометрической функции.
     *
     * @return Положительная Бесконечность.
     */
    public double getRightDomainBorder() {
        return Double.POSITIVE_INFINITY;
    }

    /**
     * Возвращает значение функции в данной точке.
     * Нужна только как шаблон дочерних функций.
     *
     * @param x точка.
     * @return 0
     */
    public double getFunctionValue(double x) {
        return 0;
    }
}

