/*
*
* MAIL APP USING SMTP PROTOCOL
*
* */

package appcamp.hemang.intentmailapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText et1, et2, et3;
    ImageButton btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et1 = (EditText) findViewById(R.id.editText);
        et2 = (EditText) findViewById(R.id.editText2);
        et3 = (EditText) findViewById(R.id.editText3);

        btn = (ImageButton) findViewById(R.id.button);

        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        //Business Logic: Email App

        String emails = et1.getText().toString();

        //SUPER TRICK. SEND STRING SEPARATED BY ',' AND IT GETS AUTOMATICALLY CONVERTED TO STRING ARRAY

        //FOR EG if String emails = "hemangsk@gmail.com , a@h.com, b@k.com"

        //String emailID[] = {emails} will convert it into {"Hemangsk@gmail.com", "a@h.com", "b@k.com"};

        String emailId[] = { emails };


        String subject = et2.getText().toString();

        String message = et3.getText().toString();


        Intent email = new Intent();

        email.setAction(Intent.ACTION_SEND);

        email.setType("*/*");

        //Learn about EXTRAS from specific ,Documentation
        email.putExtra(Intent.EXTRA_EMAIL, emailId);
        email.putExtra(Intent.EXTRA_SUBJECT, subject);
        email.putExtra(Intent.EXTRA_TEXT, message);

        //Start Intent
        startActivity(email);

    }
}
