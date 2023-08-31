package utfpr.aulathread;

public class App {

    public static void main(String[] args) throws InterruptedException {
        int[] array = {1, 2, 3, 4, 5};//15
        ExemploRunnable r1 = new ExemploRunnable("#1", array);
        ExemploRunnable r2 = new ExemploRunnable("#2", array);

        //ExemploThread t1 = new ExemploThread("Thread-1",100);
        //ExemploThread t2 = new ExemploThread("Thread-2",200);
        
        //ExemploRunnable r1 = new ExemploRunnable("Thread-1",100);
        //ExemploRunnable r2 = new ExemploRunnable("Thread-2",200);
        //ExemploRunnable r3 = new ExemploRunnable("Thread-3",300);
        
        //Thread t1 = new Thread(r1);
        //Thread t2 = new Thread(r2);
        //Thread t3 = new Thread(r3);
        
        //t1.start();
        //t2.start();
        //t3.start();
        
        //t1.setPriority(1);
        //t2.setPriority(5);
        //t3.setPriority(10);
        
        //t1.join();
        //t2.join();
        //t3.join();
    }
}
