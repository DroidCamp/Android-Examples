package appcamp.hemang.locationapp;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    TextView tv1, tv2, tv3, tv4;
    ImageView iv1;

    String LAT, LONG, ALT, SPEED;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv1 = (TextView) findViewById(R.id.textView2);
        tv2 = (TextView) findViewById(R.id.textView3);
        tv3 = (TextView) findViewById(R.id.textView4);
        tv4 = (TextView) findViewById(R.id.textView5);
        iv1 = (ImageView) findViewById(R.id.imageView);

        //Get Location Service
        LocationManager lm = (LocationManager) getSystemService(LOCATION_SERVICE);

        MyLocListener listener = new MyLocListener();

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.

            Toast.makeText(this, "Please enable the PERMISSIONS for this App", Toast.LENGTH_LONG).show();

            return;
        }

        //ACCESS FINE AND ACCESS COARSE
        lm.requestLocationUpdates(lm.GPS_PROVIDER, 0, 0, listener);
    }

    private class MyLocListener implements LocationListener{
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

            // To execute AsyncTask child class called GetImage
            GetImage getImage = new GetImage();

            //Execute the thread using method called execute method
            getImage.execute();

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

    private class GetImage extends AsyncTask<Void, Void, Bitmap>{
        @Override
        protected Bitmap doInBackground(Void... params) {

            //Background Thread
            //Cannot show any graphical elements
            //ANY
            //ANY

            /*
            Lets Begin the background thread work
             */

            //Make the API url
            String location = LAT + "," + LONG;
            String mapApiUrl = "https://maps.googleapis.com/maps/api/staticmap?"
                    + "center=" + location
                    + "&zoom=18"
                    + "&size=300x300"
                    + "&maptype=roadmap"
                    + "&markers=colors:blue|label:H|" + location;

           //Convert String to URL
            Bitmap mapImage = null; //This variable will hold the Bitmap decoded InputStream
            try {
                URL mapUrl = new URL(mapApiUrl);
                //Now to execute Open Connection

                //Three Streams - InputStream, OutputStream , ErrorStream

                InputStream inputStreamData = (InputStream) mapUrl.openConnection().getContent();

                //Convert inputStreamData (Binary) to Bitmap
                //A Class is available BitMapFactory - with method DecodeStream

                mapImage = BitmapFactory.decodeStream(inputStreamData);

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }


            return mapImage;



        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            //UI THREAD, MAIN THREAD, GUI THREAD
            iv1.setImageBitmap(bitmap);
            super.onPostExecute(bitmap);
        }
    }
}
