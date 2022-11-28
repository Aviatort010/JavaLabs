package functions;

import java.io.*;
import java.util.Objects;

public class TabulatedFunctions {
    private TabulatedFunctions(){}

    public static TabulatedFunction tabulate(Function function, double leftX, double rightX, int pointsCount) throws InappropriateFunctionPointException {
        if (leftX < function.getLeftDomainBorder() || function.getRightDomainBorder() < rightX) throw new IllegalArgumentException();

        LinkedListTabulatedFunction fun = new LinkedListTabulatedFunction(leftX, rightX, pointsCount);
        for (int i = 0; i < pointsCount; ++i) {
            fun.setPointY(i, function.getFunctionValue(fun.getPointX(i)));
        }
        return fun;
    }

    public static void outputTabulatedFunction(TabulatedFunction function, OutputStream out) throws IOException {
        FileOutputStream outFile = (FileOutputStream) out;
        String outString = "";
        outString = outString.concat(String.valueOf(function.getPointsCount()));
        for (int i = 1; i < function.getPointsCount() + 1; ++i){
            outString = outString.concat(" " + String.valueOf(function.getPointX(i - 1))
                               + " " + String.valueOf(function.getPointY(i - 1)));
        }
        outFile.write(outString.getBytes());
    }

    public static TabulatedFunction inputTabulatedFunction(InputStream in) throws IOException {
        // create a new tokenizer
        Reader r = new BufferedReader(new InputStreamReader(in));
        StreamTokenizer st = new StreamTokenizer(r);

        st.parseNumbers();
        st.ordinaryChar('-');

        st.nextToken();
        int pointsCount = (int)st.nval;
        FunctionPoint[] rePoints = new FunctionPoint[pointsCount];
        for (int i = 0; i < pointsCount; ++i){
            st.nextToken();
            rePoints[i] = new FunctionPoint(st.nval, 0);

            st.nextToken();
            if (st.ttype == '-') {
                st.nextToken();
                if (st.ttype == -3) rePoints[i].setY(Double.NEGATIVE_INFINITY);
                else rePoints[i].setY(-st.nval);
            }else if (st.ttype == -3) rePoints[i].setY(Double.POSITIVE_INFINITY);
            else rePoints[i].setY(st.nval);
        }
        return new LinkedListTabulatedFunction(rePoints);
    }

    public static void writeTabulatedFunction(TabulatedFunction function, Writer out) throws IOException {
        String writeString;
        writeString = String.valueOf(function.getPointsCount());
        for (int i = 0; i < function.getPointsCount(); ++i){
            writeString = String.join(" ", writeString, String.valueOf(function.getPointX(i)));
            writeString = String.join(" ", writeString, String.valueOf(function.getPointY(i)));
        }
        out.write(writeString);
    }

    public static TabulatedFunction readTabulatedFunction(Reader in) throws IOException {
        String readString = "";
        int charNum;
        while((charNum = in.read()) != -1)
            readString = readString.concat(String.valueOf((char) charNum));
        String[] stringsDouble = readString.split(" ");

        int pointsCount = Integer.parseInt(stringsDouble[0]);
        FunctionPoint[] points = new FunctionPoint[pointsCount];

        for(int i = 1; i < pointsCount + 1; ++i)
            points[i - 1] = new FunctionPoint(Double.parseDouble(stringsDouble[i * 2 - 1]),
                                          Double.parseDouble(stringsDouble[i * 2]));

        TabulatedFunction reFunction = new LinkedListTabulatedFunction(points);
        return reFunction;
    }
}
