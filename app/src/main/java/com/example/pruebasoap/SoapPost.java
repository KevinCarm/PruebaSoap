package com.example.pruebasoap;

import android.os.AsyncTask;
import android.os.Build;

import androidx.annotation.RequiresApi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.stream.Collectors;

import javax.xml.soap.SOAPMessage;

public class SoapPost extends AsyncTask<String, Integer, String> {

    private String url;

    public SoapPost(String url) {
        this.url = url;
    }

    @Override
    protected String doInBackground(String... strings) {
        try {
            URL oURL = new URL(this.url);
            HttpURLConnection con = (HttpURLConnection) oURL.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-type", "text/xml; charset=utf-8");
            con.setRequestProperty("SOAPAction", "http://tempuri.org/accesoLogin");
            con.setRequestProperty("Host", "sicenet.itsur.edu.mx");

            String reqXML = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
                    "<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">\n" +
                    "  <soap:Body>\n" +
                    "    <accesoLogin xmlns=\"http://tempuri.org/\">\n" +
                    "      <strMatricula>s17120225</strMatricula>\n" +
                    "      <strContrasenia>iB/5%w2Y</strContrasenia>\n" +
                    "      <tipoUsuario>0</tipoUsuario>\n" +
                    "    </accesoLogin>\n" +
                    "  </soap:Body>\n" +
                    "</soap:Envelope>";
            OutputStream reqStream = con.getOutputStream();
            reqStream.write(reqXML.getBytes());

            InputStream resStream = con.getInputStream();
            return streamToString(resStream);


        } catch (Exception e) {
            return e.getMessage();
        }
    }


    public static String streamToString(final InputStream inputStream) throws Exception {
        InputStreamReader is=new InputStreamReader(inputStream);
        BufferedReader br=new BufferedReader(is);
        String read = null;
        StringBuffer sb = new StringBuffer();
        while((read = br.readLine()) != null) {
            sb.append(read);
        }
        return sb.toString();
    }

    @Override
    protected void onPostExecute(String s) {
        System.out.println("----------> " + s + s.length());
    }
}
