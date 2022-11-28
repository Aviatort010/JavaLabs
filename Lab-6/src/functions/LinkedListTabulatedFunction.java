package functions;

import java.util.Arrays;

import static java.lang.Math.abs;

/**
 * Узел двусвязного списка.
 *
 * @author Aviatort010
 */
class FunctionNode {
    //========================================[Поля класса]========================================
    FunctionPoint fpValue;
    FunctionNode back = null;
    FunctionNode next = null;

//========================================[Конструкторы]========================================

    /**
     * Конструктор Узла по умолчанию.
     * Указатели на предыдущий и на следующий элементы равны этому элементу.
     */
    FunctionNode() {
        fpValue = null;
        back = this;
        next = this;
    }

    /**
     * Конструктор Узла из Точки функции.
     * Указатели на предыдущий и на следующий элементы равны этому элементу.
     *
     * @param val точка функции.
     */
    FunctionNode(FunctionPoint val) {
        fpValue = val;
        back = this;
        next = this;
    }
}

/**
 * Циклический двусвязный список.
 *
 * @author Aviatort010
 * @see functions.TabulatedFunction
 */
@SuppressWarnings("unused")
public class LinkedListTabulatedFunction implements TabulatedFunction {
    //========================================[Поля класса]========================================
    FunctionNode head = new FunctionNode();
    private FunctionNode currentNode = head;
    private int pointsCount = 0;

//========================================[Конструкторы]========================================

    /**
     * Конструктор из левой и правой границ, с количеством точек.
     *
     * @param leftX       левая граница.
     * @param rightX      правая граница.
     * @param pointsCount количество точек.
     * @throws IllegalArgumentException
     */
    public LinkedListTabulatedFunction(double leftX, double rightX, int pointsCount) throws IllegalArgumentException {
        if (leftX >= rightX || pointsCount < 2) throw new IllegalArgumentException();

        double step = (rightX - leftX + 1) / pointsCount;
        for (int i = 0; i < pointsCount; ++i) {
            addNodeToTail(new FunctionPoint((leftX + (i * step)), 0));
        }
        this.pointsCount = pointsCount;
    }

    /**
     * Конструктор из левой и правой границ, с массивом точек аргумента.
     *
     * @param leftX  левая граница.
     * @param rightX правая граница.
     * @param values массив аргументов функции.
     * @throws IllegalArgumentException
     */
    public LinkedListTabulatedFunction(double leftX, double rightX, double[] values) throws IllegalArgumentException {
        if (leftX >= rightX || values.length < 2) throw new IllegalArgumentException();

        pointsCount = values.length;
        double step = (rightX - leftX + 1) / values.length;
        for (int i = 0; i < values.length; ++i) {
            addNodeToTail(new FunctionPoint((leftX + (i * step)), values[i]));
        }
    }

    /**
     * Конструктор из массива точек функции.
     *
     * @param fPoints массив точек функции.
     * @throws IllegalArgumentException
     */
    public LinkedListTabulatedFunction(FunctionPoint[] fPoints) throws IllegalArgumentException {
        if (fPoints.length < 2) throw new IllegalArgumentException();
        for (int i = 0; i < fPoints.length - 1; ++i) {
            if (fPoints[i].getX() >= fPoints[i + 1].getX()) throw new IllegalArgumentException();
        }
        pointsCount = fPoints.length;
        for (int i = 0; i < fPoints.length; ++i) {
            addNodeToTail(new FunctionPoint(fPoints[i]));
        }
    }

//==============================[private методы]==============================

    /**
     * Добавляет Точку функции в конец списка.
     *
     * @param val точка функции.
     * @return добавленный узел списка.
     */
    private FunctionNode addNodeToTail(FunctionPoint val) {
        FunctionNode new_node = new FunctionNode(val);
        if (is_empty()) {
            head.next = new_node;
            new_node.next = head;
            head.back = new_node;
            new_node.back = head;
        } else {
            head.back.next = new_node;
            new_node.next = head;
            new_node.back = head.back;
            head.back = new_node;
        }
        return new_node;
    }

