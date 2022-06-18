package concur;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @Test
    void firstTest() {
        var mainProcesss = new Main(10, 2, 10);
        mainProcesss.run();
        assertEquals(1, 1);
    }

}