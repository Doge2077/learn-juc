# 线程池

****

## 线程回顾

****

创建线程的方式

- 继承 Thread 类
- 实现 Runnable 接口

创建后的线程有如下状态：

NEW：新建的线程，无任何操作

```java
public static void main(String[] args) {
        Thread thread = new Thread(() -> System.out.println(Thread.currentThread().getName() + "is running"));
        Thread.State state = thread.getState();
        // 线程刚创建还未执行，状态为 NEW
        System.out.println(state);
    }
```

RUNNABLE：可执行的线程，在 JVM 中执行但是在等待操作系统的资源

```java
public static void main(String[] args) {
    Thread thread = new Thread(() -> System.out.println(Thread.currentThread().getName() + "is running"));
    thread.start();
    Thread.State state = thread.getState();
    // 调用 start() 方法,可以执行，但不代表一定在执行
    System.out.println(state);
}
```

BOLCKED：阻塞，获取不到锁

```java
public static void main(String[] args) {
        Thread thread1 = new Thread(() -> {
            synchronized (Test.class) {
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread thread2 = new Thread(() -> {
            synchronized (Test.class) {
            }
        });
        thread1.start();
        thread2.start();
        // 等待，保证 线程1 开始执行
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Thread.State state = thread2.getState();
        System.out.println(state);
    }
```

WAITING：等待，等待其他线程进行操作，时间不确定

```java
public static void main(String[] args) {
        Thread thread = new Thread(LockSupport::park);
        thread.start();
        // 等待线程开始执行
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Thread.State state = thread.getState();
        System.out.println(state);

        // 等待状态解除禁止，线程执行完毕
        LockSupport.unpark(thread);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        state = thread.getState();
        System.out.println(state);
    }
```

TIMED_WAITING：等待，等待的时间是确定的

```java
public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        thread.start();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Thread.State state = thread.getState();
        System.out.println(state);
    }
```

TERMINATED：终止，线程已经运行完毕

```java
public static void main(String[] args) {
        Thread thread = new Thread(() -> System.out.println(Thread.currentThread().getName() + "is running"));
        thread.start();
        try {
            // 等待线程执行完毕
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Thread.State state = thread.getState();
        System.out.println(state);
    }
```

****

## 线程池设计思想

****

上述创建线程的方式存在如下缺陷：

- 线程使用完后会被销毁，高并发场景下频繁创建和销毁线程的性能开销不可忽略
- 无法控制线程并发数量，线程过多会导致 JVM 宕机

线程池是一种池化思想