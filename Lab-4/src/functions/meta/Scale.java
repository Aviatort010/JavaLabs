package functions.meta;

import functions.Function;

public class Scale implements Function {
    Function a;
    double x = 1, y = 1;

    public Scale(Function a, double x, double y){
        this.a = a;
        this.x = x;
        this.y = y;
    }

    public double getLeftDomainBorder() {
        return x * a.getLeftDomainBorder();
    }

    public double getRightDomainBorder() {
        return x * a.getRightDomainBorder();
    }

    public double getFunctionValue(double x) {
        return y * a.getFunctionValue(x);
    }
}
