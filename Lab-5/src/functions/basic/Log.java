package functions.basic;

import functions.Function;


/**
 * Класс - функция Логарифма.
 *
 * @author Aviatort010
 * @see functions.Function
 */
@SuppressWarnings("unused")
public class Log implements Function {
    /**
     * Основание Логарифма.
     */
    double base;

    /**
     * Конструктор Логарифма по основанию.
     *
     * @param base основание логарифма.
     */
    public Log(double base) {
        this.base = base;
    }

    /**
     * Возвращает наименьшее значение области определения Логарифма.
     *
     * @return 0.
     */
    public double getLeftDomainBorder() {
        return 0;
    }

    /**
     * Возвращает наибольшее значение области определения Логарифма.
     *
     * @return Положительная Бесконечность.
     */
    public double getRightDomainBorder() {
        return Double.POSITIVE_INFINITY;
    }

    /**
     * Возвращает значение Логарифма в данной точке.
     *
     * @param x аргумент функции.
     * @return значение Логарифма.
     */
    public double getFunctionValue(double x) {
        return Math.log(x) / Math.log(base);
    }
}
