package concur;

import java.math.BigInteger;
import java.util.Objects;

public class Buffer {

    private BigInteger[] data;
    private int begin = 0, end = 0;

    public Buffer(int size){
        this.data = new BigInteger[size+1];
    }

    public synchronized BigInteger read () throws InterruptedException {
        while (this.isEmpty ()) { wait (); }
        BigInteger result = data[end];
        end = this.next(end);
        notifyAll ();
        return result;
    }

    public synchronized void write(BigInteger number) throws InterruptedException {
        while (this.isFull ()) { wait (); }
        data[begin] = number;
        begin = this.next(begin);
        notifyAll ();
    }

    public boolean isEmpty () { return begin == end; }

    public boolean isFull () { return next(begin) == end; }

    public int next(int i) { return (i+1) % (data.length); }

    public String toString() {
        // TODO: esto se puede mejorar
        String toString = "[";
        for(int i = this.end; i < data.length; i++) {
            if (Objects.nonNull(data[i]))
                toString = toString + data[i].toString() + ", ";
        }
        toString = toString + "]";
        return toString;
    }
}
