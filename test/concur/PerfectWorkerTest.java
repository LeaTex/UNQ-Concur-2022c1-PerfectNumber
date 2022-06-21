package concur;

import org.junit.jupiter.api.Test;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PerfectWorkerTest {

    @Test
    void testOneIsNotPerfectNumber() {
        PerfectWorker worker = new PerfectWorker(new Buffer(1), new Buffer(1));
        assertFalse(worker.isPerfectNumber(BigInteger.ONE));
    }

    @Test
    void testSixIsPerfectNumber() {
        PerfectWorker worker = new PerfectWorker(new Buffer(1), new Buffer(1));
        assertTrue(worker.isPerfectNumber(BigInteger.valueOf(6)));
    }

    @Test
    void testFiftyIsNotPerfectNumber() {
        PerfectWorker worker = new PerfectWorker(new Buffer(1), new Buffer(1));
        assertFalse(worker.isPerfectNumber(BigInteger.valueOf(50)));
    }

    @Test
    void testOneHasNotProperDivisors() {
        PerfectWorker worker = new PerfectWorker(new Buffer(1), new Buffer(1));
        assertTrue(worker.properDivisors(BigInteger.ONE).isEmpty());
    }

    @Test
    void testTenHasOneTwoAndFiveAsProperDivisors() {
        PerfectWorker worker = new PerfectWorker(new Buffer(1), new Buffer(1));
        List<BigInteger> divisors = worker.properDivisors(BigInteger.valueOf(10));
        assertEquals(3 , divisors.size());
        assertIterableEquals(Arrays.asList(BigInteger.ONE, BigInteger.valueOf(2), BigInteger.valueOf(5)), divisors);
    }

    @Test
    void testAliquotSumOfOneIsZero() {
        PerfectWorker worker = new PerfectWorker(new Buffer(1), new Buffer(1));
        assertEquals(BigInteger.ZERO, worker.aliquotSum(BigInteger.ONE));
    }

    @Test
    void testAliquotSumOfSixIsSix() {
        PerfectWorker worker = new PerfectWorker(new Buffer(1), new Buffer(1));
        assertEquals(BigInteger.valueOf(6), worker.aliquotSum(BigInteger.valueOf(6)));
    }

    @Test
    void testAliquotSumOfTenIsEight() {
        PerfectWorker worker = new PerfectWorker(new Buffer(1), new Buffer(1));
        assertEquals(BigInteger.valueOf(8), worker.aliquotSum(BigInteger.valueOf(10)));
    }

    @Test
    void testPerfectWorkerFindsOnePerfectNumber() throws InterruptedException {
        Buffer buffer = new Buffer(2);
        buffer.write(BigInteger.ONE);
        buffer.write(BigInteger.valueOf(6));
        Buffer founded = new Buffer(1);
        PerfectWorker worker = new PerfectWorker(buffer, founded);
        worker.start();
        buffer.write(BigInteger.valueOf(-1));

        assertEquals(BigInteger.valueOf(6), founded.read());
    }

    @Test
    void testPerfectWorkerFindsTwoPerfectsNumbers() throws InterruptedException {
        Buffer buffer = new Buffer(5);
        buffer.write(BigInteger.ONE);
        buffer.write(BigInteger.valueOf(6));
        buffer.write(BigInteger.valueOf(100));
        buffer.write(BigInteger.valueOf(496));
        buffer.write(BigInteger.valueOf(1000));
        Buffer founded = new Buffer(2);
        PerfectWorker worker = new PerfectWorker(buffer, founded);
        worker.start();

        buffer.write(BigInteger.valueOf(-1));

        assertEquals(BigInteger.valueOf(6), founded.read());
        assertEquals(BigInteger.valueOf(496), founded.read());
    }

    @Test
    void testTwoWorkerFindTwoPerfectsNumbers() throws InterruptedException {
        Buffer buffer = new Buffer(5);
        buffer.write(BigInteger.valueOf(7500));
        buffer.write(BigInteger.valueOf(8000));
        buffer.write(BigInteger.valueOf(8128));
        buffer.write(BigInteger.valueOf(33550336));

        Buffer founded = new Buffer(2);

        PerfectWorker workerOne = new PerfectWorker(buffer, founded);
        PerfectWorker workerTwo = new PerfectWorker(buffer, founded);
        workerOne.start();
        workerTwo.start();

        buffer.write(BigInteger.valueOf(-1));
        buffer.write(BigInteger.valueOf(-1));

        List<BigInteger> expected = Arrays.asList(founded.read(), founded.read());

        assertTrue(expected.contains(BigInteger.valueOf(8128)));
        assertTrue(expected.contains(BigInteger.valueOf(33550336)));
    }
}