package appcamp.hemang.jprac;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    TextView textView;
    Serializer serializer;
    ArrayList<Car> cars;
    @Override
    public void onClick(View v) {


        try {
            cars = serializer.load();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        textView.setText(cars.toString());


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        Button b = new Button(this);
        b.setText("CLICK");

        textView = new TextView(this);

        b.setOnClickListener(this);

        linearLayout.addView(b);
        linearLayout.addView(textView);

        setContentView(linearLayout);
        cars = new ArrayList<Car>();

        for(int i=0;i<5;i++){
            Log.e("ADDING!!!", "HEY");
            Car c = new Car("Mahindra" , true);
            cars.add(c);
        }
        serializer = new Serializer("n2s.json", getApplicationContext());
        try {
            serializer.save(cars);
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        try {
            serializer.save(cars);
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
