class ZeroEvenOdd {
    private int n;
    private Lock lock;
    private Condition condition0, condition1, condition2;
    private int current1, current2;
    private boolean canPrint0, canPrint1, canPrint2;
    private int lastPrinted;
    
    public ZeroEvenOdd(int n) {
        this.n = 2 * n;
        lock = new ReentrantLock();
        
        condition0 = lock.newCondition();
        condition1 = lock.newCondition();
        condition2 = lock.newCondition();
        
        current1 = 1;
        current2 = 2;
        
        canPrint0 = true;
        canPrint1 = false;
        canPrint2 = false;

        lastPrinted = 0;
        
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void zero(IntConsumer printNumber) throws InterruptedException {
        try {
            lock.lock();
            while (n > 0) {
                // wait till we can print 0 
                while (canPrint0 == false) {
                    condition0.await();
                }

                // print 0
                if (n > 0) {
                    printNumber.accept(0);
                    System.out.print(0);   
                }
                canPrint0 = false;
                n -= 1;
                if (lastPrinted % 2 == 0) {
                    canPrint1 = true;
                    condition1.signal();
                } else {
                    canPrint2 = true;
                    condition2.signal();
                }
            }
        } finally {
            lock.unlock();
        }
    }

    public void even(IntConsumer printNumber) throws InterruptedException {
        try {
            lock.lock();
            while (n > 0) {
                // wait till we can print even
                while (canPrint2 == false) {
                    condition2.await();
                }
                
                // print even
                if (n > 0) {
                    printNumber.accept(current2);
                    System.out.print(current2);
                }
                n -= 1;
                lastPrinted = current2;
                current2 += 2;
                canPrint2 = false;
                
                // print 0
                canPrint0 = true;
                condition0.signal();
            }
        } finally {
            lock.unlock();
        }
    }

    public void odd(IntConsumer printNumber) throws InterruptedException {
        try {
            lock.lock();
            while (n > 0) {
                // wait till we can print odd
                while (canPrint1 == false) {
                    condition1.await();
                }

                // print odd
                if (n > 0) {
                    printNumber.accept(current1);
                    System.out.print(current1);
                }
                n -= 1;
                lastPrinted = current1; 
                current1 += 2;
                canPrint1 = false;
                
                // print 0
                canPrint0 = true;
                condition0.signal();
            }
        } finally {
            lock.unlock();
        }
    }
}
