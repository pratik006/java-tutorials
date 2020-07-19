package apple;

import java.util.concurrent.atomic.AtomicInteger;

public class AddNumbers {

    public static void main(String[] args) throws InterruptedException {
        AddNumbers addNumbers = new AddNumbers();
        System.out.println("Result: "+addNumbers.addNos(1, 100) + " vs "+(100*101/2));
    }

    public int addNos(int start, int end) throws InterruptedException {
        AtomicInteger finalRes = new AtomicInteger(0);

        Thread t1 = new Thread(new Task(start, (start+end)/2, finalRes));
        t1.start();

        Thread t2 = new Thread(new Task(((start+end)/2)+1, end, finalRes));
        t2.start();

        t1.join();
        t2.join();

        return finalRes.get();
    }

    public class Task implements Runnable {
        private int start;
        private int end;
        private AtomicInteger finalRes;

        public Task(int start, int end, AtomicInteger finalRes) {
            this.start = start;
            this.end = end;
            this.finalRes = finalRes;
        }


        @Override
        public void run() {
            int res = 0;
            for (int i=start;i<=end;i++) {
                res += i;
            }

            synchronized (finalRes) {
                finalRes.getAndAdd(res);
            }
        }
    }
}
