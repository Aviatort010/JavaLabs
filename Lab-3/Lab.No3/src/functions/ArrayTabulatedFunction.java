package functions;

import static java.lang.Math.abs;

public class ArrayTabulatedFunction implements TabulatedFunction{
    private FunctionPoint[] pointArray;
    private int arrLen = 1;
    private int pointsCount = 0;

    public ArrayTabulatedFunction(double leftX, double rightX, int pointsCount) throws IllegalArgumentException {
        if (leftX >= rightX || pointsCount < 2) throw new IllegalArgumentException();

        while (arrLen < pointsCount) arrLen *= 2;
        pointArray = new FunctionPoint[arrLen];
        double step = (rightX - leftX + 1) / pointsCount;
        for (int i = 0; i < pointsCount; ++i) {
            pointArray[i] = new FunctionPoint((leftX + (i * step)), 0);
        }
    }

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

    public double getLeftDomainBorder() {
        return pointArray[0].getX();
    }

    public double getRightDomainBorder() {
        return pointArray[pointsCount - 1].getX();
    }

    public double getFunctionValue(double x) {
        double epsilon = 0.000001;   // just null for programmistic double

        if ((pointArray[0].getX() > x) || (pointArray[pointsCount - 1].getX() < x)) return Double.NaN;

        for (int i = 0; i < pointsCount; ++i) {
            if (abs(pointArray[i].getX() - x) < epsilon) return pointArray[i].getY();
        }

        for (int i = 1; i < pointsCount; ++i) {
            if ((pointArray[i - 1].getX() < x) && (x < pointArray[i].getX()))
                return ((x - pointArray[i - 1].getX()) / (pointArray[i].getX() - pointArray[i - 1].getX())) * (pointArray[i].getY() - pointArray[i - 1].getY()) + pointArray[i - 1].getY();
        }

        return Double.NaN;
    }

    public int getPointsCount() {
        return pointsCount;
    }

    public FunctionPoint getPoint(int index) {
        // Если не в границах списка, то Ошибка
        if (index < 0 || pointsCount <= index) throw new FunctionPointIndexOutOfBoundsException();
        return pointArray[index];
    }

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

    public double getPointX(int index) throws FunctionPointIndexOutOfBoundsException {
        if (0 <= index || index < pointsCount) {
            return pointArray[index].getX();
        } else throw new FunctionPointIndexOutOfBoundsException();
    }

    public void setPointX(int index, double x) throws InappropriateFunctionPointException {
        /*
        Функция изменяет значение абсциссы точки из списка
        */
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

    public double getPointY(int index) {
        if (0 <= index || index < pointsCount) {
            return pointArray[index].getY();
        } else throw new FunctionPointIndexOutOfBoundsException();
    }

    public void setPointY(int index, double y) {
        // Если не в границах списка, то Ошибка
        if (index < 0 || pointsCount <= index) throw new FunctionPointIndexOutOfBoundsException();
        pointArray[index].setY(y);
    }

    public void sortXPointArray(int beginIndex, int endIndex) {   // Bubble sort
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

    public void deletePoint(int index) {
        // Если точек в списке меньше трёх - Ошибка
        if (pointsCount < 3) throw new IllegalStateException();
        // Если не в границах списка, то Ошибка
        if (index < 0 || pointsCount <= index) throw new FunctionPointIndexOutOfBoundsException();

        for (int i = index; i < pointsCount - 1; ++i) pointArray[i] = pointArray[i + 1];
        --pointsCount;
    }

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
}




