package concur;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class PerfectWorker extends Thread {

    private Buffer buffer;
    private Buffer founded;

    public PerfectWorker (Buffer buffer, Buffer founded){
        this.buffer = buffer;
        this.founded = founded;
    }

    @Override
    public void run() {
        while (!this.founded.isFull()) {
            try {
                var candidate = this.buffer.read();
                if (this.isPerfectNumber(candidate)) {
                    System.out.println("Worker PID "+ Thread.currentThread().getId() + " found perfect number " + candidate);
                    founded.write(candidate);}
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public List<BigInteger> properDivisors(BigInteger number) {
        List<BigInteger> properDivisors = new ArrayList<>();

        if (number.compareTo(BigInteger.ONE) == 0) {
            return properDivisors;
        }

        BigInteger candidate = BigInteger.valueOf(2);
        BigInteger biggestDivisor = number.sqrt();

        properDivisors.add(BigInteger.ONE);
        while (candidate.compareTo(biggestDivisor) != 1) {  // (candidate <= biggestDivisor) OR !(candidate > biggestDivisor)
            if (number.mod(candidate).compareTo(BigInteger.ZERO) == 0) {
                properDivisors.add(candidate);
                properDivisors.add(number.divide(candidate));
            }
            candidate = candidate.add(BigInteger.ONE);
        }

        return properDivisors;
    }

    public boolean isPerfectNumber(BigInteger number) {
        return this.aliquotSum(number).equals(number);
    }

    public BigInteger aliquotSum(BigInteger number) {
        return this.properDivisors(number).stream().reduce(BigInteger.ZERO, BigInteger::add);
    }
}
