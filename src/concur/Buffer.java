package concur;

import java.math.BigInteger;

public class Buffer {
    //Una clase Buffer (implementada como un monitor utilizando métodos synchroni-
    //zed) que actúa como una cola FIFO concurrente de capacidad acotada. Es decir,
    //bloquea a un lector intentando sacar un elemento cuando está vacı́a y bloquea a
    //un productor intentando agregar un elemento cuando está llena. La capacidad del
    //Buffer también debe ser un parámetro configurable desde la clase Main.

    public BigInteger next() {
        return BigInteger.ONE;
    }
}
