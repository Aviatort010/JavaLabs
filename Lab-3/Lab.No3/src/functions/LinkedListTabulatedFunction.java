package functions;

import static java.lang.Math.abs;

class FunctionNode{
    FunctionPoint fpValue;
    FunctionNode back = null;
    FunctionNode next = null;
    FunctionNode(){
        fpValue = null;
        back = this;
        next = this;
    }
    FunctionNode(FunctionPoint val){
        fpValue = val;
        back = this;
        next = this;
    }
}

public class LinkedListTabulatedFunction implements TabulatedFunction{
    FunctionNode head = new FunctionNode();

    boolean is_empty() { return head.next == head; }

    public FunctionNode addNodeToTail(FunctionPoint val) {
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

    FunctionNode getNodeByIndex(int index){
        FunctionNode currentNode = head;
        for (int i = 0; i < index + 1; ++i) currentNode = currentNode.next;
        return currentNode;
    }

    FunctionNode addNodeByIndex(int index){
        FunctionNode currentNode = head, newNode = new FunctionNode();
        for (int i = 0; i < index + 1; ++i) currentNode = currentNode.next;
        currentNode.back.next = newNode;
        newNode.next = currentNode;
        newNode.back = currentNode.back;
        currentNode.back = newNode;
        return newNode;
    }

    FunctionNode deleteNodeByIndex(int index) {
        FunctionNode currentNode = head;
        for (int i = 0; i < index + 1; ++i) currentNode = currentNode.next;
        currentNode.back.next = currentNode.next;
        currentNode.next.back = currentNode.back;
        return currentNode;
    }

    // TabulatedFunction code:
    
    private int pointsCount = 0;

    public LinkedListTabulatedFunction(double leftX, double rightX, int pointsCount) throws IllegalArgumentException {
        if (leftX >= rightX || pointsCount < 2) throw new IllegalArgumentException();

        double step = (rightX - leftX + 1) / pointsCount;
        for (int i = 0; i < pointsCount; ++i) {
            addNodeToTail(new FunctionPoint((leftX + (i * step)), 0));
        }
        this.pointsCount = pointsCount;
    }

    public LinkedListTabulatedFunction(double leftX, double rightX, double[] values) throws IllegalArgumentException {
        if (leftX >= rightX || values.length < 2) throw new IllegalArgumentException();

        pointsCount = values.length;
        double step = (rightX - leftX + 1) / values.length;
        for (int i = 0; i < values.length; ++i) {
            addNodeToTail(new FunctionPoint((leftX + (i * step)), values[i]));
        }
    }

    public double getLeftDomainBorder() {
        return head.next.fpValue.getX();
    }

    public double getRightDomainBorder() {
        return head.back.fpValue.getX();
    }

    public double getFunctionValue(double x) {
        double epsilon = 0.000001;   // Просто Ноль для программистов (на всякий пожарный случай)

        if ((head.next.fpValue.getX() > x) || (head.back.fpValue.getX() < x)) return Double.NaN;
        
        FunctionNode currentNode = head;
        for (int i = 0; i < pointsCount; ++i) {
            currentNode = currentNode.next;
            if (abs(currentNode.fpValue.getX() - x) < epsilon) return currentNode.fpValue.getY();
        }

        currentNode = head.next;
        for (int i = 1; i < pointsCount; ++i) {
            if ((currentNode.back.fpValue.getX() < x) && (x < currentNode.fpValue.getX()))
                return ((x - currentNode.back.fpValue.getX()) / (currentNode.fpValue.getX() - currentNode.back.fpValue.getX())) * (currentNode.fpValue.getY() - currentNode.back.fpValue.getY()) + currentNode.back.fpValue.getY();
            currentNode = currentNode.next;
        }

        return Double.NaN;
    }

    public int getPointsCount() {
        return pointsCount;
    }

    public FunctionPoint getPoint(int index) {
        // Если не в границах списка, то Ошибка
        if (index < 0 || pointsCount <= index) throw new FunctionPointIndexOutOfBoundsException();
        
        FunctionNode currentNode = head;
        for (int i = 0; i < index + 1; ++i) currentNode = currentNode.next;
        
        return currentNode.fpValue;
    }

    public void setPoint(int index, FunctionPoint newPoint) throws InappropriateFunctionPointException {
        /*
        Функция изменяет значение точки из списка
        */
        // Если не в границах списка, то Ошибка
        if (index < 0 || pointsCount <= index) throw new FunctionPointIndexOutOfBoundsException();

        FunctionNode currentNode = head;
        for (int i = 0; i < index + 1; ++i) currentNode = currentNode.next;

        // Нулевая точка (крайняя слева)
        if (index == 0){
            if (newPoint.getX() < head.next.next.fpValue.getX()) currentNode.fpValue = newPoint;
            else throw new InappropriateFunctionPointException();
            // Последняя точка (крайняя справа)
        } else if (index == pointsCount - 1){
            if (currentNode.back.fpValue.getX() < newPoint.getX()) currentNode.fpValue = newPoint;
            else throw new InappropriateFunctionPointException();
            // Лежащая внутри списка точка
        } else if ((currentNode.back.fpValue.getX() < newPoint.getX()) && (newPoint.getX() < currentNode.next.fpValue.getX()))
            currentNode.fpValue = newPoint;
        else throw new InappropriateFunctionPointException();
    }

    public double getPointX(int index) throws FunctionPointIndexOutOfBoundsException{
        if (0 <= index || index < pointsCount){
            return getPoint(index).getX();
        } else throw new FunctionPointIndexOutOfBoundsException();
    }

    public void setPointX(int index, double x) throws InappropriateFunctionPointException {
        /*
        Функция изменяет значение абсциссы точки из списка
        */
        // Если не в границах списка, то Ошибка
        if (index < 0 || pointsCount <= index) throw new FunctionPointIndexOutOfBoundsException();

        setPoint(index, new FunctionPoint(x, getPoint(index).getY()));
    }

    public double getPointY(int index) {
        if (0 <= index || index < pointsCount){
            return getPoint(index).getY();
        } else throw new FunctionPointIndexOutOfBoundsException();
    }

    public void setPointY(int index, double y) throws InappropriateFunctionPointException {
        // Если не в границах списка, то Ошибка
        if (index < 0 || pointsCount <= index) throw new FunctionPointIndexOutOfBoundsException();

        setPoint(index, new FunctionPoint(getPoint(index).getX(), y));
    }

    public void sortXPointArray(int beginIndex, int endIndex) throws InappropriateFunctionPointException {// Bubble sort
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

    public void deletePoint(int index) {
        // Если точек в списке меньше трёх - Ошибка
        if (pointsCount < 3) throw new IllegalStateException();
        // Если не в границах списка, то Ошибка
        if (index < 0 || pointsCount <= index) throw new FunctionPointIndexOutOfBoundsException();

        deleteNodeByIndex(index);
    }

    public void addPoint(FunctionPoint newPoint) throws InappropriateFunctionPointException {
        for (int i = 0; i < pointsCount; ++i){
            if (newPoint.getX() == getPointX(i)) throw new InappropriateFunctionPointException();
        }
        addNodeToTail(newPoint);
        sortXPointArray(0, pointsCount);
    }
}