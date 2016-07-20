package appcamp.hemang.countrywebservapp;

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
        new GetCountryCurrency().execute(et1.getText().toString());
    }

    private class GetCountryCurrency extends AsyncTask<String, Void, String> {

        private static final String NAMESPACE = "http://www.webserviceX.NET";
        private static final String METHOD = "GetCurrencyByCountry";
        private static final String ACTION = "http://www.webserviceX.NET/GetCurrencyByCountry";
        private static final String WSURL = "http://www.webservicex.net/country.asmx?WSDL";

        @Override
        protected String doInBackground(String... params) {
            SoapObject request, response;
            SoapSerializationEnvelope envelope;
            HttpTransportSE conn;
            String result = "";


            request = new SoapObject(NAMESPACE, METHOD)
                    .addProperty("CountryName", params[0]);

            envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.setOutputSoapObject(request);
            envelope.dotNet = true;

            conn = new HttpTransportSE(WSURL);
            try {
                conn.call(ACTION, envelope);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (XmlPullParserException e) {
                e.printStackTrace();
            }
            response = (SoapObject) envelope.bodyIn;

            result = response
                    .getProperty("GetCurrencyByCountryResult")
                    .toString();




            return  result;

        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            tv1.setText(s);
        }
    }
}
