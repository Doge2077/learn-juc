public class Test02 {

    static int num;

    public static void main(String args[]) throws InterruptedException {
        Thread thread1 = new Thread(() -> {
            for (int i = 1; i <= 1000000; i ++) {
                num ++;
            }
        });

        Thread thread2 = new Thread(() -> {
            for (int i = 1; i <= 1000000; i ++) {
                num ++;
            }
        });

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        System.out.println(num);
    }
}
