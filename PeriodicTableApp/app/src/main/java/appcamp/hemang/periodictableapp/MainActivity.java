package appcamp.hemang.periodictableapp;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.SoapFault;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.ksoap2.transport.HttpsTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.Hashtable;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    EditText et1;
    Button bt1;
    TextView tv1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et1 = (EditText) findViewById(R.id.editText);
        bt1 = (Button) findViewById(R.id.button);
        tv1 = (TextView) findViewById(R.id.textView2);

        bt1.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        String elem = et1
                .getText()
                .toString();

        new GetAtomicNum().execute(elem);

    }

    private class GetAtomicNum extends AsyncTask<String, Void, String>{

        public static final String NAMESPACE = "http://www.webservicex.net";
        public static final String METHOD =  "GetAtomicNumber";
        public static final String ACTION = "http://www.webserviceX.NET/GetAtomicNumber";
        public static  final String WSURL = "http://www.webservicex.net/periodictable.asmx?WSDL";
        @Override
        protected String doInBackground(String... params) {


            SoapObject request = new SoapObject(NAMESPACE, METHOD);
            request.addProperty("ElementName", params[0]);

            //Add in Envelope
            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11); //VERSION11 =  1.1

            //Add request SoapObject in envelope
            envelope.setOutputSoapObject(request);

            //Two Popular Web Service - PHP, DOT NET.
            // Set the flag True/False
            envelope.dotNet = true;


            //Send request, Transports Soap Envelope using Http Protocol
            //It takes Web Service Url as an argument
            HttpTransportSE transport = new HttpTransportSE(WSURL);


            try {
                transport.call(ACTION, envelope);


            } catch (IOException e) {
                //NO SERVER FOUND
                e.printStackTrace();
            } catch (XmlPullParserException e) {
                //SERVER EXISTS BUT NO XML RESPONSE
                e.printStackTrace();
            }

            //READ RESPONSE

        /*
        SAMPLE RESPONSE
                    HTTP/1.1 200 OK
                    Content-Type: text/xml; charset=utf-8
                    Content-Length: length

                    <?xml version="1.0" encoding="utf-8"?>
                    <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/">
                      <soap:Body>
                        <GetWhoISResponse xmlns="http://www.webservicex.net">
                          <GetWhoISResult>string</GetWhoISResult>
                        </GetWhoISResponse>
                      </soap:Body>
                    </soap:Envelope>

         */



            //Cast response Object type to SoapObject Type so that fetching response becomes easier
            SoapObject response = null;
            try {
                response = (SoapObject) envelope.getResponse();
            } catch (SoapFault soapFault) {
                soapFault.printStackTrace();
            }


            //Get Result
            String result = response
                    .getProperty("GetAtomicNumberResult") //Sends and Object
                    .toString();                   // Convert to String

            return result;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            tv1.setText(s);
        }
    }
}
