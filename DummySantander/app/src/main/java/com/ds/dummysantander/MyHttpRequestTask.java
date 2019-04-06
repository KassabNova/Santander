package com.ds.dummysantander;

import android.os.AsyncTask;
import android.util.Log;

import com.android.volley.ApplicationController;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class MyHttpRequestTask extends AsyncTask<String,Void,String> {

    @Override
    protected String doInBackground(String... params) {
        String my_url = params[0];
        String my_data = params[1];
        String response = "";
        try {
            URL url = new URL(my_url);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            // setting the  Request Method Type
            httpURLConnection.setRequestMethod("POST");
            // adding the headers for request
            httpURLConnection.setRequestProperty("Content-Type", "application/json");
            try{
                //to tell the connection object that we will be wrting some data on the server and then will fetch the output result
                httpURLConnection.setDoOutput(true);
                // this is used for just in case we don't know about the data size associated with our request
                httpURLConnection.setChunkedStreamingMode(0);

                // to write tha data in our request
                OutputStream outputStream = new BufferedOutputStream(httpURLConnection.getOutputStream());
                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);
                outputStreamWriter.write(my_data);
                outputStreamWriter.flush();
                outputStreamWriter.close();

                // to log the response code of your request
                Log.d(ApplicationController.TAG, "MyHttpRequestTask doInBackground : " +httpURLConnection.getResponseCode());
                // to log the response message from your server after you have tried the request.
                Log.d(ApplicationController.TAG, "MyHttpRequestTask doInBackground : " +httpURLConnection.getResponseMessage());
                response = readStream(httpURLConnection.getInputStream());


            }catch (Exception e){
                e.printStackTrace();
            }finally {
                // this is done so that there are no open connections left when this task is going to complete
                httpURLConnection.disconnect();
            }


        }catch (Exception e){
            e.printStackTrace();
        }

        return response;
    }

    @Override
    protected void onPostExecute(String result){
        super.onPostExecute(result);
    }

    // Converting InputStream to String
    public String readStream(InputStream in) {
        BufferedReader reader = null;
        StringBuffer response = new StringBuffer();
        try {
            reader = new BufferedReader(new InputStreamReader(in));
            String line = "";
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return response.toString();
    }
}


