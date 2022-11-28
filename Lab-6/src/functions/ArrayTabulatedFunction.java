package functions;

import java.util.Arrays;

import static java.lang.Math.abs;

/**
 * Класс - Массив точек табулированной функции.
 *
 * @author Aviatort010
 * @see functions.TabulatedFunction
 */
@SuppressWarnings("unused")
public class ArrayTabulatedFunction implements TabulatedFunction {
//========================================[Поля класса]========================================
    private FunctionPoint[] pointArray;
    private int arrLen = 1; // Размер памяти массва
    private int pointsCount = 0;    // Количество точек массива

//========================================[Конструкторы]========================================

    /**
     * Конструктор из левой и правой границ, с количеством точек.
     *
     * @param leftX       левая граница.
     * @param rightX      правая граница.
     * @param pointsCount количество точек.
     * @throws IllegalArgumentException
     */
    public ArrayTabulatedFunction(double leftX, double rightX, int pointsCount) throws IllegalArgumentException {
        if (leftX >= rightX || pointsCount < 2) throw new IllegalArgumentException();

        while (arrLen < pointsCount) arrLen *= 2;
        pointArray = new FunctionPoint[arrLen];
        double step = (rightX - leftX + 1) / pointsCount;
        for (int i = 0; i < pointsCount; ++i) {
            pointArray[i] = new FunctionPoint((leftX + (i * step)), 0);
        }
    }

    /**
     * Конструктор из левой и правой границ, с массивом точек аргумента.
     *
     * @param leftX  левая граница.
     * @param rightX правая граница.
     * @param values массив аргументов функции.
     * @throws IllegalArgumentException
     */
    public ArrayTabulatedFunction(double leftX, double rightX, double[] values) throws IllegalArgumentException {
        if (leftX >= rightX || values.length < 2) throw new IllegalArgumentException();

        pointsCount = values.length;
        while (arrLen < values.length) arrLen *= 2;
        pointArray = new FunctionPoint[arrLen];
        double step = (rightX - leftX + 1) / values.length;
        for (int i = 0; i < values.length; ++i) {
            pointArray[i] = new FunctionPoint((leftX + (i * step)), values[i]);
        }
    }

    /**
     * Конструктор из массива точек функции.
     *
     * @param fPoints массив точек функции.
     * @throws IllegalArgumentException
     */
    public ArrayTabulatedFunction(FunctionPoint[] fPoints) throws IllegalArgumentException {
        if (fPoints.length < 2) throw new IllegalArgumentException();
        for (int i = 0; i < fPoints.length - 1; ++i) {
            if (fPoints[i].getX() >= fPoints[i + 1].getX()) throw new IllegalArgumentException();
        }

        pointsCount = fPoints.length;
        while (arrLen < fPoints.length) arrLen *= 2;
        pointArray = new FunctionPoint[arrLen];
        for (int i = 0; i < fPoints.length; ++i) {
            pointArray[i] = new FunctionPoint(fPoints[i]);
        }
    }

//==============================[private методы]==============================

    /**
     * Сортирует точки функции по абсциссам.
     *
     * @param beginIndex левая граница сортируемого списка.
     * @param endIndex   правая граница сортируемого списка.
     */
    private void sortXPointArray(int beginIndex, int endIndex) {   // Bubble sort
        if ((pointsCount > 1) && (endIndex - beginIndex > 1)) {
            boolean sorted = false;
            FunctionPoint buffer;
            while (!sorted) {
                sorted = true;
                for (int i = beginIndex; i < endIndex - 1; ++i) {
                    if (pointArray[i].getX() > pointArray[i + 1].getX()) {
                        buffer = pointArray[i];
                        pointArray[i] = pointArray[i + 1];
                        pointArray[i + 1] = buffer;
                        sorted = false;
                    }
                }
            }
        }
    }

//==============================[public методы]==============================

    /**
     * Возвращает левую границу области определения функции.
     *
     * @return минимальное значение абсциссы функции.
     */
    public double getLeftDomainBorder() {
        return pointArray[0].getX();
    }

    /**
     * Возвращает правую границу области определения функции.
     *
     * @return максимальное значение абсциссы функции.
     */
    public double getRightDomainBorder() {
        return pointArray[pointsCount - 1].getX();
    }

