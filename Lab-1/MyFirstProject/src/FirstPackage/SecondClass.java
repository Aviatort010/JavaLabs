package FirstPackage;

public class SecondClass
{
    private int a = 0, b = 0;

    public SecondClass(){
        this.a = 0;
        this.b = 0;
    }
    public int getA() {
        return a;
    }
    public int getB() {
        return b;
    }
    public void setA(int a) {
        this.a = a;
    }
    public void setB(int b) {
        this.b = b;
    }
    public int sumAB() {
        return a + b;
    }
}
