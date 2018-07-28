package kata.rpn;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculatorTest {

    private RPNCalculator rpn;

    public CalculatorTest()
    {
        rpn = new RPNCalculator();
    }

    @Test
    public void additionSimple() {
        assertEquals(new BigDecimal(6), rpn.compute("4 2 +"));
        assertEquals(new BigDecimal(0), rpn.compute("0 0 +"));
        assertEquals(new BigDecimal(4), rpn.compute("2 2 +"));
        assertEquals(new BigDecimal(0), rpn.compute("-4 4 +"));
        assertEquals(new BigDecimal(0), rpn.compute("2 -2 +"));
        assertEquals(new BigDecimal(-6), rpn.compute("-4 -2 +"));
    }

    @Test
    public void soustractionSimple() {
        assertEquals(new BigDecimal(2), rpn.compute("4 2 -"));
        assertEquals(new BigDecimal(0), rpn.compute("0 0 -"));
        assertEquals(new BigDecimal(0), rpn.compute("2 2 -"));
        assertEquals(new BigDecimal(-8), rpn.compute("-4 4 -"));
        assertEquals(new BigDecimal(4), rpn.compute("2 -2 -"));
        assertEquals(new BigDecimal(-2), rpn.compute("-4 -2 -"));
    }

    @Test
    public void multiplicationSimple() {
        assertEquals(new BigDecimal(0), rpn.compute("1 0 x"));
        assertEquals(new BigDecimal(1), rpn.compute("1 1 x"));
        assertEquals(new BigDecimal(18), rpn.compute("3 6 x"));
        assertEquals(new BigDecimal(125), rpn.compute("5 25 x"));
        assertEquals(new BigDecimal(1353), rpn.compute("11 123 x"));
    }

    @Test
    public void divisionSimple() {
        assertEquals(new BigDecimal(1), rpn.compute("1 1 /"));
        assertEquals(new BigDecimal(0.5), rpn.compute("1 2 /"));
        // BigDecimal(0.3) = 0.29999999...
        assertEquals(new BigDecimal("0.3"), rpn.compute("1 3 /"));
        assertEquals(new BigDecimal(1.5), rpn.compute("3 2 /"));
    }

    @Test
    public void operationComposee() {
        assertEquals(new BigDecimal(381), rpn.compute("3 15 8 x 7 + x"));
        assertEquals(new BigDecimal(14), rpn.compute("1 2 + 4 x 5 + 3 -"));
        assertEquals(new BigDecimal(17), rpn.compute("5 4 1 2 + x +"));
        assertEquals(new BigDecimal(7.5), rpn.compute("3 4 2 1 + x + 2 /"));
    }

    @Test
    public void divisionParZero() {
        assertThrows(ArithmeticException.class, () -> rpn.compute("42 0 /"));
        assertThrows(ArithmeticException.class, () -> rpn.compute("3 4 2 1 + x + 0 /"));
    }

}
