package com.example.simpleasynctask;

import android.os.AsyncTask;
import android.widget.TextView;

import java.util.Random;

public class SimpleAsyncTask extends AsyncTask<Void, Void, String> {

    TextView mTextView;

    public SimpleAsyncTask(TextView tv){
        mTextView = tv;
    }



    @Override
    protected String doInBackground(Void... voids) {

        //Generating random number between 0 - 10
        Random r = new Random();
        int n = r.nextInt(11);

        // Make the task take long enough that we have
        // time to rotate the phone while it is running
        int s = n*200;

        // Sleep for the random amount of time
        try {
            Thread.sleep(s);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return "Awake after sleeping for" +s+ "milliseconds";

    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);

        mTextView.setText(result);
    }


}


























