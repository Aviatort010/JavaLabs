import functions.*;

import java.text.DecimalFormat;

public class Main {
    public static void main(String[] args) throws InappropriateFunctionPointException {
        DecimalFormat decimalFormat = new DecimalFormat("#.########");

        // TabulatedFunction someFunc = new LinkedListTabulatedFunction(5, 1, new double[]{1, 2, 3});
        // TabulatedFunction someFunc = new LinkedListTabulatedFunction(0, 4, new double[]{1});

        double[] someArr = {0.0, 0.1, 0.2, 0.3, 0.4};
        TabulatedFunction someFunc = new LinkedListTabulatedFunction(0, 4, someArr);

        for (int i = 0; i < someFunc.getPointsCount(); ++i){
            System.out.print(decimalFormat.format(someFunc.getFunctionValue(i)) + "; ");
        }
        System.out.println(" ");

        // someFunc.setPoint(7, new FunctionPoint(3.5, 0.5));
        // someFunc.setPoint(-1, new FunctionPoint(3.5, 0.5));
        // someFunc.setPoint(1, new FunctionPoint(3.5, 0.5));

        // someFunc.getPoint(-1);
    }
}