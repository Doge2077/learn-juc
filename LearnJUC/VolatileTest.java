public class VolatileTest {
    public static int ans01 = 0;
    public static volatile int ans02 = 0;

    public static void main(String[] args) {
        volatileTest01();
        volatileTest02();
        System.out.println(ans01);
        System.out.println(ans02);
    }

    public static void volatileTest01 () {
        new Thread(() -> {
            for (int i = 1; i <= 50000; i ++) {
                ans01 ++;
            }
        }).start();
        new Thread(() -> {
            for (int i = 1; i <= 50000; i ++) {
                ans01 ++;
            }
        }).start();
    }

    public static void volatileTest02 () {
        new Thread(() -> {
            for (int i = 1; i <= 50000; i ++) {
                ans02 ++;
            }
        }).start();
        new Thread(() -> {
            for (int i = 1; i <= 50000; i ++) {
                ans02 ++;
            }
        }).start();
    }
}
