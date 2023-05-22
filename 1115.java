class FooBar {
    private int n;
    Lock lock;
    Condition canFooCondition, canBarCondition;
    boolean canFoo, canBar;

    public FooBar(int n) {
        this.n = n;
        lock = new ReentrantLock();
        canFooCondition = lock.newCondition();
        canBarCondition = lock.newCondition();
        canFoo = true;
        canBar = false;
    }

    public void foo(Runnable printFoo) throws InterruptedException {
        try {
            lock.lock();
            for (int i = 0; i < n; i++) {
                // printFoo.run() outputs "foo". Do not change or remove this line.
                printFoo.run();
                System.out.print("Foo:" + i + " ");
                canFoo = false;
                canBar = true;
                canBarCondition.signal();
                while (canFoo == false) {
                    canFooCondition.await();
                }
            }
            canBar = true;
            canFoo = false;
            canBarCondition.signal();
        } finally {
            lock.unlock();
        }
    }

    public void bar(Runnable printBar) throws InterruptedException {
        
        try {
            lock.lock();
            // In case the 1st thread that is invoked is this one.
            while (canBar == false) {
                canBarCondition.await();
            }
            for (int i = 0; i < n; i++) {
                // printBar.run() outputs "bar". Do not change or remove this line.
                printBar.run();
                System.out.print("Bar:" + i + " ");
                canBar = false;
                canFoo = true;
                canFooCondition.signal();
                while (canBar == false) {
                    canBarCondition.await();
                }
            }
        } finally {
            lock.unlock();
        }
    }
}
