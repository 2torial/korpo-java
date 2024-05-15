package GeoConsole.Figure.test;

import GeoConsole.Figure.Rhombus;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RhombusTest {

    @Test
    void illegalConstructors() {
        assertThrows(IllegalStateException.class, () -> new Rhombus(-1, -1, -1, -1));
        assertThrows(IllegalStateException.class, () -> new Rhombus(1, -1, -1, -1));
        assertThrows(IllegalStateException.class, () -> new Rhombus(1, 1, 1, -1));

        assertThrows(IllegalStateException.class, () -> new Rhombus(1, 8, -1, -1));
        assertThrows(IllegalStateException.class, () -> new Rhombus(1, -1, 8, -1));
        assertThrows(IllegalStateException.class, () -> new Rhombus(1, -1, -1, 8));
    }

    @Test
    void getCircumcircle() {
        for (int i = 0; i < 10; i++) {
            double side = Math.floor(Math.random() * 49 + 1), diagA = Math.random() * 49 + 1, diagB = Math.random() * 49 + 1, area = Math.random() * 49 + 1;

            if (diagA != diagB) {
                assertThrows(IllegalArgumentException.class, () -> new Rhombus(-1, diagA, -1, area).getCircumcircle());
                assertThrows(IllegalArgumentException.class, () -> new Rhombus(-1, diagA, diagB, -1).getCircumcircle());
            }
            assertDoesNotThrow(() -> new Rhombus(side, -1, side * Math.sqrt(2), -1));
            assertDoesNotThrow(() -> new Rhombus(side, side * Math.sqrt(2), -1, -1));
        }
    }

    @Test
    void doubleSelf() {
        for (int i = 0; i < 10; i++) {
            double diagA = Math.random() * 49 + 1,
                diagB = Math.random() * 49 + 1,
                area = Math.random() * 49 + 1;

            Rhombus r1 = new Rhombus(-1, diagA, diagB, -1),
                    r2 = new Rhombus(-1, diagA, -1, area),
                    r3 = new Rhombus(-1, -1, diagB, area);

            assertEquals(r1.getArea() * 2, r1.doubleSelf().first().getArea(), 0.005);
            assertEquals(r2.getArea() * 2, r2.doubleSelf().first().getArea(), 0.005);
            assertEquals(r3.getArea() * 2, r3.doubleSelf().first().getArea(), 0.005);
        }
    }
}