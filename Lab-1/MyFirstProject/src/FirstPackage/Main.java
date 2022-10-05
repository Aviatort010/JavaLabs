package FirstPackage;

public class Main
{
    public static void main(String[] s)
    {
        SecondClass someObj = new SecondClass();
        int i, j;
        for (i = 1; i <= 8; ++i){
            for (j = 1; j <= 8; ++j){
                someObj.setA(i);
                someObj.setB(j);
                System.out.print(someObj.sumAB());
                System.out.print(" ");
            }
        }
    }
}

