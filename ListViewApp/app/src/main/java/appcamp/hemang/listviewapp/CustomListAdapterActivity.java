package appcamp.hemang.listviewapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomListAdapterActivity extends AppCompatActivity {

    Button bt1, bt2;
    ListView listView;
    ArrayList<CarModel> carModelArrayList;
    CustomCarAdapter customCarAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_list_adapter);

        listView = (ListView) findViewById(R.id.listView2);
        bt1 = (Button) findViewById(R.id.button3);
        bt2 = (Button) findViewById(R.id.button4);

        carModelArrayList = new ArrayList<>();
        carModelArrayList.add(new CarModel("Suzuki", "1212"));
        carModelArrayList.add(new CarModel("Maruti", "12122"));

        customCarAdapter = new CustomCarAdapter(this, R.layout.custom_list_item, carModelArrayList);
        listView.setAdapter(customCarAdapter);

        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customCarAdapter.add(new CarModel("Hyundai", "21"));

            }
        });

        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CustomListAdapterActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }

    private class CustomCarAdapter extends ArrayAdapter<CarModel>{


        public CustomCarAdapter(Context context, int resource, ArrayList<CarModel> objects) {
            super(context, resource, objects);
        }

        @Override
        public void add(CarModel object) {
            carModelArrayList.add(object);
            notifyDataSetChanged();
        }

        @Override
        public int getCount() {
            return carModelArrayList.size();
        }

        @Override
        public CarModel getItem(int position) {
            return carModelArrayList.get(position);
        }

        @Override
        public int getPosition(CarModel item) {
            return carModelArrayList.indexOf(item);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = convertView;

            if(view == null){
                LayoutInflater inflater = LayoutInflater.from(getContext());
                view = inflater.inflate(R.layout.custom_list_item, parent, false);
            }

            TextView tv = (TextView) view.findViewById(R.id.textView);
            TextView tv2 = (TextView) view.findViewById(R.id.textView2);

            CarModel carModel = getItem(position);
            tv.setText(carModel.getName());
            tv2.setText(carModel.getId());

            return  view;
        }
    }
}
