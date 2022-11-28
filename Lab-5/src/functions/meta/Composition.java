package functions.meta;

import functions.Function;

/**
 * Класс - Композиция функций.
 *
 * @author Aviatort010
 * @see functions.Function
 */
public class Composition implements Function {
    /**
     * Внешняя функция.
     */
    Function a;
    /**
     * Внутренняя функция.
     */
    Function b;

    /**
     * Конструктор композиции двух функций.
     *
     * @param a Внещняя функция.
     * @param b Внутренняя функция.
     */
    public Composition(Function a, Function b) {
        this.a = a;
        this.b = b;
    }

    /**
     * Возвращает минимальное допустимое значение композиции.
     *
     * @return минимальное значение внешней функции.
     */
    public double getLeftDomainBorder() {
        return a.getLeftDomainBorder();
    }

    /**
     * Возвращает максимальное допустимое значение композиции.
     *
     * @return максимальное значение внешней функции.
     */
    public double getRightDomainBorder() {
        return a.getRightDomainBorder();
    }

    /**
     * Возвращает значение композиции двух функций.
     *
     * @param x аргумент внутренней функции.
     * @return funcA(funcB ( x))
     */
    public double getFunctionValue(double x) {
        return a.getFunctionValue(b.getFunctionValue(x));
    }
}
