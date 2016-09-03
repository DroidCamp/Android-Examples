package xyz.hemangkumar.recyclerview;

import android.content.Context;
import android.media.Image;
import android.support.v7.widget.ButtonBarLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Hemang on 03/09/16.
 */
public class CarAdapter extends RecyclerView.Adapter<CarAdapter.ViewHolder> {

    private List<Car> cars;

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public CarAdapter(List<Car> cars, Context context) {

        this.cars = cars;
        this.context = context;
    }

    private Context context;

    @Override
    public CarAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context ctx = parent.getContext();

        LayoutInflater inflater = LayoutInflater.from(ctx);

        View carView = inflater.inflate(R.layout.card_list_item, parent, false);

        ViewHolder viewHolder = new ViewHolder(carView);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Car car = cars.get(position);
        TextView textView = holder.tv;
        textView.setText(car.getName());
        Button btn = holder.btn;
        btn.setText(String.valueOf(car.getYear()));
        ImageView img = holder.img;
        Picasso.with(this.getContext()).load("http://i.imgur.com/DvpvklR.png").into(img);
    }

    @Override
    public int getItemCount() {
        return cars.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv;
        Button btn;
        ImageView img;
        public ViewHolder(View itemView) {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.contact_name);
            btn = (Button) itemView.findViewById(R.id.message_button);
            img = (ImageView) itemView.findViewById(R.id.imageView);
        }
    }
}
