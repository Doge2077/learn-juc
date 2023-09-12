public class SleepSort {
    public static void main(String[] args) {
        int[] a = {4, 2, 10, 5, 3, 7, 6, 9, 8, 1};
        for (int i : a) {
            new Thread(() -> {
                try {
                    Thread.sleep(i * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(i);
            }).start();
        }
    }
}
