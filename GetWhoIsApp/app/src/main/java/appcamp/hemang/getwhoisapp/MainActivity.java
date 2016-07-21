package appcamp.hemang.getwhoisapp;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;
import java.io.IOException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText et1;
    TextView t1;
    Button bt1;
    private String hostName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et1 = (EditText) findViewById(R.id.editText);
        bt1 = (Button) findViewById(R.id.button);
        t1 = (TextView) findViewById(R.id.textView2);

        bt1.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        hostName = et1.getText().toString();
        new WebServiceTask().execute(hostName);
    }

    public class WebServiceTask extends AsyncTask<String, Void, String> {

        /*  FOR IMPORTANT PROPERTIES REQUIRED EVERY FCKIN TIME

         NAMESPACE
         METHOD
         SOAPACTION
         WSURL


    SAMPLE REQUEST HEADER

            POST /whois.asmx HTTP/1.1
            Host: www.webservicex.net
            Content-Type: text/xml; charset=utf-8
            Content-Length: length
            SOAPAction: "http://www.webservicex.net/GetWhoIS"

     NAMESPACE = http://webservicex.net
     METHOD = GetWhoIs
     SOAPACTION = http://webservicex.net/GetWhoIS
     WSURL = http://www.webservicex.net/whois.asmx?WSDL  (FOR WSURL APPEND WSDL in the end of URL)

     */




    /*

    SAMPLE REQUEST

    <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/">
         <soap:Body>
              <GetWhoIS xmlns="http://www.webservicex.net">
                    <HostName>string</HostName>
               </GetWhoIS>
        </soap:Body>
    </soap:Envelope>


     */

        public static final String NAMESPACE = "http://www.webservicex.net";
        public static final String METHOD =  "GetWhoIS";
        public static final String ACTION = "http://www.webservicex.net/GetWhoIS";
        public static  final String WSURL = "http://www.webservicex.net/whois.asmx?WSDL";
        @Override
        protected String doInBackground(String... params) {



            SoapObject request = new SoapObject(NAMESPACE, METHOD);
            request.addProperty("HostName",  params[0]);

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
            SoapObject response = (SoapObject) envelope.bodyIn;


            //Get Result
            String result = response
                    .getProperty("GetWhoISResult") //Sends and Object
                    .toString();                   // Convert to String

            return result;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            t1.setText(s);
        }
    }
}
