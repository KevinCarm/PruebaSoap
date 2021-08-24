package com.example.pruebasoap;

import android.content.Context;
import android.os.AsyncTask;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.ksoap2.transport.HttpsTransportSE;

public class Request extends AsyncTask<String, Integer, String> {

    private Context context;
    private String NAMESPACE = "http://tempuri.org/";
    private String URL = "https://sicenet.itsur.edu.mx/ws/wsalumnos.asmx?wsdl";
    private String METHOD_NAME = "accesoLogin";
    private String SOAP_ACTION = "http://tempuri.org/accesoLogin";

    public Request(Context context) {
        this.context = context;
    }

    @Override
    protected String doInBackground(String... strings) {
        String result = "";
        SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);

        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
                SoapEnvelope.VER11
        );
        envelope.dotNet = true;
        envelope.setOutputSoapObject(request);


        request.addProperty("strMatricula", "s17120225");
        request.addProperty("strContrasenia", "iB/5%w2Y");
        request.addProperty("tipoUsuario", 0);


        try {
            SSLConnection.allowAllSSL();
            HttpsTransportSE httpsTransportSE = new HttpsTransportSE("sicenet.itsur.edu.mx",443,"/ws/wsalumnos.asmx/accesoLogin",50000);
            httpsTransportSE.debug = true;
            httpsTransportSE.call(SOAP_ACTION, envelope);
        } catch (Exception e) {
            System.out.println("_________________________ \n " + e.getMessage());
        }
        return result;
    }

    @Override
    protected void onPostExecute(String s) {
        System.out.println("Result _____________________ ");
        System.out.println(s);
    }
}
