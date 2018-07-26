package kata.rpn;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CalculatorTest {

    private RPNCalculator rpn;

    public CalculatorTest()
    {
        rpn = new RPNCalculator();
    }

    @Test
    public void additionSimple() {
        assertTrue(rpn.compute("4 2 +").equals(new BigDecimal(6)));
        assertTrue(rpn.compute("0 0 +").equals(new BigDecimal(0)));
        assertTrue(rpn.compute("2 2 +").equals(new BigDecimal(4)));
        assertTrue(rpn.compute("-4 4 +").equals(new BigDecimal(0)));
        assertTrue(rpn.compute("2 -2 +").equals(new BigDecimal(0)));
        assertTrue(rpn.compute("-4 -2 +").equals(new BigDecimal(-6)));
    }

    @Test
    public void soustractionSimple() {
        assertTrue(rpn.compute("4 2 -").equals(new BigDecimal(2)));
        assertTrue(rpn.compute("0 0 -").equals(new BigDecimal(0)));
        assertTrue(rpn.compute("2 2 -").equals(new BigDecimal(0)));
        assertTrue(rpn.compute("-4 4 -").equals(new BigDecimal(-8)));
        assertTrue(rpn.compute("2 -2 -").equals(new BigDecimal(4)));
        assertTrue(rpn.compute("-4 -2 -").equals(new BigDecimal(-2)));
    }

    @Test
    public void multiplicationSimple() {
        assertTrue(rpn.compute("1 0 x").equals(new BigDecimal(0)));
        assertTrue(rpn.compute("1 1 x").equals(new BigDecimal(1)));
        assertTrue(rpn.compute("3 6 x").equals(new BigDecimal(18)));
        assertTrue(rpn.compute("5 25 x").equals(new BigDecimal(125)));
        assertTrue(rpn.compute("11 123 x").equals(new BigDecimal(1353)));
    }

    @Test
    public void divisionSimple() {
        assertTrue(rpn.compute("1 1 /").equals(new BigDecimal(1)));
        assertTrue(rpn.compute("1 2 /").equals(new BigDecimal(0.5)));
        // BigDecimal(0.3) = 0.29999999...
        assertTrue(rpn.compute("1 3 /").equals(new BigDecimal("0.3")));
        assertTrue(rpn.compute("3 2 /").equals(new BigDecimal(1.5)));
    }

    @Test
    public void operationComposee() {
        assertTrue(rpn.compute("3 15 8 x 7 + x").equals(new BigDecimal(381)));
        assertTrue(rpn.compute("1 2 + 4 x 5 + 3 -").equals(new BigDecimal(14)));
        assertTrue(rpn.compute("5 4 1 2 + x +").equals(new BigDecimal(17)));
        assertTrue(rpn.compute("3 4 2 1 + x + 2 /").equals(new BigDecimal(7.5)));
    }

    @Test
    public void divisionParZero() {
        assertThrows(ArithmeticException.class, () -> rpn.compute("42 0 /"));
        assertThrows(ArithmeticException.class, () -> rpn.compute("3 4 2 1 + x + 0 /"));
    }

}
