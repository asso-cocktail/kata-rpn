package kata.rpn;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;

/**
 * Calculate RPN expressions
 * @author Emmanuel Turbet-Delof
 * @version 1.0
 */
public class RPNCalculator implements Calculator {

    private int roundingScale;

    private IllegalArgumentException invalidArgumentException = new IllegalArgumentException("Invalid expression");
    private ArithmeticException divideByZeroException = new ArithmeticException("/ by zero");

    RPNCalculator(int roundingScale)
    {
        this.roundingScale = roundingScale;
    }

    RPNCalculator()
    {
        this.roundingScale = 1;
    }

    /**
     * Calculate a RPN expression
     * @param operation (String) RPN expression
     * @return (BigDecimal) the result
     */
    public BigDecimal compute(String operation)
    {
        if(!isValid(operation))
            throw this.invalidArgumentException;

        String[] stack = new String[0];

        for (String elem : operation.split(" "))
            stack = isNum(elem) ? push(stack, elem) : calc(stack, elem);

        if (stack.length > 1)
            throw this.invalidArgumentException;

        return new BigDecimal(stack[0]);
    }

    /**
     * Determines if the RPN expression is valid
     * @param operation RPN expression
     * @return (boolean) true or false
     */
    private boolean isValid(String operation)
    {
        String[] n = operation.split(" ");

        if (n.length == 0) return false;
        for (String i : n)
            if (!isSign(i) && !isNum(i)) return false;
        return true;
    }

    /**
     * Determines if an element is a valid mathematical sign
     * @param elem (String) element
     * @return (boolean) true or false
     */
    private boolean isSign(String elem)
    {
        return elem.equals("+") || elem.equals("-") || elem.equals("x") || elem.equals("/");
    }

    /**
     * Determines if an element is a number
     * @param elem (String) element
     * @return (boolean) true or false
     */
    private boolean isNum(String elem)
    {
        try {
            Double.parseDouble(elem);
        }catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    /**
     * Determines if a number is a whole number
     * @param number (BigDecimal) number
     * @return (boolean) true or false
     */
    private boolean isWholeNum(BigDecimal number) {
        return number.remainder(BigDecimal.ONE).compareTo(BigDecimal.ZERO) == 0;
    }

    /**
     * Adds elem to the end of array
     * @param array (String[]) array that contains elements
     * @param elem (String) element of the array
     * @return (String[]) a new array grown by one element
     */
    private String[] push(String[] array, String elem)
    {
        String[] copy = Arrays.copyOf(array, array.length+1);
        copy[array.length] = elem;
        return copy;
    }

    /**
     * Make an addition, subtraction, multiplication or division
     * on the last two elements of the array depends on the mathematical sign given
     * @param array (String[]) array that contains elements to be calculated
     * @param sign (String) mathematical sign : +, -, x or /
     * @return (String[]) a new array with a result at the end instead of two elements
     * or exit the program if the calculation is a division by 0
     * or exit the program if the sign is invalid (not equal to +, -, x or /)
     */
    private String[] calc(String[] array, String sign)
    {
        int pos = array.length - 2;

        BigDecimal first = new BigDecimal(array[pos]);
        BigDecimal second = new BigDecimal(array[pos+1]);

        switch(sign) {
            case "+":
                array[pos] = first.add(second).toString();
                break;
            case "-":
                array[pos] = first.subtract(second).toString();
                break;
            case "x":
                array[pos] = first.multiply(second).toString();
                break;
            case "/":
                if (second.equals(BigDecimal.ZERO)) throw this.divideByZeroException;
                BigDecimal res = first.divide(second, this.roundingScale, RoundingMode.FLOOR);
                if(isWholeNum(res)) res = res.setScale(0, BigDecimal.ROUND_DOWN);
                array[pos] = res.toString();
                break;
            default:
                break;
        }

        return Arrays.copyOf(array, array.length-1);
    }
}
