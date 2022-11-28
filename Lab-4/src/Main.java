import functions.*;
import functions.basic.*;

import java.io.*;
import java.text.DecimalFormat;

public class Main {
    public static void main(String[] args) throws InappropriateFunctionPointException, IOException {
        // Formatter позволит красиво написать неявно Округлённые(!) значения.
        DecimalFormat formatter = new DecimalFormat("#.###");
        double pi = 3.1415926;

        //==============================================================================================================
        Sin justSin = new Sin();
        Cos justCos = new Cos();
        System.out.println("Sin:");
        for (double d = 0; d <= 2 * pi; d += 0.1 * 2 * pi)
            System.out.print(" \t" + formatter.format(justSin.getFunctionValue(d)));
        System.out.println("");
        System.out.println("Cos:");
        for (double d = 0; d <= 2 * pi; d += 0.1 * 2 * pi)
            System.out.print(" \t" + formatter.format(justCos.getFunctionValue(d)));

        //==============================================================================================================
        TabulatedFunction tabSin = TabulatedFunctions.tabulate(justSin, 0, 2 * pi, 10);
        TabulatedFunction tabCos = TabulatedFunctions.tabulate(justCos, 0, 2 * pi, 10);
        System.out.println("");
        System.out.println("Tab Sin:");
        for (double d = 0; d <= 2 * pi; d += 0.1 * 2 * pi)
            System.out.print(" \t" + formatter.format(tabSin.getFunctionValue(d)));
        System.out.println("");
        System.out.println("Tab Cos:");
        for (double d = 0; d <= 2 * pi; d += 0.1 * 2 * pi)
            System.out.print(" \t" + formatter.format(tabCos.getFunctionValue(d)));

        //==============================================================================================================
        Function sumOfSquares = Functions.sum(Functions.power(tabSin, 2), Functions.power(tabCos, 2));
        System.out.println("");
        System.out.println("Sum of squares of tabulated Sin and Cos:");
        for (double d = 0; d <= 2 * pi; d += 0.1 * 2 * pi)
            System.out.print(" \t" + formatter.format(sumOfSquares.getFunctionValue(d)));

        //==============================================================================================================
        TabulatedFunction tabExp = TabulatedFunctions.tabulate(new Exp(), 0, 10, 11);
        Writer outfile = new FileWriter("/home/slava/Рабочий стол/IDEA/Lab-4/outfile.txt");
        TabulatedFunctions.writeTabulatedFunction(tabExp, outfile);
        outfile.close();

        Reader infile = new FileReader("/home/slava/Рабочий стол/IDEA/Lab-4/outfile.txt");
        TabulatedFunction fileTabExp = TabulatedFunctions.readTabulatedFunction(infile);

        System.out.println("");
        System.out.println("Tab Exp:");
        for (int i = 0; i < 11; ++i)
            System.out.print(" \t" + formatter.format(tabExp.getFunctionValue(i)));
        System.out.println("");
        System.out.println("Returned from file Tab Exp:");
        for (int i = 0; i < 11; ++i)
            System.out.print(" \t" + formatter.format(fileTabExp.getFunctionValue(i)));

        //==============================================================================================================
        TabulatedFunction tabLog = TabulatedFunctions.tabulate(new Log(2.71828), 0, 10, 11);
        OutputStream otherOutfile = new FileOutputStream("/home/slava/Рабочий стол/IDEA/Lab-4/otherOutfile.txt");
        TabulatedFunctions.outputTabulatedFunction(tabLog, otherOutfile);
        otherOutfile.close();

        InputStream otherInfile = new FileInputStream("/home/slava/Рабочий стол/IDEA/Lab-4/otherOutfile.txt");
        TabulatedFunction fileTabLog = TabulatedFunctions.inputTabulatedFunction(otherInfile);

        System.out.println("");
        System.out.println("Tab Log:");
        for (int i = 0; i < 11; ++i)
            System.out.print(" \t" + formatter.format(tabLog.getFunctionValue(i)));
        System.out.println("");
        System.out.println("Returned from file with Byte type of memory Tab Log:");
        for (int i = 0; i < 11; ++i)
            System.out.print(" \t" + formatter.format(fileTabLog.getFunctionValue(i)));

    }
}