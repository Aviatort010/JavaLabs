package functions.basic;

/**
 * Класс - функция Тангенса.
 *
 * @author Aviatort010
 * @see functions.basic.TrigonometricFunction
 */
@SuppressWarnings("unused")
public class Tan extends TrigonometricFunction {
    /**
     * Возвращает значение тангенса в радианах в данной точке.
     *
     * @param x угол в радианах.
     * @return тангенс угла.
     */
    public double getFunctionValue(double x) {
        return Math.tan(x);
    }
}
