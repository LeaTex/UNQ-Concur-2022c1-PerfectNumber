package concur;

import java.math.BigInteger;

public class Buffer {

    private BigInteger[] data;
    private int begin = 0, end = 0;

    public Buffer(int size){
        this.data = new BigInteger[size+1];
    }

    synchronized BigInteger read () throws InterruptedException {
        while (this.isEmpty ()) { wait (); }
        BigInteger result = data[end];
        end = this.next(end);
        notifyAll ();
        return result;
    }

    synchronized void write(BigInteger number) throws InterruptedException {
        while (this.isFull ()) { wait (); }
        data[begin] = number;
        begin = this.next(begin);
        notifyAll ();
    }

    public boolean isEmpty () { return begin == end; }

    public boolean isFull () { return next(begin) == end; }

    public int next(int i) { return (i+1) % (data.length); }
}
