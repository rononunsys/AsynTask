package com.android.curso.asyntask;

import android.os.AsyncTask;

public class ProgressTask extends AsyncTask<Integer,Integer,Void> {
    private final MainActivity activity;

    public ProgressTask(MainActivity activity) {
        this.activity = activity;
    }

    @Override
    protected Void doInBackground(Integer... integers) {
        int progress = integers [0];
        while (!isCancelled()&&progress <100){
            progress++;
            publishProgress(progress);

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        int progress = values [0];
        this.activity.setProgres(progress);
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        this.activity.sendMessage();
    }
}
