package com.example.simpleasynctask;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class WhoWroteItActivity extends AppCompatActivity {

    EditText mBookInput;
    TextView mTitleText;
    TextView mAuthorText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_who_wrote_it);

        mBookInput = (EditText) findViewById(R.id.bookInput);
        mTitleText = (TextView) findViewById(R.id.titleText);
        mAuthorText = (TextView) findViewById(R.id.authorText);


    }

    public void searchBooks(View view) {

        String querystring = mBookInput.getText().toString();
        new FetchBook(mTitleText, mAuthorText).execute(querystring);



    }
}