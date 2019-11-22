package pl.borecki.example02;

/**
 * Created by michalborecki on 22/11/19.
 */

class Write {
    void write(String message) {
        System.out.print("Start -> " + message);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.out.println("Thread has been interrupted!");
        }
        System.out.println(" -> End");
    }
}

class Writer implements Runnable {

    Thread t;
    Write w;
    String message;

    Writer(Write w, String message) {
        this.w = w;
        this.message = message;
        t = new Thread(this);
        t.start();
    }

    @Override
    public void run() {
        // Synchronization of thread created on a base of object
        synchronized (w) {
            w.write(message);
        }
    }
}

public class ThreadsWithSynchronization {
    public static void main(String[] args) {

        Write write = new Write();
        Writer w1 = new Writer(write, "thread 1");
        Writer w2 = new Writer(write, "thread 2");
        Writer w3 = new Writer(write, "thread 3");

        try {
            w1.t.join();
            w2.t.join();
            w3.t.join();
        } catch (InterruptedException e) {
            System.out.println("Main thread has been interrupted!");
        }
        System.out.println("Exiting main thread");
    }
}