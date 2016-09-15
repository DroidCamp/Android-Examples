package xyz.hemangkumar.recyclerview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<Car> cars;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
      /*  ImageView img = (ImageView) findViewById(R.id.imgV);
        Picasso.with(this).load("http://i.imgur.com/DvpvklR.png").into(img);*/

        RecyclerView rvContacts = (RecyclerView) findViewById(R.id.rv);

        // Initialize contacts
        cars = Car.createCarList(20);
        // Create adapter passing in the sample user data
        CarAdapter adapter = new CarAdapter(cars, this);
        // Attach the adapter to the recycler view to populate items
        rvContacts.setAdapter(adapter);
        // Set layout manager to position the items
        rvContacts.setLayoutManager(new StaggeredGridLayoutManager(this, null, 0, 0));


    }
}
