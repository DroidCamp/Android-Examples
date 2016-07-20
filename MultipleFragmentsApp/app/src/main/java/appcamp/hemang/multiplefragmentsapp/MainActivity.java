package appcamp.hemang.multiplefragmentsapp;

import android.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        Button b1 = (Button) findViewById(R.id.button);

        
        b1.setOnClickListener(this);

        if(savedInstanceState == null){
            MyFragment myFrag = new MyFragment();
            FragmentManager fragmentManager = getFragmentManager();

            fragmentManager.beginTransaction().add(R.id.fragment_hold, myFrag).commit();
        }
    }

    @Override
    public void onClick(View v) {
        Toast.makeText(MainActivity.this, "Hey! This is a toast", Toast.LENGTH_SHORT).show();
    }
}
