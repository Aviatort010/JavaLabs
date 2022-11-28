package functions;

/**
 * Интерфейс функций.
 *
 * @author Aviatort010
 */
public interface Function {
    /**
     * Возвращает левую границу области определения функции.
     *
     * @return минимальное значение абсциссы функции.
     */
    double getLeftDomainBorder();

    /**
     * Возвращает правую границу области определения функции.
     *
     * @return максимальное значение абсциссы функции.
     */
    double getRightDomainBorder();

    /**
     * Возвращает значение функции в данной точке.
     *
     * @param x аргумент функции.
     * @return func(x)
     */
    double getFunctionValue(double x);
}
