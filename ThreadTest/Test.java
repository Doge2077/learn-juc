class Test {
    public static void main(String[] args) {
        Thread thread = new Thread(() -> System.out.println(Thread.currentThread().getName() + "is running"));
        Thread.State state = thread.getState();
        // 线程刚创建还未执行，状态为 NEW
        System.out.println(state);
    }
}