    /**
     * Возвращает значение функции в данной точке.
     * Вернёт следующее, если аргумент:
     * <ul><li>Выходящий за область определения функции - NaN.
     * <li>Равен абсциссе одной из точек - Значение её Ординаты.
     * <li>Лежит между двумя точками - Значение на прямой между этими точками в данной абсциссе.</ul>
     *
     * @param x аргумент функции.
     * @return func(x)
     */
    public double getFunctionValue(double x) {
        double epsilon = 0.000001;   // Просто нуль для С++ программистов, на всякий пожарный случай.

        if ((x < pointArray[0].getX()) || (pointArray[pointsCount - 1].getX() < x)) return Double.NaN;

        for (int i = 0; i < pointsCount; ++i) {
            if (abs(pointArray[i].getX() - x) < epsilon) return pointArray[i].getY();
        }

        for (int i = 1; i < pointsCount; ++i) {
            if ((pointArray[i - 1].getX() < x) && (x < pointArray[i].getX()))
                return ((x - pointArray[i - 1].getX()) / (pointArray[i].getX() - pointArray[i - 1].getX())) * (pointArray[i].getY() - pointArray[i - 1].getY()) + pointArray[i - 1].getY();
        }

        return Double.NaN;
    }

    /**
     * Возвращает количество точек.
     *
     * @return число точек.
     */
    public int getPointsCount() {
        return pointsCount;
    }

    /**
     * Возвращает список точек функции.
     *
     * @return список точек функции.
     */
    public FunctionPoint[] getPointsArray() {
        return pointArray;
    }

    /**
     * Возвращает Точку функции.
     *
     * @param index индекс точки.
     * @return точка функции.
     */
    public FunctionPoint getPoint(int index) {
        // Если не в границах списка, то Ошибка
        if (index < 0 || pointsCount <= index) throw new FunctionPointIndexOutOfBoundsException();
        return pointArray[index];
    }

    /**
     * Заменяет точку на новую.
     *
     * @param index    индекс заменяемой точки.
     * @param newPoint новая точка.
     * @throws InappropriateFunctionPointException
     */
    public void setPoint(int index, FunctionPoint newPoint) throws InappropriateFunctionPointException {
        /*
        Функция изменяет значение точки из списка
        */
        // Если не в границах списка, то Ошибка
        if (index < 0 || pointsCount <= index) throw new FunctionPointIndexOutOfBoundsException();
        // Нулевая точка (крайняя слева)
        if (index == 0) {
            if (newPoint.getX() < pointArray[1].getX()) pointArray[index] = newPoint;
            else throw new InappropriateFunctionPointException();
            // Последняя точка (крайняя справа)
        } else if (index == pointsCount - 1) {
            if (pointArray[index - 1].getX() < newPoint.getX()) pointArray[index] = newPoint;
            else throw new InappropriateFunctionPointException();
            // Лежащая внутри списка точка
        } else if ((pointArray[index - 1].getX() < newPoint.getX()) && (newPoint.getX() < pointArray[index + 1].getX()))
            pointArray[index] = newPoint;
        else throw new InappropriateFunctionPointException();
    }

    /**
     * Возвращает значение Абсциссы точки.
     *
     * @param index индекс точки.
     * @return абсцисса точки.
     * @throws FunctionPointIndexOutOfBoundsException
     */
    public double getPointX(int index) throws FunctionPointIndexOutOfBoundsException {
        if (0 <= index || index < pointsCount) {
            return pointArray[index].getX();
        } else throw new FunctionPointIndexOutOfBoundsException();
    }

    /**
     * Изменяет абсциссу точки на данную.
     *
     * @param index индекс точки.
     * @param x     новая абсцисса.
     * @throws InappropriateFunctionPointException
     */
    public void setPointX(int index, double x) throws InappropriateFunctionPointException {
        // Если не в границах списка, то Ошибка
        if (index < 0 || pointsCount <= index) throw new FunctionPointIndexOutOfBoundsException();
        // Нулевая точка (крайняя слева)
        if (index == 0) {
            if (x < pointArray[1].getX()) pointArray[index].setX(x);
            else throw new InappropriateFunctionPointException();
            // Последняя точка (крайняя справа)
        } else if (index == pointsCount - 1) {
            if (pointArray[index - 1].getX() < x) pointArray[index].setX(x);
            else throw new InappropriateFunctionPointException();
            // Лежащая внутри списка точка
        } else if ((pointArray[index - 1].getX() < x) && (x < pointArray[index + 1].getX()))
            pointArray[index].setX(x);
        else throw new InappropriateFunctionPointException();
    }

