package appcamp.hemang.databaseapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

//CREATE A TASK APP
// STORE TASK WITH TASKNAME AND TASKDESCRIPTION
//BUTTONS TO SAVE, CLEAR DATA
//VIEW BUTTON

public class SaveActivity extends AppCompatActivity implements View.OnClickListener {

    Button saveButton, viewButton;
    EditText et1, et2;
    String a ,b;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        saveButton = (Button) findViewById(R.id.button);
        viewButton = (Button) findViewById(R.id.button2);

        et1 = (EditText) findViewById(R.id.editText);
        et2 = (EditText) findViewById(R.id.editText2);

        saveButton.setOnClickListener(this);
        viewButton.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        int id = v.getId();

        switch(id){
            case R.id.button:
                String task = et1
                        .getText()
                        .toString();

                String description = et2
                        .getText()
                        .toString();

                //Save the data
                //Create new class myDatabase to handle database operations
                MyDatabase mdb = new MyDatabase(this);


                mdb.open();
                mdb.write(task , description);
                mdb.close();

                Toast.makeText(SaveActivity.this, "Data Saved Successfully", Toast.LENGTH_SHORT).show();
                break;
            case R.id.button2:
                //View the data
                Intent view = new Intent(this, ViewActivity.class);
                startActivity(view);
                finish();

                break;
        }
    }
}
