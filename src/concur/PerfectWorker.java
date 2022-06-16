package concur;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class PerfectWorker extends Thread {
    // Una clase PerfectWorker que extiende de Thread y realiza la verificación de si un
    //número es perfecto o no. Un PerfectWorker debe tomar los números a verificar
    //de un Buffer conocido al momento de su creación.

    private Buffer buffer;
    private Founded founded;

    public PerfectWorker (Buffer buffer, Founded founded){
        this.buffer = buffer;
        this.founded = founded;
    }

    @Override
    public void run() {

        while (!this.founded.isComplete()) {
            try {
                var candidate = this.buffer.next();
                if (this.isPerfectNumber(candidate))
                    System.out.println("número perfecto encontrado");
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
