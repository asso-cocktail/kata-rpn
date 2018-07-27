package kata.rpn;

public class MyTest {

    public static void main(String[] args)
    {
        RPNCalculator rpn = new RPNCalculator();

        System.out.println(rpn.compute("1 1 /"));
    }

}
