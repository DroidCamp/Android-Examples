package appcamp.hemang.cameraintentapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button bt1;


    //Good Practice for request code
    int camrequest = 1;

    ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bt1 = (Button) findViewById(R.id.button);
        bt1.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        //Camera's intent is not stored in Intent.ACTION_blah blah. It is a static constant in MediaStore class
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        //New method, instead of startActivity
        startActivityForResult(intent, camrequest);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        /* BASIC LOGIC OF onAcvitiyResult
        *
        * if(requestCode == <YOUR REQUEST CODE> eg camrequest, vidrequest)
        *
        * {
        *
        *       if(resultCode == RESULT_OK )
        *
        *       {
        *
        *           BUSINESS LOGIC
        *
        *
        *        }
        *
        *
        *        else if (resultCode == RESULT_CANCELLED | RESULT_NOT_OK)
        *        {
        *
        *
        *        }
        *
        * }
        *
        *
        * ALTERNATIVE BASIC LOGIC
        *
        * if(requestCode == camrequest && resultCode == RESULT_OK)
        *
        * {
        *
        *     BUSINESS LOGIC
        *
        * }
        *
        *
        * */
        if(requestCode==camrequest){
            if(resultCode==RESULT_OK){
                Bundle val = data.getExtras();


                Bitmap image = (Bitmap) val.get("data");


                img = (ImageView) findViewById(R.id.imageView);
                img.setImageBitmap(image);

            }
        }



    }
}
