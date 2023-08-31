class Q{
    int num;
    boolean valuSet = false;
    //wait and notify are used to communicate between two threads and both are from OBJECT class
    public synchronized void put(int num){
        while(valuSet){
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println("Put"+num);
        this.num=num;
        valuSet=true;
        notify();
    }
    public synchronized void get(){
        while(!valuSet){
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println("Get" +num);
        valuSet=false;
        notify();
    }
}

class Producer implements Runnable{
    Q q;
    public Producer(Q q){
        this.q=q;
        Thread t =new Thread(this,"Producer");
        t.start();
    }
    public void run(){
        int i=0;
        while(true){
            q.put(i++);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

class Consumer implements Runnable{
    Q q;
    public Consumer(Q q){
        this.q=q;
        Thread t2 = new Thread(this,"Consumer");
        t2.start();
    }
    public void run(){
        while(true){
            q.get();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

public class Interthread {

    public static void main(String[] args) {
        Q q = new Q();
        new Producer(q);
        new Consumer(q);

    }
}
