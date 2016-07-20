package appcamp.hemang.excplicitintentbasic;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity implements View.OnClickListener {
    TextView tv;
    Button bt2;
    MediaPlayer mp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Button bt1;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_second);

        bt1 = (Button) findViewById(R.id.button2);
        bt1.setOnClickListener(this);

        bt2 = (Button) findViewById(R.id.button3);
        bt2.setOnClickListener(this);

        tv = (TextView) findViewById(R.id.textView2);

        // getIntent method returns all the intents being sent to this activity. ALL THE INTENTS. ALL THE INTENTS...
        Intent receive = getIntent();
        Bundle values = receive.getExtras();
        String name = receive.getStringExtra("Name");
        int age = receive.getIntExtra("Age", 0);
        tv.setText("Hello! Name is " + name + " and age is " + age + ".");

        mp = MediaPlayer.create(this, R.raw.audio);
        mp.start();

    }

    @Override
    public void onClick(View v) {
        /* BUSINESS LOGIC : MULTIPLE PAGE APP */

        switch(v.getId()){
            case R.id.button2:Intent firstActivity = new Intent(this, FirstActivity.class);
                startActivity(firstActivity);
                finish();
                break;
            case R.id.button3: if(mp.isPlaying()){
                mp.pause();
                bt2.setText("Play Music");

            }
                else{
                mp.start();
                bt2.setText("Pause Music");
            }
                break;

            default:break;
        }

    }
}
