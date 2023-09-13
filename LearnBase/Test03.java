public class Test03 {

    static int num;

    public static void main(String args[]) {
        Object lock = new Object();
        new Thread(() -> {
            synchronized (lock) {
                for (int i = 1; i <= 100000; i ++) {
                    num ++;
                }
            };
        }).start();

        new Thread(() -> {
            synchronized (lock) {
                for (int i = 1; i <= 100000; i ++) {
                    num ++;
                }
            };
        }).start();

        System.out.println(num);
    }
}
