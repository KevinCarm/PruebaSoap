package com.example.pruebasoap;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class POST extends AsyncTask<String, Integer, String> {

    private String url;

    public POST(String url) {
        this.url = url;
    }



    @Override
    protected String doInBackground(String... strings) {
        URL UrlObj = null;
        try {
            UrlObj = new URL(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        HttpURLConnection connection = null;
        try {
            connection = (HttpURLConnection) UrlObj.openConnection();
            connection.setRequestMethod("POST");
        } catch (IOException e) {
            e.printStackTrace();
        }
        connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        connection.setRequestProperty("Host", "sicenet.itsur.edu.mx");
        connection.setDoOutput(true);

        DataOutputStream outputStream = null;
        try {
            outputStream = new DataOutputStream(connection.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

        String urlPostParameters = "strMatricula=s17120225&strContrasenia=iB/5%w2Y&tipoUsuario=0";
        try {
            outputStream.writeBytes(urlPostParameters);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            outputStream.flush();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Send 'HTTP POST' request to : " + url);

        int responseCode = 0;
        try {
            responseCode = connection.getResponseCode();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Response Code : " + responseCode);

        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader inputReader = null;
            try {
                inputReader = new BufferedReader(
                        new InputStreamReader(connection.getInputStream()));
            } catch (IOException e) {
                e.printStackTrace();
            }
            String inputLine = null;
            StringBuffer response = new StringBuffer();

            while (true) {
                try {
                    assert inputReader != null;
                    if ((inputLine = inputReader.readLine()) == null) break;
                } catch (IOException e) {
                    e.printStackTrace();
                }
                response.append(inputLine);
            }
            try {
                inputReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return response.toString();
        } else {
            System.out.println(responseCode);
            return "Error";
        }
    }

    @Override
    protected void onPostExecute(String s) {
        System.out.println(s);
    }
}

