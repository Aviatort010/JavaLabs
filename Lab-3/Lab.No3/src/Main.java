import functions.*;

import java.text.DecimalFormat;

public class Main {
    public static void main(String[] args) throws Exception {

        double[] someArr = {0.1, 0.2};
        FunctionPoint fp = new FunctionPoint(1, 1);

        //TabulatedFunction someFunc = new TabulatedFunction(6, 6, 3);
        //TabulatedFunction someFunc = new TabulatedFunction(5, 6, 2);
        //TabulatedFunction someFunc = new TabulatedFunction(5, 6, someArr);

        TabulatedFunction someFunc = new TabulatedFunction(1, 6, 6);
        //someFunc.getPoint(-1);
        //someFunc.getPoint(6);

        //someFunc.setPoint(-1, fp);
        //someFunc.setPoint(-1, fp);

        // and others... "setters" and "getters"...

        //someFunc.deletePoint(-1);
        //someFunc.deletePoint(7);

    }
}