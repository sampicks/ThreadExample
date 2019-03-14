package com.nagarro.peeyoosh.simplethread;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ProgressBar;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class SecondActivity extends AppCompatActivity {

    private static final String TAG = "SecondActivity";
    private ProgressBar progressBar1;
    private ProgressBar progressBar2;
    private ProgressBar progressBar3;
    private ProgressBar progressBar4;
    private ProgressBar progressBar5;
    private ProgressBar progressBar6;
    private ProgressBar progressBar7;
    private ProgressBar progressBar8;

    private ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    Random r = new Random();
    int minTime = 5;
    int maxTime = 70;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        progressBar1 = findViewById(R.id.progressBar1);
        progressBar2 = findViewById(R.id.progressBar2);
        progressBar3 = findViewById(R.id.progressBar3);
        progressBar4 = findViewById(R.id.progressBar4);
        progressBar5 = findViewById(R.id.progressBar5);
        progressBar6 = findViewById(R.id.progressBar6);
        progressBar7 = findViewById(R.id.progressBar7);
        progressBar8 = findViewById(R.id.progressBar8);

        submitTask();
    }

    private void submitTask() {
        executorService.submit(new ProgressbarRunnable(progressBar1));
        executorService.submit(new ProgressbarCallable(progressBar2));
        executorService.submit(new ProgressbarRunnable(progressBar3));
        executorService.submit(new ProgressbarCallable(progressBar4));
        executorService.submit(new ProgressbarRunnable(progressBar5));
        executorService.submit(new ProgressbarCallable(progressBar6));
        executorService.submit(new ProgressbarRunnable(progressBar7));
        Future<String> callableResult =executorService.submit(new ProgressbarCallable(progressBar8));
//        try {
//            Log.e(TAG,"===== callable 8 returned value : "+callableResult.get());
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        executorService.shutdown();
        try {
            executorService.awaitTermination(60, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    class ProgressbarRunnable implements Runnable {

        ProgressBar progressBar;

        ProgressbarRunnable(ProgressBar bar) {
            progressBar = bar;
        }

        @Override
        public void run() {
            Log.w(TAG, "=== Current Thread : " + Thread.currentThread().getName());
            int sleepTime = r.nextInt(maxTime - minTime) + minTime;
            for (int i = 0; i < 100; i++) {
                try {
                    Thread.sleep(sleepTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                progressBar.setProgress(i);
            }
        }
    }

    class ProgressbarCallable implements Callable<String> {

        ProgressBar progressBar;

        ProgressbarCallable(ProgressBar bar) {
            progressBar = bar;
        }

        @Override
        public String call() throws Exception {
            Log.w(TAG, "=== Current Thread : " + Thread.currentThread().getName());
            int sleepTime = r.nextInt(maxTime - minTime) + minTime;
            for (int i = 0; i < 100; i++) {
                try {
                    Thread.sleep(sleepTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                progressBar.setProgress(i);
            }
            return "Completed";
        }
    }
}
