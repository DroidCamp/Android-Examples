package appcamp.hemang.sensorapp;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener{

    ImageView iv1 ;

    SensorManager sensorManager;

    Sensor proximity;

    TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        iv1 = (ImageView) findViewById(R.id.imageView);

        textView = (TextView) findViewById(R.id.textView);

        //Sensor Service
        //Make Global Variables of  SensorManager, Sensor

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        proximity = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);

        //Now as soon as acivity is created sensor should start, and as soon as activity pauses sensor should stop
        //Due to battery issues
        //Now  implement SensorEventListener

        //Then override OnResume() and onPause

    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //Generally micro controller / micro processor read write data at 9600 Bits per second
        //Botrate should be same between sensor and micro controller
        sensorManager.registerListener(this,
                proximity,
                sensorManager.SENSOR_DELAY_NORMAL
                );
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        float proximityValue = event.values[0];

        textView.setText("Light : " + proximityValue);

        if(proximityValue==0){
            //Near Object

            iv1.setImageResource(R.drawable.g);
        }
        else{
            //Far Object

            iv1.setImageResource(R.drawable.i);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }





    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
