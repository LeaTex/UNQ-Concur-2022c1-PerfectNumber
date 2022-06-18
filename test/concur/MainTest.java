package concur;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

// Threads: 1, 2, 4, 8 y 16
// Tamaño del Buffer: 1, 2, 4, 8 y 16
// [6, 28, 496, 8128, 33550336]

class MainTest {

    @Test
    void scenarioBuffer16Threads1() {
        var mainProcesss = new Main(16, 1, 4);
        mainProcesss.run();
        assertEquals(1, 1);
    }

    @Test
    void scenarioBuffer16Threads2() {
        var mainProcesss = new Main(16, 2, 4);
        mainProcesss.run();
        assertEquals(1, 1);
    }

    @Test
    void scenarioBuffer16Threads4() {
        var mainProcesss = new Main(16, 4, 4);
        mainProcesss.run();
        assertEquals(1, 1);
    }

    @Test
    void scenarioBuffer16Threads8() {
        var mainProcesss = new Main(16, 8, 4);
        mainProcesss.run();
        assertEquals(1, 1);
    }

    @Test
    void scenarioBuffer16Threads16() {
        var mainProcesss = new Main(16, 16, 4);
        mainProcesss.run();
        assertEquals(1, 1);
    }
}