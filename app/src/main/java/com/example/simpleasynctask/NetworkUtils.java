package com.example.simpleasynctask;

import android.net.Uri;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class NetworkUtils {

    private static final String LOG_TAG = NetworkUtils.class.getSimpleName();
    private static Log log;

    public static String getBookInfo(String queryString) {

        HttpURLConnection urlConnection=null;
        BufferedReader reader =null;
        String bookJSONString = null;

        // Base URI for the Books API
        final String BOOK_BASE_URL = "https://www.googleapis.com/books/v1/volumes?";
        // Parameter for the search string
        final String QUERY_PARAM = "q";
        // Parameter that limits search results
        final String MAX_RESULTS = "maxResults";
        // Parameter to filter by print type
        final String PRINT_TYPE = "printType";

        try {
            Uri builtURI = Uri.parse(BOOK_BASE_URL).buildUpon()
                    .appendQueryParameter(QUERY_PARAM, queryString)
                    .appendQueryParameter(MAX_RESULTS,"10")
                    .appendQueryParameter(PRINT_TYPE,"books")
                    .build();

            URL requestURL = new URL(builtURI.toString());


            urlConnection = (HttpURLConnection) requestURL.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            InputStream inputStream = urlConnection.getInputStream();
            StringBuffer buffer = new StringBuffer();

            if (inputStream ==null){
                // do nothing
                return null;
            }
            reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = reader.readLine()) != null){
                /* Since it's JSON, adding a newline isn't necessary (it won't affect
                    parsing) but it does make debugging a *lot* easier if you print out the
                    completed buffer for debugging. */

                buffer.append(line + "\n");

            }

            if (buffer.length() ==0) {
                // stream empty
                return null;
            }

            bookJSONString = buffer.toString();

        }catch (IOException e) {
            e.printStackTrace();
            return null;
        }finally {

            if (urlConnection != null){
                urlConnection.disconnect();
            }
            if (reader != null){
                try {
                    reader.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
           
            log.d(LOG_TAG, bookJSONString);
            return bookJSONString;
        }

    }

}


























