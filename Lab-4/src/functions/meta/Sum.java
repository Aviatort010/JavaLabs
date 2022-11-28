package functions.meta;

import functions.Function;

public class Sum implements Function {
    Function a;
    Function b;
    double leftBorder = 0;
    double rightBorder = 0;

    public Sum(Function a, Function b){
        this.a = a;
        this.b = b;
        leftBorder = Math.max(a.getLeftDomainBorder(), b.getLeftDomainBorder());
        rightBorder = Math.min(a.getRightDomainBorder(), b.getRightDomainBorder());
    }

    public double getLeftDomainBorder() {
        return leftBorder;
    }

    public double getRightDomainBorder() {
        return rightBorder;
    }

    public double getFunctionValue(double x) {
        return a.getFunctionValue(x) + b.getFunctionValue(x);
    }
}
