import java.util.concurrent.locks.LockSupport;

class Test {
    public static void main(String[] args) {
        Thread thread = new Thread(() -> System.out.println(Thread.currentThread().getName() + "is running"));
        thread.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Thread.State state = thread.getState();
        System.out.println(state);
    }
}