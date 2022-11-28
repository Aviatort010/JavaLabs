package functions;

import functions.meta.*;

/**
 * Класс описывающий функции взаимодействия между функциями.
 *
 * @author Aviatort010
 */
@SuppressWarnings("unused")
public class Functions {
//========================================[Конструкторы]========================================
    // private не позволит создать объекты класса вне самого класса.

    /**
     * Конструктор по умолчанию.
     */
    private Functions() {
    }

//==============================[public функции]==============================

    /**
     * Смещение функции.
     *
     * @param f      функция.
     * @param shiftX смещение по X.
     * @param shiftY смещение по Y.
     * @return смещённая функция.
     */
    public static Function shift(Function f, double shiftX, double shiftY) {
        return new Shift(f, shiftX, shiftY);
    }

    /**
     * Масштабирование функции.
     *
     * @param f      функция.
     * @param scaleX масштабирование по Х.
     * @param scaleY масштабирование по У.
     * @return масштабированная функция.
     */
    public static Function scale(Function f, double scaleX, double scaleY) {
        return new Scale(f, scaleX, scaleY);
    }

    /**
     * Возведение функции в степень.
     *
     * @param f     функция.
     * @param power степень-число.
     * @return функция в степени.
     */
    public static Function power(Function f, double power) {
        return new Power(f, power);
    }

    /**
     * Сумма функций.
     *
     * @param f1 функция А.
     * @param f2 функция B.
     * @return (А + В).
     */
    public static Function sum(Function f1, Function f2) {
        return new Sum(f1, f2);
    }

    /**
     * Произведение функций.
     *
     * @param f1 функция А.
     * @param f2 функция В.
     * @return (А + В).
     */
    public static Function mult(Function f1, Function f2) {
        return new Mult(f1, f2);
    }

    /**
     * Композиция функций.
     *
     * @param f1 внешняя функция.
     * @param f2 внутренняя функция.
     * @return f1(f2 ()).
     */
    public static Function composition(Function f1, Function f2) {
        return new Composition(f1, f2);
    }
}
