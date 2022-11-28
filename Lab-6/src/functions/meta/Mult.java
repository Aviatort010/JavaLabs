package functions.meta;

import functions.Function;

/**
 * Класс - Произведение двух функций.
 *
 * @author Aviatort010
 * @see functions.Function
 */
public class Mult implements Function {
    Function a;
    Function b;
    double leftBorder = 0;
    double rightBorder = 0;

    /**
     * Конструктор произведения двух функций.
     *
     * @param a первая функция.
     * @param b вторая функция.
     */
    public Mult(Function a, Function b) {
        this.a = a;
        this.b = b;
        leftBorder = Math.max(a.getLeftDomainBorder(), b.getLeftDomainBorder());
        rightBorder = Math.min(a.getRightDomainBorder(), b.getRightDomainBorder());
    }

    /**
     * Возвращает минимальное допустимое значение произведения.
     *
     * @return max(funcA.min, funcB.min)
     */
    public double getLeftDomainBorder() {
        return leftBorder;
    }

    /**
     * Возвращает максимальное допустимое значение произведения.
     *
     * @return min(funcA.max, funcB.max)
     */
    public double getRightDomainBorder() {
        return rightBorder;
    }

    /**
     * Возвращает произведение двух функций.
     *
     * @param x аргумент двух функций
     * @return funcA(x) * funcB(x)
     */
    public double getFunctionValue(double x) {
        return a.getFunctionValue(x) * b.getFunctionValue(x);
    }
}
