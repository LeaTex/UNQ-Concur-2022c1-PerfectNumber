package concur;

import org.junit.jupiter.api.Test;

import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.*;

class BufferTest {

    @Test
    void testNext() {
        var buffer = new Buffer(3);
        assertEquals(1, buffer.next(0));
        assertEquals(2, buffer.next(1));
        assertEquals(3, buffer.next(2));
        assertEquals(0, buffer.next(3));
    }

    @Test
    void testNewBufferIsEmpty() {
        var buffer = new Buffer(5);
        assertTrue(buffer.isEmpty());
        assertFalse(buffer.isFull());
    }

    @Test
    void testABufferWithOneElementIsNotEmtpyAndNotFull() throws InterruptedException {
        var buffer = new Buffer(5);
        buffer.write(BigInteger.ONE);
        assertFalse(buffer.isEmpty());
        assertFalse(buffer.isFull());
    }

    @Test
    void testABufferWithTwoElementIsFull() throws InterruptedException {
        var buffer = new Buffer(2);
        buffer.write(BigInteger.ONE);
        buffer.write(BigInteger.TEN);
        assertFalse(buffer.isEmpty());
        assertTrue(buffer.isFull());
    }

    @Test
    void testReadingAndWritingInBufferWorksLikeATail() throws InterruptedException {
        // qué nombre más pedorro para un test
        var buffer = new Buffer(3);
        buffer.write(BigInteger.ONE);
        buffer.write(BigInteger.TEN);
        assertEquals(BigInteger.ONE, buffer.read());

        buffer.write(BigInteger.ZERO);
        assertEquals(BigInteger.TEN, buffer.read());

        buffer.write(BigInteger.TWO);
        buffer.write(BigInteger.ONE);
        assertEquals(BigInteger.ZERO, buffer.read());

        buffer.write(BigInteger.ONE);
        assertEquals(BigInteger.TWO, buffer.read());

        assertFalse(buffer.isFull());
    }

    // ¿cómo testeo que se hace el wait()? yahoo respuestas
}