package edu.northeastern.movieapi.network;


import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class NetworkThread extends Thread {
    private String url;
    private NetworkCallback networkCallback;

    public interface NetworkCallback {
        void processResponse(String responseData);
        void onError();

        void onEmptyResult();
    }

    public NetworkThread(String url, NetworkCallback networkCallback) {
        this.url = url;
        this.networkCallback = networkCallback;
    }

    @Override
    public void run() {
        try {
            URL requestUrl = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) requestUrl.openConnection();
            connection.setRequestMethod("GET");
            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                InputStream inputStream = connection.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                // Handle the response data (e.g., parse JSON, update UI)
                String responseData = response.toString();
                Log.d("RESPONSE_DATA",responseData);
                if(!preProcessResponseForErrors(responseData)){
                    this.networkCallback.processResponse(responseData);
                }
                reader.close();
                inputStream.close();
            } else {
                this.networkCallback.onError();
                // Handle the error response (e.g., show an error message)
            }
            connection.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
            this.networkCallback.onError();
            // Handle any exceptions that occur during the network request

        }
    }

    public boolean preProcessResponseForErrors(String responseData){
        try {
            JSONObject jsonObject = new JSONObject(responseData);
            String errorMessage = jsonObject.getString("errorMessage");
            if (errorMessage.isEmpty()) {
                return false;
            }
            if(!jsonObject.getString("errorMessage").equals("null")){
                networkCallback.onError();
                return true;
            }

            JSONArray moviesArray = jsonObject.getJSONArray("results");
            if (moviesArray.length() == 0) {
                networkCallback.onEmptyResult();
                return true;
            }
        } catch (JSONException e) {
            networkCallback.onError();
            return true;
        }
    return false;
    }

}