    /**
     * Возвращает значение Ординаты точки.
     *
     * @param index индекс точки.
     * @return ордината.
     */
    public double getPointY(int index) {
        if (0 <= index || index < pointsCount) {
            return pointArray[index].getY();
        } else throw new FunctionPointIndexOutOfBoundsException();
    }

    /**
     * Изменяет Ординату точки на данную.
     *
     * @param index индекс точки.
     * @param y     новая ордината.
     */
    public void setPointY(int index, double y) {
        // Если не в границах списка, то Ошибка
        if (index < 0 || pointsCount <= index) throw new FunctionPointIndexOutOfBoundsException();
        pointArray[index].setY(y);
    }

    /**
     * Удаляет точку из списка.
     *
     * @param index индекс удаляемой точки.
     */
    public void deletePoint(int index) {
        // Если точек в списке меньше трёх - Ошибка
        if (pointsCount < 3) throw new IllegalStateException();
        // Если не в границах списка, то Ошибка
        if (index < 0 || pointsCount <= index) throw new FunctionPointIndexOutOfBoundsException();

        for (int i = index; i < pointsCount - 1; ++i) pointArray[i] = pointArray[i + 1];
        --pointsCount;
    }

    /**
     * Добавляет точку к списку и сортирует его.
     *
     * @param newPoint новая точка.
     * @throws InappropriateFunctionPointException
     */
    public void addPoint(FunctionPoint newPoint) throws InappropriateFunctionPointException {
        for (int i = 0; i < pointsCount; ++i) {
            if (newPoint.getX() == pointArray[i].getX()) throw new InappropriateFunctionPointException();
        }
        ++pointsCount;
        if (pointsCount < arrLen) {
            FunctionPoint[] bufferPointArray = new FunctionPoint[arrLen];
            System.arraycopy(pointArray, 0, bufferPointArray, 0, pointsCount - 1);
            arrLen *= 2;
            pointArray = new FunctionPoint[arrLen];     // Memory lost??
            System.arraycopy(bufferPointArray, 0, pointArray, 0, pointsCount - 1);
        }
        pointArray[pointsCount - 1] = newPoint;
        sortXPointArray(0, pointsCount);
    }

//==============================[Перегруженные методы]==============================

    /**
     * Перегрузка преобразования в строку.
     *
     * @return {(x0, y0), ..., (x, y)}
     */
    @Override
    public String toString() {
        String result = "{";
        for (int i = 0; i < pointsCount - 1; ++i)
            result = result.concat(pointArray[i].toString() + ", ");
        result = result.concat(pointArray[pointsCount - 1] + "}");
        return result;
    }

    /**
     * Перегрузка сравнения на равество.
     *
     * @param o объект для сравнения.
     * @return Array1 =? Array2.
     */
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof TabulatedFunction)) return false;
        if (((TabulatedFunction) o).getPointsCount() != pointsCount) return false;
        if (o instanceof ArrayTabulatedFunction)
            return Arrays.equals(((ArrayTabulatedFunction) o).pointArray, pointArray);
        else {
            for (int i = 0; i < pointsCount; ++i) {
                if (!(((TabulatedFunction) o).getPoint(i).equals(pointArray[i]))) return false;
            }
        }
        return true;
    }

    /**
     * Перегрузка генерации ХешКода.
     * ХешКод - Целочисленный идендификатор объекта в программе.
     *
     * @return ХешКод массива точек функции.
     */
    @Override
    public int hashCode() {
        // Магическая штука - класс Arrays!
        int resultHash = Arrays.hashCode(pointArray);
        // Добавляем к полученному количество точек
        return pointsCount + 31 * resultHash;
    }

    /**
     * Перегрузка клонирования массива точек.
     *
     * @return клон исходного массива.
     * @throws CloneNotSupportedException
     */
    @Override
    public Object clone() throws CloneNotSupportedException {
        ArrayTabulatedFunction clonedArrayTabFunc = (ArrayTabulatedFunction) super.clone();
        // На всякий случай лучше создать абсолютно новый массив.
        clonedArrayTabFunc.pointArray = new FunctionPoint[pointsCount];
        for (int i = 0; i < pointsCount; ++i)
            clonedArrayTabFunc.pointArray[i] = pointArray[i].clone();
        return clonedArrayTabFunc;
    }
}




