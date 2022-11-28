package functions.basic;


/**
 * Класс - функция Синуса.
 *
 * @author Aviatort010
 * @see functions.basic.TrigonometricFunction
 */
@SuppressWarnings("unused")
public class Sin extends TrigonometricFunction {
    /**
     * Возвращает значение синуса в радианах в данной точке.
     *
     * @param x угол в радианах.
     * @return синус угла.
     */
    public double getFunctionValue(double x) {
        return Math.sin(x);
    }
}
