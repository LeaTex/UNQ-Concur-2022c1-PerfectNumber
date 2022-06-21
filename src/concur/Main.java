package concur;

import java.math.BigInteger;

public class Main implements Runnable{

    public Buffer buffer;
    public Buffer founded;
    public ThreadPool threadPool;

    public Main(int bufferSize, int threadsQuantity, int numbersQuntity){
        this.buffer = new Buffer(bufferSize);
        this.founded = new Buffer(numbersQuntity);
        this.threadPool = new ThreadPool(threadsQuantity, this.buffer, this.founded);
    }

    public static void main(String[] args) {
        Runnable proceso1 = new Main(6, 5, 4);
        new Thread(proceso1).start();
    }

    @Override
    public void run() {
        long initialTime = System.currentTimeMillis();

        this.threadPool.launch();

        BigInteger number = BigInteger.ONE;
        while (!this.founded.isFull()) {
            try {
                number = number.add(BigInteger.ONE);

                /* DEBUG mode
                if (number.mod(BigInteger.valueOf(100)).compareTo(BigInteger.ZERO) == 0) {
                    System.out.println("Número " + number.toString());
                }*/

                this.buffer.write(number);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.threadPool.stop();

        long endTime = System.currentTimeMillis();
        System.out.println("Tiempo empleado " + (endTime - initialTime) + " milisegundos");
        System.out.println(this.founded);
    }
}
