package xyz.hemangkumar.rnfapp.fragments;

import android.app.Dialog;
import android.content.ContentValues;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import xyz.hemangkumar.rnfapp.MainActivity;
import xyz.hemangkumar.rnfapp.R;


/**
 * Created by Hemang on 04/09/16.
 */
public class Workshop extends Fragment {

    RecyclerView recycler;

    public static Workshop newInstance() {

        Bundle args = new Bundle();

        Workshop fragment = new Workshop();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_workshop, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Firebase.setAndroidContext(getActivity());

        FirebaseRecyclerAdapter<xyz.hemangkumar.rnfapp.models.Workshop, WorkshopHolder> mAdapter;


        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("workshops");

        recycler = (RecyclerView) view.findViewById(R.id.recyclerview);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);

        recycler.setLayoutManager(layoutManager);

        mAdapter = new FirebaseRecyclerAdapter<xyz.hemangkumar.rnfapp.models.Workshop, WorkshopHolder>(xyz.hemangkumar.rnfapp.models.Workshop.class, R.layout.workshop_list_item, WorkshopHolder.class, ref) {
            @Override
            public void populateViewHolder(WorkshopHolder workshopViewHolder, xyz.hemangkumar.rnfapp.models.Workshop workshop, int position) {
                workshopViewHolder.setTitle(workshop.getTitle());
                workshopViewHolder.setImg(workshop.getCategory(), workshop.getTitle(), getActivity());
                workshopViewHolder.setBlock(workshop.getVenue());
                workshopViewHolder.setDate(workshop.getWorkshop_date());
                workshopViewHolder.setTime(workshop.getWorkshop_time());
                workshopViewHolder.setListener(workshop.getDetails(), workshop.getOrganiser(), workshop.getContact(), getActivity());



            }


        };
        recycler.setAdapter(mAdapter);
        recycler.smoothScrollToPosition(mAdapter.getItemCount());


    }


    public static class WorkshopHolder extends RecyclerView.ViewHolder {
        View mView;

        public WorkshopHolder(View itemView) {
            super(itemView);
            mView = itemView;
        }

        public void setTitle(String name) {
            TextView field = (TextView) mView.findViewById(R.id.workshop_title);
            field.setText(name);
        }

        public void setDate(String name) {
            TextView field = (TextView) mView.findViewById(R.id.workshop_date);
            field.setText(name);
        }

        public void setTime(String name) {
            TextView field = (TextView) mView.findViewById(R.id.workshop_time);
            field.setText(name);
        }

        public void setBlock(String name) {
            if(name!=""){
                TextView field = (TextView) mView.findViewById(R.id.workshop_block);
                field.setText(name + " BLOCK");
            }

        }


        public void setImg(String category,String title, Context context){
            if(category.equals("Tech")) {
                LinearLayout linearLayout = (LinearLayout) mView.findViewById(R.id.linearLayoutSidebar);


                TextView textView = (TextView) mView.findViewById(R.id.sidebarText);
                if(title.length()>0)
                    textView.setText(String.valueOf(title.charAt(0)));
                textView.setBackgroundColor(Color.rgb(100, 0, 50));
                //Picasso.with(context).load("http://i.imgur.com/Cn8i0zx.png").fit().into(imgView);
            }
            else{
                LinearLayout linearLayout = (LinearLayout) mView.findViewById(R.id.linearLayoutSidebar);
          //      linearLayout.setBackgroundColor(Color.parseColor("#000"));

                TextView textView = (TextView) mView.findViewById(R.id.sidebarText);
                if(title.length()>0)
                    textView.setText(String.valueOf(title.charAt(0)));
                textView.setBackgroundColor(Color.rgb(50, 0, 100));
               // Picasso.with(context).load("http://i.imgur.com/nOklY4n.png").fit().into(imgView);
            }
        }

        public void setListener(String detail, String organiser, String contact, Context ctx){

            final String d = detail;
            final Context c = ctx;
            final String org = organiser, contactString = contact;

            Button btn = (Button) mView.findViewById(R.id.message_button);

            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final Dialog dialog = new Dialog(c);
                    dialog.setContentView(R.layout.dialog_fragment_workshop_list_item);
                    dialog.setTitle("Hello!");
                    TextView textViewUser = (TextView) dialog.findViewById(R.id.details_workshop_dialog);
                    textViewUser.setText(d);

                    TextView textViewOrg = (TextView) dialog.findViewById(R.id.workshop_organiser);
                    textViewOrg.setText(org);

                    TextView textViewContact = (TextView) dialog.findViewById(R.id.workshop_contact);
                    textViewContact.setText(contactString);

                    Button ok = (Button) dialog.findViewById(R.id.ok_button);
                    ok.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if(dialog.isShowing()){
                                dialog.dismiss();
                            }
                        }
                    });
                    dialog.show();

                }
            });

        }
    }

}
