package functions.basic;

/**
 * Класс - функция Косинуса.
 *
 * @author Aviatort010
 * @see functions.basic.TrigonometricFunction
 */
public class Cos extends TrigonometricFunction {
    /**
     * Возвращает значение косинуса в радианах в данной точке.
     *
     * @param x угол в радианах.
     * @return косинус угла.
     */
    public double getFunctionValue(double x) {
        return Math.cos(x);
    }
}
