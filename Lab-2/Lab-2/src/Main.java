import functions.*;

import java.text.DecimalFormat;

public class Main {
    public static void main(String[] args) {
        double[] someArr = {0.1, 0.2, 0.3, 0.4, 0.5};
        TabulatedFunction someFunc = new TabulatedFunction(2, 6, someArr);
        System.out.println(someFunc.getLeftDomainBorder());
        System.out.println(someFunc.getRightDomainBorder());
        DecimalFormat decimalFormat = new DecimalFormat("#.########");
        System.out.println(someFunc.getPointsCount());
        for (int i = 0; i <= someFunc.getPointsCount(); ++i){
            System.out.print(decimalFormat.format(someFunc.getFunctionValue(i)) + "; ");
        }
        System.out.println(" ");

        someFunc.setPoint(1, new FunctionPoint(3.5, 0.5));
        System.out.println(someFunc.getPointsCount());
        for (int i = 0; i <= someFunc.getPointsCount(); ++i){
            System.out.print(decimalFormat.format(someFunc.getFunctionValue(i)) + "; ");
        }
        System.out.println(" ");

        someFunc.setPointX(1, 3);
        System.out.println(someFunc.getPointsCount());
        for (int i = 0; i <= someFunc.getPointsCount(); ++i){
            System.out.print(decimalFormat.format(someFunc.getFunctionValue(i)) + "; ");
        }
        System.out.println(" ");

        someFunc.deletePoint(4);
        System.out.println(someFunc.getPointsCount());
        for (int i = 0; i <= someFunc.getPointsCount(); ++i){
            System.out.print(decimalFormat.format(someFunc.getFunctionValue(i)) + "; ");
        }
        System.out.println(" ");

        for(double i = 0.5; i < 10; ++i){
            someFunc.addPoint(new FunctionPoint(i, i * 0.1));
        }
        System.out.println(someFunc.getPointsCount());
        for (int i = 0; i <= someFunc.getPointsCount(); ++i){
            System.out.print(decimalFormat.format(someFunc.getFunctionValue(i)) + "; ");
        }
        System.out.println(" ");

    }
}