package appcamp.hemang.tempconverterwebserviceapp;

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
        String temp = et1.getText().toString();
        new WebServiceTask().execute(temp);
    }

    private class WebServiceTask extends AsyncTask<String, Void, String> {

        private static final String N = "http://www.webserviceX.NET/";
        private static final String M = "ConvertTemp";
        private static final String A = "http://www.webserviceX.NET/ConvertTemp";
        private static final String W = "http://www.webservicex.net/ConvertTemperature.asmx?WSDL";

        @Override
        protected String doInBackground(String... params) {
            String result = "";

            SoapObject request = new SoapObject(N, M);
            request.addProperty("Temperature", 29);
            request.addProperty("FromUnit", "degreeCelsius");
            request.addProperty("ToUnit", "degreeFahrenheit");

            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.setOutputSoapObject(request);
            envelope.dotNet = true;

            HttpTransportSE conn = new HttpTransportSE(W);

            try {
                conn.call(A, envelope);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (XmlPullParserException e) {
                e.printStackTrace();
            }

            SoapObject response = (SoapObject) envelope.bodyIn;
            result = response.getProperty("ConvertTempResult").toString();
            return result;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            tv1.setText(s);
        }
    }
}
