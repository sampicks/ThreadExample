package com.nagarro.peeyoosh.simplethread.threads;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;

public class SimpleWorker extends Thread {
    private static final String TAG = "SimpleWorker";

    private AtomicBoolean flag = new AtomicBoolean(true);
    private ConcurrentLinkedQueue<Runnable> linkedQueue = new ConcurrentLinkedQueue<>();

    public SimpleWorker() {
        super(TAG);
        start();
    }

    @Override
    public void run() {
        while (flag.get()) {
            Runnable runnable = linkedQueue.poll();
            if (runnable != null) runnable.run();
            else quit();
        }
    }

    public SimpleWorker execute(Runnable runnable) {
        linkedQueue.add(runnable);
        return this;
    }

    public void quit() {
        flag.set(false);
    }
}
