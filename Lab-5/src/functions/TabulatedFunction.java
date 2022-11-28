package functions;

/**
 * Интерфейс клссов Массивов точек табулированной функции.
 *
 * @author Aviatort010
 */
public interface TabulatedFunction extends Function, Cloneable {
    /**
     * Возвращает количество точек.
     *
     * @return число точек.
     */
    int getPointsCount();

    /**
     * Возвращает Точку функции.
     *
     * @param index индекс точки.
     * @return точка функции.
     */
    FunctionPoint getPoint(int index);

    /**
     * Заменяет точку на новую.
     *
     * @param index    индекс заменяемой точки.
     * @param newPoint новая точка.
     * @throws InappropriateFunctionPointException
     */
    void setPoint(int index, FunctionPoint newPoint) throws InappropriateFunctionPointException;

    /**
     * Возвращает значение Абсциссы точки.
     *
     * @param index индекс точки.
     * @return абсцисса точки.
     * @throws FunctionPointIndexOutOfBoundsException
     */
    double getPointX(int index) throws FunctionPointIndexOutOfBoundsException;

    /**
     * Изменяет абсциссу точки на данную.
     *
     * @param index индекс точки.
     * @param x     новая абсцисса.
     * @throws InappropriateFunctionPointException
     */
    void setPointX(int index, double x) throws InappropriateFunctionPointException;

    /**
     * Возвращает значение Ординаты точки.
     *
     * @param index индекс точки.
     * @return ордината.
     */
    double getPointY(int index);

    /**
     * Изменяет Ординату точки на данную.
     *
     * @param index индекс точки.
     * @param y     новая ордината.
     */
    void setPointY(int index, double y) throws InappropriateFunctionPointException;

    /**
     * Удаляет точку из списка.
     *
     * @param index индекс удаляемой точки.
     */
    void deletePoint(int index);

    /**
     * Добавляет точку к списку и сортирует его.
     *
     * @param newPoint новая точка.
     * @throws InappropriateFunctionPointException
     */
    void addPoint(FunctionPoint newPoint) throws InappropriateFunctionPointException;

    /**
     * Перегрузка клонирования массива точек.
     *
     * @return клон исходного массива.
     * @throws CloneNotSupportedException
     */
    Object clone() throws CloneNotSupportedException;
}