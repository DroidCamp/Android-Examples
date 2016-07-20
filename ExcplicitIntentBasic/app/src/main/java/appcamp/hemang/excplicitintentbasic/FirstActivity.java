package appcamp.hemang.excplicitintentbasic;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class FirstActivity extends AppCompatActivity implements View.OnClickListener {
    Button bt1, bt2;
    EditText et1, et2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_first);

        bt1 = (Button) findViewById(R.id.button);
        bt1.setOnClickListener(this);

        et1  = (EditText) findViewById(R.id.editText);
        et2 = (EditText) findViewById(R.id.editText2);




    }

    @Override
    public void onClick(View v) {

        Intent intent = new Intent();
        intent.setClass(this, SecondActivity.class);
        intent.putExtra("Name", et1.getText().toString());
        intent.putExtra("Age", Integer.parseInt(et2.getText().toString()));
        startActivity(intent);
        finish();

    }
}
