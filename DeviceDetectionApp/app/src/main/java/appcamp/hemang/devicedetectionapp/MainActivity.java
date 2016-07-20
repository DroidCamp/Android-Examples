package appcamp.hemang.devicedetectionapp;

import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Button b1;
    TextView tv1, tv2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        b1 = (Button) findViewById(R.id.button);
        tv1 = (TextView) findViewById(R.id.textView);
        tv2 = (TextView) findViewById(R.id.textView2);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // What is the orientation?
                Display display = getWindowManager().getDefaultDisplay();
                tv1.setText("Orientation : " + getWindowManager().getDefaultDisplay().getRotation());


                // What is the resolution?
                Point xy = new Point();
                display.getSize(xy);
                tv2.setText("x = " + xy.x + " y = " + xy.y);
            }
        });
    }
}
