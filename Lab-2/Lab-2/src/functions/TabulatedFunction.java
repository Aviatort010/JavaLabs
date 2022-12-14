package functions;

import static java.lang.Math.abs;

public class TabulatedFunction {
    private FunctionPoint[]  pointArray;
    private int arrLen = 1;
    private int pointsCount = 0;
    
    public TabulatedFunction(double leftX, double rightX, int pointsCount){
        pointsCount = pointsCount;
        while(arrLen < pointsCount) arrLen *= 2;
        pointArray = new FunctionPoint[arrLen];
        double step = (rightX - leftX + 1)/pointsCount;
        for (int i = 0; i < pointsCount; ++i){
            pointArray[i] = new FunctionPoint((leftX + (i * step)), 0);
        }
    }
    public TabulatedFunction(double leftX, double rightX, double[] values){
        pointsCount = values.length;
        while(arrLen < values.length) arrLen *= 2;
        pointArray = new FunctionPoint[arrLen];
        double step = (rightX - leftX + 1)/values.length;
        for (int i = 0; i < values.length; ++i){
            pointArray[i] = new FunctionPoint((leftX + (i * step)), values[i]);
        }
    }
    public double getLeftDomainBorder(){
        return pointArray[0].getX();
    }
    public double getRightDomainBorder(){
        return pointArray[pointsCount - 1].getX();
    }
    public double getFunctionValue(double x){
        double epsilon = 0.000001;   // just null for programmistic double

        if ((pointArray[0].getX() > x) || (pointArray[pointsCount - 1].getX() < x)) return Double.NaN;

        for (int i = 0; i < pointsCount; ++i){
            if (abs(pointArray[i].getX() - x) < epsilon) return pointArray[i].getY();
        }

        for (int i = 1; i < pointsCount; ++i){
            if ((pointArray[i - 1].getX() < x) && (x < pointArray[i].getX()))
                return ((x - pointArray[i - 1].getX()) / (pointArray[i].getX() - pointArray[i - 1].getX())) * (pointArray[i].getY() - pointArray[i - 1].getY()) + pointArray[i - 1].getY();
        }

        return Double.NaN;
    }
    public int getPointsCount(){
        return pointsCount;
    }
    public FunctionPoint getPoint(int index){   // ???
        return pointArray[index];
    }
    public void setPoint(int index, FunctionPoint newPoint){
        if ((index == 0) && (pointArray[index + 1].getX() > newPoint.getX())) pointArray[index] = newPoint;
        if ((index == pointsCount - 1 ) && (pointArray[index - 1].getX() < newPoint.getX())) pointArray[index] = newPoint;
        if ((pointArray[index - 1].getX() < newPoint.getX()) && (newPoint.getX() < pointArray[index + 1].getX())) pointArray[index] = newPoint;
    }
    public double getPointX(int index){
        return pointArray[index].getX();
    }
    public void setPointX(int index, double x){
        if ((index == 0) && (pointArray[index + 1].getX() > x)) pointArray[index].setX(x);
        if ((index == pointsCount - 1 ) && (pointArray[index - 1].getX() < x)) pointArray[index].setX(x);
        if ((pointArray[index - 1].getX() < x) && (x < pointArray[index + 1].getX())) pointArray[index].setX(x);
    }
    public double getPointY(int index){
        return pointArray[index].getY();
    }
    public void setPointY(int index, double y){
        pointArray[index].setY(y);
    }
    public void sortXPointArray(int beginIndex, int endIndex){   // Bubble sort
        if((pointsCount > 1) && (endIndex - beginIndex > 1)){
            boolean sorted = false;
            FunctionPoint buffer;
            while(!sorted){
                sorted = true;
                for(int i = beginIndex; i < endIndex - 1; ++i){
                    if(pointArray[i].getX() > pointArray[i + 1].getX()){
                        buffer = pointArray[i];
                        pointArray[i] = pointArray[i + 1];
                        pointArray[i + 1] = buffer;
                        sorted = false;
                    }
                }
            }
        }
    }
    public void deletePoint(int index){
        if (index != 0){
            for(int i = index; i < pointsCount - 1; ++i) pointArray[i] = pointArray[i + 1];
        }
        --pointsCount;
    }
    public void addPoint(FunctionPoint newPoint){
        ++pointsCount;
        if(pointsCount < arrLen) {
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




