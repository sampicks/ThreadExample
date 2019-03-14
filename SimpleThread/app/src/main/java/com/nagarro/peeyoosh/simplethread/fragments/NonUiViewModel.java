package com.nagarro.peeyoosh.simplethread.fragments;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.os.AsyncTask;

public class NonUiViewModel extends ViewModel {

    MutableLiveData<Integer> progressValue = new MutableLiveData<>();
    private ProgressTask progressTask;

    public void startTask(){
        if(progressTask==null){
            progressTask = new ProgressTask();
            progressTask.execute();
        }
//        else if(progressTask.getStatus()!= AsyncTask.Status.FINISHED){
//
//        }
    }

    private class ProgressTask extends AsyncTask<Void, Integer, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            for (int i = 0; i <= 100; i++) {
                try {
                    Thread.sleep(60);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                publishProgress(i);
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            progressValue.setValue(values[0]);
        }
    }
}
