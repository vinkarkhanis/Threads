public class MultiThreading {
    public static void main(String[] args) throws InterruptedException {

        Thread t1 = new Thread(() -> {
            for (int i = 1; i <= 5; i++) {
                System.out.println("Hi");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

            }
        });
        Thread t2 = new Thread(() -> {
            for (int i = 1; i <= 5; i++) {
                System.out.println("Hello");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        /*Priority of a Thread:
        Thread priority is a number between 1 and 10 that reflects the priority of the given thread to execute.
        1 being least priority and 10 is highest*/
        t1.setPriority(Thread.MIN_PRIORITY);
        t2.setPriority(Thread.MAX_PRIORITY);
        System.out.println(t1.getPriority());
        System.out.println(t2.getPriority());


        t1.start();
        Thread.sleep(10);
        t2.start();
        System.out.println(t1.isAlive());
        t1.join();
        t2.join();//after join thread becomes dead and joins main thread
        System.out.println(t1.isAlive());
        System.out.println("bye");
    }
}