    /**
     * Возвращает Узел списка.
     *
     * @param index индекс узла.
     * @return узел.
     */
    private FunctionNode getNodeByIndex(int index) {
        FunctionNode currentNode = head;
        for (int i = 0; i < index + 1; ++i) currentNode = currentNode.next;
        return currentNode;
    }

    /**
     * Добавляет новый узел в список.
     *
     * @param index место в списке.
     * @return новый узел.
     */
    private FunctionNode addNodeByIndex(int index) {
        FunctionNode currentNode = head, newNode = new FunctionNode();
        for (int i = 0; i < index + 1; ++i) currentNode = currentNode.next;
        currentNode.back.next = newNode;
        newNode.next = currentNode;
        newNode.back = currentNode.back;
        currentNode.back = newNode;
        return newNode;
    }

    /**
     * Удаляет узел из списка.
     *
     * @param index индекс узла.
     * @return удалённый узел.
     */
    private FunctionNode deleteNodeByIndex(int index) {
        FunctionNode currentNode = head;
        for (int i = 0; i < index + 1; ++i) currentNode = currentNode.next;
        currentNode.back.next = currentNode.next;
        currentNode.next.back = currentNode.back;
        return currentNode;
    }

    /**
     * Сортирует точки функции по абсциссам.
     *
     * @param beginIndex левая граница сортируемого списка.
     * @param endIndex   правая граница сортируемого списка.
     */
    private void sortXPointArray(int beginIndex, int endIndex) throws InappropriateFunctionPointException {// Bubble sort
        if ((pointsCount > 1) && (endIndex - beginIndex > 1)) {
            boolean sorted = false;
            FunctionPoint buffer;
            while (!sorted) {
                sorted = true;
                for (int i = beginIndex; i < endIndex - 1; ++i) {
                    if (getPoint(i).getX() > getPoint(i + 1).getX()) {
                        buffer = new FunctionPoint(getPoint(i));
                        setPoint(i, new FunctionPoint(getPointX(i + 1), getPointY(i + 1)));
                        setPoint(i, new FunctionPoint(buffer));
                        sorted = false;
                    }
                }
            }
        }
    }

//==============================[public методы]==============================

    /**
     * Проверка на пустоту списка.
     *
     * @return пуст?
     */
    public boolean is_empty() {
        return head.next == head;
    }

    /**
     * Возвращает левую границу области определения функции.
     *
     * @return минимальное значение абсциссы функции.
     */
    public double getLeftDomainBorder() {
        return head.next.fpValue.getX();
    }

