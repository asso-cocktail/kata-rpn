package kata.rpn;

public class MyTest {

    public static void main(String[] args)
    {
        RPNCalculator rpn = new RPNCalculator();

        System.out.println(rpn.compute("5 3 +"));
        System.out.println(rpn.compute("6 2 /"));
        System.out.println(rpn.compute("5 2 - 7 +"));
        System.out.println(rpn.compute("7 5 2 - +"));
        System.out.println(rpn.compute("3 15 8 x 7 + x"));
        System.out.println(rpn.compute("1 2 + 4 x 5 + 3 -"));
        System.out.println(rpn.compute("5 4 1 2 + x +"));
        System.out.println(rpn.compute("3 4 2 1 + x + 2 /"));
    }

}
