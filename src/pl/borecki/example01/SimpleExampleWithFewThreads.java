package pl.borecki.example01;

/**
 * Created by michalborecki on 22/11/19.
 */

public class SimpleExampleWithFewThreads {
    public static void main(String[] args) {

        NewThread nt1 = new NewThread("FIRST");
        NewThread nt2 = new NewThread("SECOND");
        NewThread nt3 = new NewThread("THIRD");
        NewThread nt4 = new NewThread("FOURTH");

        System.out.println("Thread " + nt1.newThread.getName() + " " + nt1.newThread.isAlive());
        System.out.println("Thread " + nt2.newThread.getName() + " " + nt2.newThread.isAlive());
        System.out.println("Thread " + nt3.newThread.getName() + " " + nt3.newThread.isAlive());
        System.out.println("Thread " + nt4.newThread.getName() + " " + nt4.newThread.isAlive());

        try {
            nt1.newThread.join();
            nt2.newThread.join();
            nt3.newThread.join();
            nt4.newThread.join();
        } catch (InterruptedException e) {
            System.out.println("Main thread has been interrupted!");
        }
        System.out.println("Exiting main thread");

        System.out.println("Thread " + nt1.newThread.getName() + " " + nt1.newThread.isAlive());
        System.out.println("Thread " + nt2.newThread.getName() + " " + nt2.newThread.isAlive());
        System.out.println("Thread " + nt3.newThread.getName() + " " + nt3.newThread.isAlive());
        System.out.println("Thread " + nt4.newThread.getName() + " " + nt4.newThread.isAlive());
    }
}

class NewThread implements Runnable {

    Thread newThread;
    private String name;

    NewThread(String name) {
        this.name = name;
        newThread = new Thread(this, name);
        newThread.start();
    }

    @Override
    public void run() {
        System.out.println("---Starting thread " + name);

        try {
            System.out.println("---Sleeping thread " + name);
            Thread.sleep(2000);
            System.out.println("---Waking up thread " + name);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("---Exiting thread " + name);
    }
}

