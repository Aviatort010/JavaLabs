package docFunctions;

import functions.*;

/**
 * Документ с Табулированной функцией.
 *
 * @author Aviatort010
 * @see functions.TabulatedFunction
 */
@SuppressWarnings("unused")
public class TabulatedFunctionDoc implements TabulatedFunction{
//========================================[Поля класса]========================================
    /**
     * Текущая Табулированная функция.
     */
    private TabulatedFunction currentTF;
    /**
     * Имя открытого файла документа.
     */
    private String openedFilename = "";
    /**
     * Флажок - изменился ли документ с последнего сохранения?
     */
    private boolean modified;
    /**
     * Флажок - назначено ли для документа имя файла?
     */
    private boolean fileNameAssigned;

//==============================[public методы]==============================

    /**
     * Заменяет объект табулированной функции на новый с указанными параметрами.
     *
     * @param leftX левая грань абсциссы функции.
     * @param rightX правая грань абсциссы функции.
     * @param pointsCount количество точек.
     */
    public void newFunction(double leftX, double rightX, int pointsCount){}

    /**
     * Заменяет объект табулированной функции на новый,
     * полученный путём табулирования указанной функции с указанными параметрами.
     *
     * @param function исходная функция.
     * @param leftX левая грань абсциссы функции.
     * @param rightX правая грань абсциссы функции.
     * @param pointsCount количество точек.
     */
    public void tabulateFunction(Function function, double leftX, double rightX, int pointsCount) {}

    /**
     * Сохраняет текущую табулированную функцию в формате JSON.
     *
     * @param fileName имя файла для сохранения.
     */
    public void saveFunctionAs(String fileName) {}

    /**
     * Загружает из файла с указанным именем табулированную функцию в формате JSON.
     *
     * @param fileName имя файла для загрузки.
     */
    public void loadFunction(String fileName) {}

    /**
     * Сохраняет текущую табулированную функцию в используемый текстовый файл в формате JSON.
     */
    public void saveFunction() {}

    //TODO: Объект, вместо того, чтобы самому, например, вычислять значение функции,
    // будет заставлять это делать хранящийся в документе объект табулированной функции.
    // В его методах просто будут вызываться методы объекта табулированной функции,
    // хранящейся в документе

    public double getLeftDomainBorder() {
        return 0;
    }

    public double getRightDomainBorder() {
        return 0;
    }

    public double getFunctionValue(double x) {
        return 0;
    }

    public int getPointsCount() {
        return 0;
    }

    public FunctionPoint getPoint(int index) {
        return null;
    }

    public void setPoint(int index, FunctionPoint newPoint) throws InappropriateFunctionPointException {

    }

    public double getPointX(int index) throws FunctionPointIndexOutOfBoundsException {
        return 0;
    }

    public void setPointX(int index, double x) throws InappropriateFunctionPointException {

    }

    public double getPointY(int index) {
        return 0;
    }

    public void setPointY(int index, double y) throws InappropriateFunctionPointException {

    }

    public void deletePoint(int index) {

    }

    public void addPoint(FunctionPoint newPoint) throws InappropriateFunctionPointException {

    }

    public Object clone() throws CloneNotSupportedException {
        return null;
    }
}
