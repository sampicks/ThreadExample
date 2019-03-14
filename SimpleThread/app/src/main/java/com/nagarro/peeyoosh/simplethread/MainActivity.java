package com.nagarro.peeyoosh.simplethread;

import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.nagarro.peeyoosh.simplethread.threads.ProgressRunnable;
import com.nagarro.peeyoosh.simplethread.threads.SimpleWorker;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    public static final int THREAD_ONE = 1;
    public static final int THREAD_TWO = 2;
    public static final int THREAD_THREE = 3;
    private TextView textView;
    private ProgressBar progressBar;

    private ProgressBar progressBar2;
    private Handler handler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.arg1) {
                case THREAD_ONE:
                    textView.setText((String) msg.obj);
                    break;
                case THREAD_TWO:
                    progressBar.setProgress(msg.arg2);
                    break;
                case THREAD_THREE:
                    progressBar2.setProgress(msg.arg2);
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.text);
        progressBar = findViewById(R.id.progressBar);
        progressBar2 = findViewById(R.id.progressBar2);


        Thread thread = new Thread(new ProgressRunnable(handler));
        thread.start();

        Thread thread2 = new Thread(new ProgressRunnable2());
        thread2.start();

    }

    public void openSecondActivity(View view) {

        startActivity(new Intent(this, SecondActivity.class));
    }

    public void openAsyncTaskActivity(View view) {
        startActivity(new Intent(this, AsynctaskActivity.class));
    }

    public void openOrientationSurvivalActivity(View view) {
        startActivity(new Intent(this, OrientationSurvivalActivity.class));
    }

    class ProgressRunnable2 implements Runnable {


        @Override
        public void run() {

            for (int i = 0; i < 100; i++) {
                Message message = Message.obtain();
                message.arg1 = MainActivity.THREAD_THREE;
                message.arg2 = i;
                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                handler.sendMessage(message);
            }
        }
    }
}
