package appcamp.hemang.simplefragmentapp;

import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(savedInstanceState == null){
            SimpleFragment fragment = new SimpleFragment();
            FragmentManager fragmentManager = getFragmentManager();

            fragmentManager.beginTransaction()
                           .add(R.id.fragmentHolder, fragment)
                           .commit();
        }
    }
}
