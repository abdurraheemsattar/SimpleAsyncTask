package com.example.simpleasynctask;

import android.os.AsyncTask;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class FetchBook extends AsyncTask<String, Void, String> {

    private TextView mTitleText, mAuthorText;

    public FetchBook(TextView mTitleText, TextView mAuthorText) {
        this.mTitleText = mTitleText;
        this.mAuthorText = mAuthorText;
    }


    @Override
    protected String doInBackground(String... params) {
        return NetworkUtils.getBookInfo(params[0]);
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

        try {
            JSONObject jsonObject = new JSONObject(s);
            JSONArray itemsArray = jsonObject.getJSONArray("items");

            for (int i=0; i<itemsArray.length(); i++){
                JSONObject book = itemsArray.getJSONObject(i); //get current item
                String title = null;
                String authors = null;
                JSONObject volumeInfo = book.getJSONObject("volumeInfo");

                try {
                    title = volumeInfo.getString("title");
                    authors = volumeInfo.getString("authors");

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                if (title != null && authors !=null){
                    mTitleText.setText(title);
                    mAuthorText.setText(authors);
                    return;
                }
            }

            mTitleText.setText("No resluts found");
            mAuthorText.setText("");

        } catch (JSONException e) {
            mTitleText.setText("No Results Found");
            mAuthorText.setText("");
            e.printStackTrace();
        }

    }





















}
