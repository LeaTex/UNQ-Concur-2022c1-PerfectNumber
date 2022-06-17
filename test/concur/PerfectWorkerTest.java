package concur;

import org.junit.jupiter.api.Test;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PerfectWorkerTest {

    @Test
    void testOneIsNotPerfectNumber() {
        var worker = new PerfectWorker(new Buffer(1), new Buffer(1));
        assertFalse(worker.isPerfectNumber(BigInteger.ONE));
    }

    @Test
    void testSixIsPerfectNumber() {
        var worker = new PerfectWorker(new Buffer(1), new Buffer(1));
        assertTrue(worker.isPerfectNumber(BigInteger.valueOf(6)));
    }

    @Test
    void testFiftyIsNotPerfectNumber() {
        var worker = new PerfectWorker(new Buffer(1), new Buffer(1));
        assertFalse(worker.isPerfectNumber(BigInteger.valueOf(50)));
    }

    @Test
    void testOneHasNotProperDivisors() {
        var worker = new PerfectWorker(new Buffer(1), new Buffer(1));
        assertTrue(worker.properDivisors(BigInteger.ONE).isEmpty());
    }

    @Test
    void testTenHasOneTwoAndFiveAsProperDivisors() {
        var worker = new PerfectWorker(new Buffer(1), new Buffer(1));
        var divisors = worker.properDivisors(BigInteger.valueOf(10));
        assertEquals(3 , divisors.size());
        assertIterableEquals(Arrays.asList(BigInteger.ONE, BigInteger.valueOf(2), BigInteger.valueOf(5)), divisors);
    }

    @Test
    void testAliquotSumOfOneIsZero() {
        var worker = new PerfectWorker(new Buffer(1), new Buffer(1));
        assertEquals(BigInteger.ZERO, worker.aliquotSum(BigInteger.ONE));
    }

    @Test
    void testAliquotSumOfSixIsSix() {
        var worker = new PerfectWorker(new Buffer(1), new Buffer(1));
        assertEquals(BigInteger.valueOf(6), worker.aliquotSum(BigInteger.valueOf(6)));
    }

    @Test
    void testAliquotSumOfTenIsEight() {
        var worker = new PerfectWorker(new Buffer(1), new Buffer(1));
        assertEquals(BigInteger.valueOf(8), worker.aliquotSum(BigInteger.valueOf(10)));
    }

    @Test
    void testPerfectWorkerFindsOnePerfectNumber() throws InterruptedException {
        var buffer = new Buffer(2);
        buffer.write(BigInteger.ONE);
        buffer.write(BigInteger.valueOf(6));
        var founded = new Buffer(1);
        var worker = new PerfectWorker(buffer, founded);
        worker.start();

        assertEquals(BigInteger.valueOf(6), founded.read());
    }

    @Test
    void testPerfectWorkerFindsTwoPerfectsNumbers() throws InterruptedException {
        var buffer = new Buffer(5);
        buffer.write(BigInteger.ONE);
        buffer.write(BigInteger.valueOf(6));
        buffer.write(BigInteger.valueOf(100));
        buffer.write(BigInteger.valueOf(496));
        buffer.write(BigInteger.valueOf(1000));
        var founded = new Buffer(2);
        var worker = new PerfectWorker(buffer, founded);
        worker.start();

        assertEquals(BigInteger.valueOf(6), founded.read());
        assertEquals(BigInteger.valueOf(496), founded.read());
    }

    @Test
    void testTwoWorkerFindTwoPerfectsNumbers() throws InterruptedException {
        var buffer = new Buffer(5);
        buffer.write(BigInteger.valueOf(7000));
        buffer.write(BigInteger.valueOf(7500));
        buffer.write(BigInteger.valueOf(8000));
        buffer.write(BigInteger.valueOf(8128));
        buffer.write(BigInteger.valueOf(33550336));
        var founded = new Buffer(2);
        var workerOne = new PerfectWorker(buffer, founded);
        var workerTwo = new PerfectWorker(buffer, founded);
        workerOne.start();
        workerTwo.start();

        List<BigInteger> expected = Arrays.asList(founded.read(), founded.read());

        assertTrue(expected.contains(BigInteger.valueOf(8128)));
        assertTrue(expected.contains(BigInteger.valueOf(33550336)));
    }
}