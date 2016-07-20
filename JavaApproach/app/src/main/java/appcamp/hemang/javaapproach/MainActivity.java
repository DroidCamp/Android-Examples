package appcamp.hemang.javaapproach;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //context = activity in which control should be visible
        LinearLayout linearLayout = new LinearLayout(this);
        //this refers to current activity


        //Set Orientation of Layout
        linearLayout.setOrientation(LinearLayout.VERTICAL);


        //Create a control : TextView
        textView = new TextView(this);

        //Specify text
        textView.setText("Java Approach");

        //Add control to root layout
        linearLayout.addView(textView);


        //Create a control : Button
        Button btn = new Button(this);

        //Specify Text
        btn.setText("Button!");

        //Add control to root
        linearLayout.addView(btn);


        //Add Event Handling
        btn.setOnClickListener(this);

        setContentView(linearLayout);

    }

    @Override
    public void onClick(View v) {
        Toast.makeText(this, "Java Approach", Toast.LENGTH_LONG).show();
        textView.setText("Hemang");
    }
}
