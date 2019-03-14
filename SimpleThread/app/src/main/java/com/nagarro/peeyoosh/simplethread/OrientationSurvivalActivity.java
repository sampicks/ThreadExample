package com.nagarro.peeyoosh.simplethread;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;

import com.nagarro.peeyoosh.simplethread.fragments.NonUIFragment;

public class OrientationSurvivalActivity extends AppCompatActivity {

    private NonUIFragment nonUIFragment;

    private ProgressBar progressBar1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orientation_survival);

        progressBar1 = findViewById(R.id.progressBar1);

        if (savedInstanceState == null) {
            nonUIFragment = NonUIFragment.newInstance();
            getSupportFragmentManager().beginTransaction().add(nonUIFragment, "NonUIFragment").commit();
        } else {
            nonUIFragment = (NonUIFragment) getSupportFragmentManager().findFragmentByTag("NonUIFragment");
        }
    }

    public void setProgressValue(int progress) {
        progressBar1.setProgress(progress);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            progressBar1.setTooltipText("Progress"+progress);
        }
    }
}
