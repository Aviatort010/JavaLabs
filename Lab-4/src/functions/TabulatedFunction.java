package functions;


public interface TabulatedFunction extends Function{
    public int getPointsCount();

    public FunctionPoint getPoint(int index);

    public void setPoint(int index, FunctionPoint newPoint) throws InappropriateFunctionPointException;

    public double getPointX(int index) throws FunctionPointIndexOutOfBoundsException;

    public void setPointX(int index, double x) throws InappropriateFunctionPointException;

    public double getPointY(int index);

    public void setPointY(int index, double y) throws InappropriateFunctionPointException;

    public void deletePoint(int index);

    public void addPoint(FunctionPoint newPoint) throws InappropriateFunctionPointException;
}