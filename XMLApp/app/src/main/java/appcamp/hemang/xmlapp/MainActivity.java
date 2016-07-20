package appcamp.hemang.xmlapp;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    

    //All controls are View controls. In android all view control's properties are stored in View type/class
    public void showMessage(View prop){
        //Use of prop?
        //prop.getId()
        Toast.makeText(this, "XML Approach", Toast.LENGTH_SHORT).show();
    }
}
