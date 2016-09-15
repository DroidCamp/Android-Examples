package xyz.hemangkumar.firebaseapp;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.media.Image;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import com.firebase.client.Firebase;
import com.firebase.ui.database.FirebaseListAdapter;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    EditText editText;
    Button btn;
    RecyclerView recycler;
    FirebaseRecyclerAdapter<Datum, WorkshopHolder> mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       // editText = (EditText) findViewById(R.id.editText);
        btn = (Button) findViewById(R.id.button);

        Firebase.setAndroidContext(this);


        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("workshops");


        btn.setOnClickListener(this);
        //ListView messagesView = (ListView) findViewById(R.id.listView);

      //  final ProgressDialog dialog;
       // dialog = new ProgressDialog(MainActivity.this);

        recycler = (RecyclerView) findViewById(R.id.recyclerview);
       // recycler.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);

        recycler.setLayoutManager(layoutManager);

        mAdapter = new FirebaseRecyclerAdapter<Datum, WorkshopHolder>(Datum.class, R.layout.card_list_item, WorkshopHolder.class, ref) {
            @Override
            public void populateViewHolder(WorkshopHolder chatMessageViewHolder, Datum chatMessage, int position) {
                chatMessageViewHolder.setName(chatMessage.getTitle());
                chatMessageViewHolder.setText(chatMessage.getEnd_date());
               chatMessageViewHolder.setImg(chatMessage.getPicture(), MainActivity.this);

            }


        };
        recycler.setAdapter(mAdapter);
        recycler.smoothScrollToPosition(mAdapter.getItemCount());

/*
      mAdapter = new FirebaseListAdapter<Datum>(this, Datum.class, R.layout.card_list_item, ref) {
           
            @Override
            protected void populateView(View view, Datum chatMessage, int position) {

             //   dialog.setMessage("Please wait.....");
              //  dialog.show();

                ((TextView)view.findViewById(R.id.contact_name)).setText(chatMessage.getTitle());
                ((TextView)view.findViewById(R.id.end_date)).setText(chatMessage.getEnd_date());
                Picasso.with(MainActivity.this).load(chatMessage.getPicture());

            }
        };
        //dialog.dismiss();
        messagesView.setAdapter(mAdapter);

        */
    }

    @Override
    public void onClick(View v) {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("workshops");
        Datum msg = new Datum("puf", "1234", "Hello FirebaseUI world!","adasd", "affa", "http://i.imgur.com/DvpvklR.png");
        ref.push().setValue(msg);

        recycler.smoothScrollToPosition(mAdapter.getItemCount());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mAdapter.cleanup();
    }

    public static class WorkshopHolder extends RecyclerView.ViewHolder {
        View mView;

        public WorkshopHolder(View itemView) {
            super(itemView);
            mView = itemView;
        }

        public void setName(String name) {
            TextView field = (TextView) mView.findViewById(R.id.contact_name);
            field.setText(name);
        }

        public void setText(String text) {
            TextView field = (TextView) mView.findViewById(R.id.end_date);
            field.setText(text);
        }
        public void setImg(String img, Context context){
            ImageView imgView = (ImageView) mView.findViewById(R.id.imageView);
            Picasso.with(context).load(img).fit().into(imgView);
        }
    }




}
