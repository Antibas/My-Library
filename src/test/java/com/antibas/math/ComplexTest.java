package com.antibas.math;

import com.antibas.math.matrix.Matrix;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ComplexTest {

    @Test
    void add_whenUsingDouble_shouldAddToRealPart(){
        Complex c = new Complex(1,2);
        assertEquals(3, c.add(2).getReal());
    }

    @Test
    void add_whenUsingComplex_shouldAddAccordingly(){
        Complex c1 = new Complex(1,2),
                c2 = new Complex(3,4);
        assertEquals(new Complex(4, 6), c1.add(c2));
    }

    @Test
    void subtract_whenUsingDouble_shouldSubtractToRealPart(){
        Complex c = new Complex(1,2);
        assertEquals(-1, c.subtract(2).getReal());
    }

    @Test
    void subtract_whenUsingComplex_shouldSubtractAccordingly(){
        Complex c1 = new Complex(1,2),
                c2 = new Complex(3,4);
        assertEquals(new Complex(-2, -2), c1.subtract(c2));
    }

    @Test
    void multiply_whenUsingDouble_shouldMultiplyToRealPart(){
        Complex c = new Complex(1,2);
        assertEquals(2, c.multiply(2).getReal());
    }

    @Test
    void multiply_whenUsingComplex_shouldMultiplyAccordingly(){
        Complex c1 = new Complex(1,2),
                c2 = new Complex(3,4);
        assertEquals(new Complex(-5, 10), c1.multiply(c2));
    }

    @Test
    void divide_whenUsingDouble_shouldDivideToRealPart(){
        Complex c = new Complex(1,2);
        assertEquals(.5, c.divide(2).getReal());
    }

    @Test
    void divide_whenUsingComplex_shouldDivideAccordingly(){
        Complex c1 = new Complex(2,2),
                c2 = new Complex(3,4);
        assertEquals(new Complex(-2, 0.2857142857142857), c1.divide(c2));
    }

    @Test
    void divide_whenDivideByZero_shouldThrowArithmeticException(){
        Complex c = new Complex(2,2);
        assertThrows(
                ArithmeticException.class,
                () -> c.divide(0)
        );
    }

    @Test
    void conjugate_shouldBeCorrect(){
        Complex c = new Complex(1,2);
        assertEquals(new Complex(1, -2), c.conjugate());
    }

    @Test
    void parseComplex_whenInputIsValid_shouldBeCorrect(){
        Complex c = new Complex("1+9j");
        assertEquals(new Complex(1, 9), c);

        c = new Complex("1j+9");
        assertEquals(new Complex(9, 1), c);

        c = new Complex("1-9j");
        assertEquals(new Complex(1, -9), c);

        c = new Complex("-1j+9");
        assertEquals(new Complex(9, -1), c);
    }

    @Test
    void parseComplex_whenInputIsInvalid_shouldThrowNumberFormatException(){
        assertThrows(
                NumberFormatException.class,
                () -> new Complex("1+9")
        );

        assertThrows(
                NumberFormatException.class,
                () -> new Complex("1w")
        );
    }

    @Test
    void toPolarString_shouldBeCorrect(){
        Complex c = new Complex(1,2);
        assertEquals("2.23606797749979 * exp(j1.1071487177940904)", c.toPolarString());
    }
}
