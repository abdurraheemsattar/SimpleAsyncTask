package com.example.simpleasynctask;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    TextView mTextView;

    //for state of the textview
    String text_state = "current text";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize mTextView
        mTextView = (TextView) findViewById(R.id.textView1);

        //Restore TextView if there is a savedInstanceState
        if (savedInstanceState!=null){
            mTextView.setText(savedInstanceState.getString(text_state));
        }

    }

    public void startTask(View view) {

        //Put a message in the text view
        mTextView.setText(R.string.napping);
        // Start the AsyncTask.
        // The AsyncTask has a callback that will update the text view.
        new SimpleAsyncTask(mTextView).execute();

    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        // Save the state of the TextView
        outState.putString(text_state, mTextView.getText().toString());

    }
}