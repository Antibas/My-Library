package com.antibas.math.matrix;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MatrixTest {

    @Test
    void r_shouldBePositive(){
        assertThrows(
                IndexOutOfBoundsException.class,
                () -> new Matrix(-1, 1)
        );
    }

    @Test
    void c_shouldBePositive(){
        assertThrows(
                IndexOutOfBoundsException.class,
                () -> new Matrix(1, -1)
        );
    }

    @Test
    void shouldBeOrthogonal(){
        Matrix m = new Matrix(2, 2);
        assertTrue(m.isOrthogonal());
    }

    @Test
    void shouldBeEmpty(){
        Matrix m = new Matrix();
        assertTrue(m.isEmpty());
    }


    @Test
    void shouldBe1X5(){
        Matrix m = new Matrix(1d, 2d, 3, 4, 5);
        assertEquals(1, m.getRows());
        assertEquals(5, m.getColumns());
    }

    @Test
    void shouldBe2X3(){
        Matrix m = new Matrix(2,1d, 2d, 3, 4, 5);
        assertEquals(2, m.getRows());
        assertEquals(3, m.getColumns());
    }

    @Test
    void shouldBe3X2(){
        Matrix m = new Matrix(3, 2,1d, 2d, 3, 4, 5);
        assertEquals(3, m.getRows());
        assertEquals(2, m.getColumns());
    }

    @Test
    void shouldThrowNumberFormatException(){
        assertThrows(
                NumberFormatException.class,
                () -> new Matrix("2", "3", "w")
        );
    }

    @Test
    void shouldThrowIllegalArgumentException(){
        assertThrows(
                IllegalArgumentException.class,
                () -> new Matrix("2", "3", "")
        );
    }

    @Test
    void shouldNotBeEqual(){
        Matrix m1 = new Matrix(3, 2,1d, 2d, 3, 4, 5),
        m2 = new Matrix(2, 3,1d, 2d, 3, 4, 5);
        assertNotEquals(m1, m2);
    }

    @Test
    void shouldBeEqual(){
        Matrix m1 = new Matrix(3, 2,1d, 2d, 3, 4, 5),
                m2 = new Matrix(3,1d, 2d, 3, 4, 5);
        assertEquals(m1, m2);
    }

    @Test
    void excludeRow_shouldRemoveARow(){
        Matrix m1 = new Matrix(3, 2,1d, 2d, 3, 4, 5);
        assertEquals(2, m1.excludeRow(1).getRows());
    }

    @Test
    void excludeColumn_shouldRemoveAColumn(){
        Matrix m1 = new Matrix(3, 2,1d, 2d, 3, 4, 5);
        assertEquals(1, m1.excludeColumn(1).getColumns());
    }

    @Test
    void T_shouldBeInverse(){
        Matrix m1 = new Matrix(3, 2,1d, 2d, 3, 4, 5);
        assertEquals(m1, m1.T().T());
    }

    @Test
    void rowSum_shouldBeNaN(){
        Matrix m1 = new Matrix(3, 2,1d, 2d, 3, 4, 5);
        assertEquals(Double.NaN, m1.rowSum(2));
    }

    @Test
    void rowSum_shouldBe7(){
        Matrix m1 = new Matrix(3, 2,1d, 2d, 3, 4, 5);
        assertEquals(7, m1.rowSum(1));
    }

    @Test
    void columnSum_shouldBeNaN(){
        Matrix m1 = new Matrix(3, 2,1d, 2d, 3, 4, 5);
        assertEquals(Double.NaN, m1.columnSum(1));
    }

    @Test
    void columnSum_shouldBe9(){
        Matrix m1 = new Matrix(3, 2,1d, 2d, 3, 4, 5);
        assertEquals(9, m1.columnSum(0));
    }

    @Test
    void isZeroRow_shouldBeTrue(){
        Matrix m1 = new Matrix(3, 2,0,0,3, 4, 5);
        assertTrue(m1.isZeroRow(0));
    }

    @Test
    void isZeroColumn_shouldBeTrue(){
        Matrix m1 = new Matrix(3, 2,0,2,0, 4, 0);
        assertTrue(m1.isZeroColumn(0));
    }
}
