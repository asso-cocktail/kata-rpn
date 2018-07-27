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

    /**
     * Creates RPNCalculator object
     * @param roundingScale (int) number of digits after the decimal point
     */
    RPNCalculator(int roundingScale)
    {
        this.roundingScale = roundingScale;
    }

    /**
     * Creates RPNCalculator object
     * number of digits after the decimal point set to 1 by default
     */
    RPNCalculator()
    {
        roundingScale = 1;
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
        String[] parts = operation.split(" ");

        if (parts.length == 0) return false;
        for (String part : parts)
        {
            // IF not a sign AND not a number
            if ((!part.equals("+") && !part.equals("-") && !part.equals("x") && !part.equals("/")) && !isNum(part))
                return false;
        }
        return true;
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
                // IF divide by zero
                if (second.equals(BigDecimal.ZERO)) throw this.divideByZeroException;
                BigDecimal res = first.divide(second, this.roundingScale, RoundingMode.FLOOR);
                // IF res is a whole number
                if(res.remainder(BigDecimal.ONE).compareTo(BigDecimal.ZERO) == 0)
                    res = res.setScale(0, BigDecimal.ROUND_DOWN);
                array[pos] = res.toString();
                break;
            default:
                break;
        }

        return Arrays.copyOf(array, array.length-1);
    }
}
