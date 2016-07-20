package appcamp.hemang.sharedpref;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by Hemang on 23/06/16.
 */
public class ActivitySec  extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sec);

        Context context = getApplicationContext();
        SharedPreferences sharedPreferences = context.getSharedPreferences("Pref", Context.MODE_PRIVATE);

        TextView textView = (TextView) findViewById(R.id.textView2);
        textView.setText(sharedPreferences.getString("Hello", "Nothing found"));
    }
}
