public class Test02 {

    static int num;

    public static void main(String args[]) {
        new Thread(() -> {
            for (int i = 1; i <= 100000; i ++) {
                num ++;
            }
            try {
                Thread.currentThread().join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            for (int i = 1; i <= 100000; i ++) {
                num ++;
            }
            try {
                Thread.currentThread().join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        System.out.println(num);
    }
}