    /**
     * Возвращает правую границу области определения функции.
     *
     * @return максимальное значение абсциссы функции.
     */
    public double getRightDomainBorder() {
        return head.back.fpValue.getX();
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
        double epsilon = 0.000001;   // Просто Ноль для программистов (на всякий пожарный случай)
        // Если выходит за крайние точки функции, то вернёт NaN.
        if ((head.next.fpValue.getX() > x) || (head.back.fpValue.getX() < x)) return Double.NaN;

        FunctionNode currentNode = head;
        for (int i = 0; i < pointsCount; ++i) {
            currentNode = currentNode.next;
            if (abs(currentNode.fpValue.getX() - x) < epsilon) return currentNode.fpValue.getY();
        }

        currentNode = head.next.next;
        for (int i = 1; i < pointsCount; ++i) {
            if ((currentNode.back.fpValue.getX() < x) && (x < currentNode.fpValue.getX()))
                return ((x - currentNode.back.fpValue.getX()) / (currentNode.fpValue.getX() - currentNode.back.fpValue.getX())) * (currentNode.fpValue.getY() - currentNode.back.fpValue.getY()) + currentNode.back.fpValue.getY();
            currentNode = currentNode.next;
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
     * Возвращает Массив точек функции списка.
     *
     * @return массив точек функции.
     */
    public FunctionPoint[] getArray() {
        FunctionPoint[] pointArray = new FunctionPoint[pointsCount];
        currentNode = head.next;
        int i = 0;
        while (currentNode != head) {
            pointArray[i] = currentNode.fpValue;
            currentNode = currentNode.next;
            ++i;
        }
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

        FunctionNode currentNode = head;
        for (int i = 0; i < index + 1; ++i) currentNode = currentNode.next;

        return currentNode.fpValue;
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

        FunctionNode currentNode = head;
        for (int i = 0; i < index + 1; ++i) currentNode = currentNode.next;

        // Нулевая точка (крайняя слева)
        if (index == 0) {
            if (newPoint.getX() < head.next.next.fpValue.getX()) currentNode.fpValue = newPoint;
            else throw new InappropriateFunctionPointException();
            // Последняя точка (крайняя справа)
        } else if (index == pointsCount - 1) {
            if (currentNode.back.fpValue.getX() < newPoint.getX()) currentNode.fpValue = newPoint;
            else throw new InappropriateFunctionPointException();
            // Лежащая внутри списка точка
        } else if ((currentNode.back.fpValue.getX() < newPoint.getX()) && (newPoint.getX() < currentNode.next.fpValue.getX()))
            currentNode.fpValue = newPoint;
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
            return getPoint(index).getX();
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
        /*
        Функция изменяет значение абсциссы точки из списка
        */
        // Если не в границах списка, то Ошибка
        if (index < 0 || pointsCount <= index) throw new FunctionPointIndexOutOfBoundsException();

        setPoint(index, new FunctionPoint(x, getPoint(index).getY()));
    }

    /**
     * Возвращает значение Ординаты точки.
     *
     * @param index индекс точки.
     * @return ордината.
     */
    public double getPointY(int index) {
        if (0 <= index || index < pointsCount) {
            return getPoint(index).getY();
        } else throw new FunctionPointIndexOutOfBoundsException();
    }

    /**
     * Изменяет Ординату точки на данную.
     *
     * @param index индекс точки.
     * @param y     новая ордината.
     */
    public void setPointY(int index, double y) throws InappropriateFunctionPointException {
        // Если не в границах списка, то Ошибка
        if (index < 0 || pointsCount <= index) throw new FunctionPointIndexOutOfBoundsException();

        setPoint(index, new FunctionPoint(getPoint(index).getX(), y));
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

        deleteNodeByIndex(index);
    }

    /**
     * Добавляет точку к списку и сортирует его.
     *
     * @param newPoint новая точка.
     * @throws InappropriateFunctionPointException
     */
    public void addPoint(FunctionPoint newPoint) throws InappropriateFunctionPointException {
        for (int i = 0; i < pointsCount; ++i) {
            if (newPoint.getX() == getPointX(i)) throw new InappropriateFunctionPointException();
        }
        addNodeToTail(newPoint);
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
        currentNode = head.next;
        while (currentNode != head.back) {
            result = result.concat(currentNode.fpValue.toString() + ", ");
            currentNode = currentNode.next;
        }
        result = result.concat(head.back.fpValue.toString() + "}");
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
        if (o instanceof LinkedListTabulatedFunction) {
            currentNode = head.next;
            ((LinkedListTabulatedFunction) o).currentNode = ((LinkedListTabulatedFunction) o).head.next;
            while (currentNode != head) {
                if (!(((LinkedListTabulatedFunction) o).currentNode.fpValue.equals(currentNode.fpValue))) return false;
                currentNode = currentNode.next;
                ((LinkedListTabulatedFunction) o).currentNode = ((LinkedListTabulatedFunction) o).currentNode.next;
            }
        } else {
            for (int i = 0; i < pointsCount; ++i)
                if (!(((TabulatedFunction) o).getPoint(i).equals(getPoint(i)))) return false;

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
        // сводим задачу к предыдущей (из ArrayTabulatedList) при помощи getArray().
        int resultHash = Arrays.hashCode(getArray());
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
        FunctionPoint[] clonedFuncPointArray = new FunctionPoint[pointsCount];
        for (int i = 0; i < pointsCount; ++i)
            clonedFuncPointArray[i] = getPoint(i).clone();
        return new LinkedListTabulatedFunction(clonedFuncPointArray);
    }
}