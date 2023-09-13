public class Tese04 {
    static int num;

//    static void addNum () {
//        num ++;
//    }

    static synchronized void addNum() {
        num ++;
    }

    public static void main(String args[]) throws InterruptedException {
        Thread thread1 = new Thread(() -> {
            for (int i = 1; i <= 1000000; i++) {
                addNum();
            }
        });

        Thread thread2 = new Thread(() -> {
            for (int i = 1; i <= 1000000; i++) {
                addNum();
            }
        });

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        System.out.println(num);
    }
}
