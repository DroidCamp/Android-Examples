package appcamp.hemang.listviewapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ArrayList<String> stringArrayList;
    ListView listView;
    ArrayAdapter<String> stringArrayAdapter;
    Button bt1, bt2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.listView);
        stringArrayList = new ArrayList<>();
        stringArrayList.add("Hey!");
        stringArrayList.add("This");
        stringArrayList.add("is");
        stringArrayList.add("ListView");

        stringArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, stringArrayList);
        listView.setAdapter(stringArrayAdapter);
        stringArrayAdapter.notifyDataSetChanged();

        bt1 = (Button) findViewById(R.id.button);
        bt2 = (Button) findViewById(R.id.button2);

        bt1.setOnClickListener(this);
        bt2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        switch (id){
            case R.id.button:
                Intent intent = new Intent(this, CustomListAdapterActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.button2:
                stringArrayList.add("New Item inserted");
                stringArrayAdapter.notifyDataSetChanged();
                break;
        }
    }
}
