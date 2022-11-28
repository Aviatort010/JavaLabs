package functions.basic;

import functions.Function;

public class Exp implements Function {
    public double getLeftDomainBorder() {
        return Double.MIN_EXPONENT;
    }

    public double getRightDomainBorder() {
        return Double.MAX_EXPONENT;
    }

    public double getFunctionValue(double x) {
        return Math.exp(x);
    }

}
