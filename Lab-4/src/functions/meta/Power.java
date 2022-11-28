package functions.meta;

import functions.Function;

public class Power implements Function {
    Function a;
    double b;
/*
    Function b;
// Некорректное условие. Либо степень-функция, либо степень-число.
    public Power(Function a, Function b){
        this.a = a;
        this.b = b;
    }
*/
    public Power(Function a, double b){
        this.a = a;
        this.b = b;
    }

    public double getLeftDomainBorder() {
        return a.getLeftDomainBorder();
    }

    public double getRightDomainBorder() {
        return a.getRightDomainBorder();
    }

    public double getFunctionValue(double x) {
        return Math.pow(a.getFunctionValue(x), b);
    }
}
