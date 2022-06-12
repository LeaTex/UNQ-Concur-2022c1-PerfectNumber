package concur;

import org.junit.jupiter.api.Test;
import java.math.BigInteger;
import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.*;


class PerfectWorkerTest {

    @Test
    void testOneIsNotPerfectNumber() {
        var worker = new PerfectWorker();
        assertFalse(worker.isPerfectNumber(BigInteger.ONE));
    }

    @Test
    void testSixIsPerfectNumber() {
        var worker = new PerfectWorker();
        assertTrue(worker.isPerfectNumber(BigInteger.valueOf(6)));
    }

    @Test
    void testFiftyIsNotPerfectNumber() {
        var worker = new PerfectWorker();
        assertFalse(worker.isPerfectNumber(BigInteger.valueOf(50)));
    }

    @Test
    void testOneHasNotProperDivisors() {
        var worker = new PerfectWorker();
        assertTrue(worker.properDivisors(BigInteger.ONE).isEmpty());
    }

    @Test
    void testTenHasOneTwoAndFiveAsProperDivisors() {
        var worker = new PerfectWorker();
        var divisors = worker.properDivisors(BigInteger.valueOf(10));
        assertEquals(3 , divisors.size());
        assertIterableEquals(Arrays.asList(BigInteger.ONE, BigInteger.valueOf(2), BigInteger.valueOf(5)), divisors);

    }

    @Test
    void testAliquotSumOfOneIsZero() {
        var worker = new PerfectWorker();
        assertEquals(BigInteger.ZERO, worker.aliquotSum(BigInteger.ONE));
    }

    @Test
    void testAliquotSumOfSixIsSix() {
        var worker = new PerfectWorker();
        assertEquals(BigInteger.valueOf(6), worker.aliquotSum(BigInteger.valueOf(6)));
    }

    @Test
    void testAliquotSumOfTenIsEight() {
        var worker = new PerfectWorker();
        assertEquals(BigInteger.valueOf(8), worker.aliquotSum(BigInteger.valueOf(10)));
    }

}