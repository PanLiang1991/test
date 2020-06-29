package com.test.pan;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class FixedSizeThreadPool {

    private BlockingQueue<Runnable> blockingQueue;

    private List<Thread> workers;

    public static class Worker extends Thread {

        private FixedSizeThreadPool pool;

        public Worker(FixedSizeThreadPool pool) {
            this.pool = pool;
        }

        @Override
        public void run() {
            while (true) {
                Runnable task = null;
                try {
                    task = this.pool.blockingQueue.take();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if (task != null) {
                    task.run();
                    System.out.println("线程" + Thread.currentThread().getName() + "执行完毕");
                }
            }
        }

    }

    public FixedSizeThreadPool(int poolSize, int taskSize) {
        if (poolSize <= 0 || taskSize <= 0) {
            throw new IllegalArgumentException("非法参数");
        }

        this.blockingQueue = new LinkedBlockingQueue<Runnable>(taskSize);
        this.workers = Collections.synchronizedList(new ArrayList<Thread>());

        for (int i = 0; i < poolSize; i++) {
            Worker worker = new Worker(this);
            worker.start();
            workers.add(worker);
        }
    }

    public boolean submit(Runnable task) {
        return this.blockingQueue.offer(task);
    }


    public static void main(String[] args) {

    }
}
