package com.nagarro.peeyoosh.simplethread.threads;

import android.os.Handler;
import android.os.Message;

import com.nagarro.peeyoosh.simplethread.MainActivity;


public class ProgressRunnable implements Runnable {


    private Handler mainThreadHandler;

    public ProgressRunnable(Handler handler) {
        this.mainThreadHandler = handler;
    }

    @Override
    public void run() {

        for (int i = 0; i < 100; i++) {
            Message message = Message.obtain();
            message.arg1 = MainActivity.THREAD_TWO;
            message.arg2 = i;
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            mainThreadHandler.sendMessage(message);
        }
    }
}
