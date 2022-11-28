import functions.*;
import functions.basic.*;

import java.io.*;
import java.text.DecimalFormat;

public class Main {
    public static void main(String[] args) throws InappropriateFunctionPointException, IOException, CloneNotSupportedException {
        // Formatter позволит красиво написать неявно Округлённые(!) значения.
        DecimalFormat formatter = new DecimalFormat("#.###");
        double pi = 3.1415926;

        //==============================================================================================================

        FunctionPoint[] sinPoints = new FunctionPoint[5];
        Sin sinClass = new Sin();
        FunctionPoint[] cosPoints = new FunctionPoint[5];
        Cos cosClass = new Cos();
        for (int i = 0; i < 5; ++i) {
            sinPoints[i] = new FunctionPoint(i, sinClass.getFunctionValue(i * (pi / 4)));
            cosPoints[i] = new FunctionPoint(i, cosClass.getFunctionValue(i * (pi / 4)));
        }

        TabulatedFunction sinTab = new ArrayTabulatedFunction(sinPoints);
        TabulatedFunction cosTab = new LinkedListTabulatedFunction(cosPoints);

        System.out.println("sin and cos (0 -- pi):\n\t" + sinTab + "\n\t" + cosTab);
        System.out.println();

        //==============================================================================================================

        System.out.println("sinArray =? cosList:\t" +
                sinTab.equals(cosTab));    // разные объекты разные классы
        TabulatedFunction otherSinTab = new LinkedListTabulatedFunction(sinPoints);
        System.out.println("sinArray =? sinList:\t" +
                sinTab.equals(otherSinTab));  // разные классы одинаковые объекты
        TabulatedFunction otherCosTab = new ArrayTabulatedFunction(cosPoints);
        System.out.println("sinArray =? cosArray:\t" +
                sinTab.equals(otherCosTab));  // одинаковые классы разные объекты
        otherSinTab = new ArrayTabulatedFunction(sinPoints);
        System.out.println("sinArray =? sinArray:\t" +
                sinTab.equals(otherSinTab));  // одинаковые классы одинаковые объекты
        System.out.println();

        //==============================================================================================================

        System.out.println("sinTab hashCode:" + sinTab.hashCode());
        System.out.println("cosTab hashCode:" + cosTab.hashCode());
        System.out.println("sin[4] hashCode:" + sinPoints[4].hashCode());
        System.out.println("cos[0] hashCode:" + cosPoints[0].hashCode());
        cosPoints[0].setX(cosPoints[0].getX() + 0.0054);
        System.out.println("(cos[0] + 0.0054) hashCode:" + cosPoints[0].hashCode());
        System.out.println();

        //==============================================================================================================

        TabulatedFunction sinClone = (TabulatedFunction) sinTab.clone();
        System.out.println("sinClone (Array Type):\n\t" + sinClone);
        sinTab.deletePoint(2);
        sinTab.setPointY(1, 0.5);
        System.out.println("sinClone after changed sinTab:\n\t" + sinClone);

        TabulatedFunction cosClone = (TabulatedFunction) cosTab.clone();
        System.out.println("cosClone (Linked List Type):\n\t" + cosClone);
        cosTab.deletePoint(2);
        cosTab.setPointY(1, 0.5);
        System.out.println("cosClone after changed cosTab:\n\t" + cosClone);
    }
}