package appcamp.hemang.sqltodoapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //Controls
    EditText et1, et2;
    Button bt1, bt2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_main);

        et1 = (EditText) findViewById(R.id.editText);
        et2 = (EditText) findViewById(R.id.editText2);

        bt1 = (Button) findViewById(R.id.button);
        bt2 = (Button) findViewById(R.id.button2);

        bt1.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch(id){
            case  R.id.button:
                Database db = new Database(this);
                db.open();
                db.write(et1.getText().toString(), et2.getText().toString());
                db.close();
                break;

            case R.id.button2:
                Intent intent = new Intent(this, ShowTaskActivity.class);
                startActivity(intent);
                finish();
                break;
        }
    }
}