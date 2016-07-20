package appcamp.hemang.sqltodoapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.TextView;

public class ShowTaskActivity extends AppCompatActivity{

    TextView tv1;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_task);

        tv1 = (TextView) findViewById(R.id.textView3);

        Database db2 = new Database(this);
        db2.open();
        String result = db2.read();
        db2.close();

        tv1.setText(result);
    }
}