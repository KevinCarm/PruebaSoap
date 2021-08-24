package com.example.pruebasoap;

import android.os.AsyncTask;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.ksoap2.transport.HttpsTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

public class Test_1 extends AsyncTask<String, Integer, String> {
    private String NAMESPACE = "http://tempuri.org/";
    private String URL = "http://sicenet.itsur.edu.mx/ws/wsalumnos.asmx";
    private String METHOD_NAME = "accesoLogin";
    private String SOAP_ACTION = "http://tempuri.org/accesoLogin";

    @Override
    protected String doInBackground(String... strings) {

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.dotNet = false;
        envelope.implicitTypes = true;
        envelope.setAddAdornments(false);

        SoapObject body = new SoapObject(NAMESPACE, METHOD_NAME);
        body.addProperty("strMatricula", "s17120225");
        body.addProperty("strContrasenia", "iB/5%w2Y");
        body.addProperty("tipoUsuario", 0);

        envelope.setOutputSoapObject(body);

        try {
            HttpTransportSE httpTransportSE = new HttpTransportSE(URL);
            httpTransportSE.debug = true;
            httpTransportSE.call(SOAP_ACTION, envelope);
            System.out.println("SOAP Request " + httpTransportSE.requestDump);
            System.out.println("SOAP Response " + httpTransportSE.responseDump);
        } catch (IOException | XmlPullParserException e) {
            System.out.println(e.getMessage());
        }
        return "";
    }
}
