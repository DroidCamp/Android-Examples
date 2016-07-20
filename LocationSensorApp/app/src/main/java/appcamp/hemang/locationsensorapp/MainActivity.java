package appcamp.hemang.locationsensorapp;

import android.Manifest;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    TextView tv1, tv2, tv3, tv4, tv5, tv6, tv7, tv8;

    String LAT, LONG, ALT, SPEED;

    SensorManager sensorManager;

    Sensor proximity, light, compass, accelerometer ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv1 = (TextView) findViewById(R.id.textView);
        tv2 = (TextView) findViewById(R.id.textView2);
        tv3 = (TextView) findViewById(R.id.textView3);
        tv4 = (TextView) findViewById(R.id.textView4);
        tv5 = (TextView) findViewById(R.id.textView5);
        tv6 = (TextView) findViewById(R.id.textView6);
        tv7 = (TextView) findViewById(R.id.textView7);
        tv8 = (TextView) findViewById(R.id.textView8);

        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        CustomListener customListener = new CustomListener();

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.

            Toast.makeText(this, "Give permissions", Toast.LENGTH_LONG).show();

            return;
        }


        locationManager.requestLocationUpdates(locationManager.GPS_PROVIDER, 0, 0, customListener);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        proximity = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);

        light = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);

        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        compass = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);








    }

    @Override
    protected void onResume() {
        super.onResume();

        sensorManager.registerListener(this, proximity, SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(this, light, SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(this, compass, SensorManager.SENSOR_DELAY_NORMAL);

    }

    @Override
    protected void onPause() {
        super.onPause();

        sensorManager.unregisterListener(this, proximity);
        sensorManager.unregisterListener(this, light);
        sensorManager.unregisterListener(this, accelerometer);
        sensorManager.unregisterListener(this, compass);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {

        switch(event.sensor.getType()){
            case Sensor.TYPE_PROXIMITY: tv5.setText(String.valueOf(event.values[0]));
                break;
            case Sensor.TYPE_LIGHT: tv6.setText(String.valueOf(event.values[0]));
                break;
            case Sensor.TYPE_ACCELEROMETER: tv7.setText(String.valueOf(event.values[0]));
                break;
            case Sensor.TYPE_MAGNETIC_FIELD: tv8.setText(String.valueOf(event.values[0]));
                break;
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    public class CustomListener implements LocationListener {

        @Override
        public void onLocationChanged(Location location) {
            LONG = String.valueOf(location.getLongitude());
            LAT = String.valueOf(location.getLatitude());
            ALT = String.valueOf(location.getAltitude());
            SPEED = String.valueOf(location.getSpeed());

            tv1.setText(LAT);
            tv2.setText(LONG);
            tv3.setText(ALT);
            tv4.setText(SPEED);

        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }
    }
}
