package com.nagarro.peeyoosh.simplethread.threads;

import android.os.AsyncTask;
import android.widget.ArrayAdapter;

public class DataFillerTask extends AsyncTask<Void, String, String> {
    private String data[] = {"Based", "on", "your", "input,", "get", "a", "random", "alpha", "numeric", "string.",
            "The", "random", "string", "generator", "creates", "a", "series", "of", "numbers", "and", "letters",
            "that", "have", "no", "pattern.", "These", "can", "be", "helpful", "for", "creating", "security", "codes.",
            "The", "generation", "of", "this", "type", "of", "random", "string", "can", "be", "a", "common", "or",
            "typical", "task", "in", "computer", "programming.",
            "Some", "forms", "of", "randomness", "concern", "hash", "or", "seach", "algorithms.",
            "Another", "task", "that", "is", "random", "concerns", "selecting", "music", "tracks." };
    private ArrayAdapter<String> arrayAdapter;

    public DataFillerTask(ArrayAdapter<String> arrayAdapter) {
        this.arrayAdapter = arrayAdapter;
    }

    @Override
    protected String doInBackground(Void... voids) {
        for (String item : data) {
            publishProgress(item);
            try {
                Thread.sleep(80);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return "Completed";
    }

    @Override
    protected void onProgressUpdate(String... values) {
        arrayAdapter.add(values[0]);
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
    }
}
