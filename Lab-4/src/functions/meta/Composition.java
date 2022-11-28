package functions.meta;

import functions.Function;

public class Composition implements Function {
    Function a;
    Function b;

    public Composition(Function a, Function b){
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
        return a.getFunctionValue(b.getFunctionValue(x));
    }
}
