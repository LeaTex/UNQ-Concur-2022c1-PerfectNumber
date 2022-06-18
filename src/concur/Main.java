package concur;

import java.math.BigInteger;

public class Main implements Runnable{
//    Una clase Main con el punto de entrada del programa, tiene la responsabilidad de
//    inicializar las estructuras mencionadas a continuación y producir números enteros
//    grandes (BigInteger) consecutivos para que múltiples threads verifiquen si son
//    perfectos o no. El programa debe terminar únicamente cuando la cantidad de
//    números perfectos deseada haya sido alcanzada, y finalmente informar el tiempo
//    transcurrido desde el inicio de la ejecución.

    public Buffer buffer;
    public Buffer founded;

    public Main(int bufferSize, int threadsQuantity, int numbersQuntity){
        this.buffer = new Buffer(bufferSize);
        this.founded = new Buffer(numbersQuntity);
    }

    // Al iniciar el programa la clase Main debe delegar la iniciación de los threads necesarios
    //en la clase ThreadPool y luego introducir de a uno los números a verificar en el Buffer.
    //Cada PerfectWorker en funcionamiento debe tomar números de a uno del Buffer y
    //verificar si son o no perfectos. Cabe destacar que es inadmisible utilizar una cantidad
    //de threads menor a la solicitada por el usuario.

    public static void main(String[] args) {
        Runnable proceso1 = new Main(6, 2, 2);
        new Thread(proceso1).start();
    }

    @Override
    public void run() {
        long initialTime = System.currentTimeMillis();

        var number = BigInteger.ONE;
        while (!this.founded.isFull()) {
            try {
                number = number.add(BigInteger.ONE);
                this.buffer.write(number);

                // TODO: eliminar las siguientes líneas
                Thread.sleep(500);
                this.founded.write(number);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        long endTime = System.currentTimeMillis();
        System.out.println("Tiempo empleado " + (endTime - initialTime) + " milisegundos");
        System.out.println(this.founded);
    }
}
