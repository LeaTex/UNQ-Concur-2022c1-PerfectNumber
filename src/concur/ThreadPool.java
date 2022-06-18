package concur;

import java.math.BigInteger;

public class ThreadPool {

    private int threadsQuantity = 0;
    private Buffer buffer;
    private PerfectWorker[] workers;

    public ThreadPool(int threadsQuantity, Buffer buffer, Buffer founded) {
        this.threadsQuantity = threadsQuantity;
        this.buffer = buffer;
        this.workers = new PerfectWorker[threadsQuantity];

        this.initializePerfectWorkers(founded);
    }

    public void launch() {
        for (int i=0; i < this.threadsQuantity; i++) {
            this.workers[i].start();
        }
    };

    public void stop() {
        for (int i=0; i < this.threadsQuantity; i++) {
            try {
                this.buffer.write(BigInteger.valueOf(-1));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void initializePerfectWorkers(Buffer founded) {
        for (int i=0; i < this.threadsQuantity; i++) {
            this.workers[i] = new PerfectWorker(this.buffer, founded);
        }
    }
}
