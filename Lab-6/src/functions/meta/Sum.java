package functions.meta;

import functions.Function;

/**
 * Класс - Сумма двух функций.
 *
 * @author Aviatort010
 * @see functions.Function
 */
public class Sum implements Function {
    Function a;
    Function b;
    double leftBorder = 0;
    double rightBorder = 0;

    /**
     * Конструктор суммы двух функций.
     *
     * @param a первая функция.
     * @param b вторая функция.
     */
    public Sum(Function a, Function b) {
        this.a = a;
        this.b = b;
        leftBorder = Math.max(a.getLeftDomainBorder(), b.getLeftDomainBorder());
        rightBorder = Math.min(a.getRightDomainBorder(), b.getRightDomainBorder());
    }

    /**
     * Возвращает минимальное допустимое значение суммы.
     *
     * @return max(funcA.min, funcB.min)
     */
    public double getLeftDomainBorder() {
        return leftBorder;
    }

    /**
     * Возвращает максимальное допустимое значение суммы.
     *
     * @return min(funcA.max, funcB.max)
     */
    public double getRightDomainBorder() {
        return rightBorder;
    }

    /**
     * Возвращает сумму двух функций.
     *
     * @param x аргумент двух функций
     * @return funcA(x) + funcB(x)
     */
    public double getFunctionValue(double x) {
        return a.getFunctionValue(x) + b.getFunctionValue(x);
    }
}
