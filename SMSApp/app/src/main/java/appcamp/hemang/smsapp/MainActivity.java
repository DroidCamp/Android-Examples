//SMS App Using Hybrid Approach


package appcamp.hemang.smsapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //Global Variables
    EditText e1, e2;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Created Layout using Hybrid Approach
        setContentView(R.layout.activity_main);


        //Attach Java variable to XML so that XML elements can be referred in Java
        e1 = (EditText) findViewById(R.id.editText);
        e2 = (EditText) findViewById(R.id.editText2);
        btn = (Button) findViewById(R.id.button);


        //Event Handling
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        String message = e1.getText().toString();
        String number = e2.getText().toString();


        SmsManager smsManager = SmsManager.getDefault();
        // getDefault is  static method which creates and object of SmsManager Type
        // SmsManager does not have a constructor and objects are created using getDefault()


        smsManager.sendTextMessage(number, null, message, null, null);
        //sendTextMessage takes source number, destination number, message, SendIntent and DeliveryIntent
    }
}
