package appcamp.hemang.intentbasic;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    EditText e1;
    ImageButton im1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Refer xml controls in Java

        e1 = (EditText) findViewById(R.id.editText);
        im1 = (ImageButton) findViewById(R.id.imageButton);


        //Event Handling
        im1.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        //Business Logic


        /*
        * INTENTS REQUIRE THESE THINGS TO BE SPECIFIED
        *
        * CREATE INTENT OBJECT
        * SPECIFY ACTION
        * SET DATA URI SCHEME
        * SET MIME DATA
        * START INTENT
        */


        //Receive number from EditText view
        String number = e1.getText().toString();


        //Create an Intent Object & Set Action on intent
        Intent intent = new Intent();

        intent.setAction(Intent.ACTION_CALL);
        /*Set Data URI Scheme (UNIVERSAL RESOURCE IDENTIFIER) - Read appropriate doc to find what is the required Data URI Scheme for that intent

        According to Phone Intent Doc, setData accepts data in Uri format. */

        //Converting String to Uri after modifying the string according to documentation, and passing in setData
        intent.setData(Uri.parse("tel:" + number));


        //No MIME Required - Read appropriate doc to find what is the required MIME for that intent


        /* NOTE FROM ANDROID DOCUMENTATION */
       //If you want to set both the URI and MIME type,
       // do not call setData() and setType() because they each nullify the value of the other.
       // Always use setDataAndType() to set both URI and MIME type.



        //Start Intent
        startActivity(intent);




    }
}
