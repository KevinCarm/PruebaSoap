package com.example.pruebasoap

import android.os.Bundle
import android.os.NetworkOnMainThreadException
import androidx.appcompat.app.AppCompatActivity
import org.w3c.dom.Document
import org.xml.sax.InputSource
import org.xml.sax.SAXException
import java.io.*
import java.lang.RuntimeException
import java.net.HttpURLConnection
import java.nio.charset.Charset
import javax.net.ssl.HttpsURLConnection
import javax.xml.parsers.DocumentBuilder
import javax.xml.parsers.DocumentBuilderFactory
import javax.xml.parsers.ParserConfigurationException
import javax.xml.transform.OutputKeys
import javax.xml.transform.Transformer
import javax.xml.transform.TransformerException
import javax.xml.transform.TransformerFactory
import javax.xml.transform.dom.DOMSource
import javax.xml.transform.stream.StreamResult


class MainActivity : AppCompatActivity() {

    val NAMESPACE = "http://tempuri.org/"
    val URL = "http://sicenet.itsur.edu.mx/ws/wsalumnos.asmx"
    val METHOD_NAME = "accesoLogin"
    val SOAP_ACTION = "http://tempuri.org/accesoLogin"



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //POST("https://sicenet.itsur.edu.mx/ws/wsalumnos.asmx?op=accesoLogin").execute("POST")
        //SoapPost(URL).execute("")
        //Test_1().execute("")
        Request(applicationContext).execute("")
    }
}