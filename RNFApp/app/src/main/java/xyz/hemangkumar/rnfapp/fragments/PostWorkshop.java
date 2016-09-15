package xyz.hemangkumar.rnfapp.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.borax12.materialdaterangepicker.date.DatePickerDialog;
import com.borax12.materialdaterangepicker.time.RadialPickerLayout;
import com.borax12.materialdaterangepicker.time.TimePickerDialog;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

import xyz.hemangkumar.rnfapp.R;
import xyz.hemangkumar.rnfapp.models.*;
import xyz.hemangkumar.rnfapp.models.Workshop;
import xyz.hemangkumar.rnfapp.utils.Months;

/**
 * Created by Hemang on 04/09/16.
 */
public class PostWorkshop extends Fragment implements TimePickerDialog.OnTimeSetListener, DatePickerDialog.OnDateSetListener, View.OnClickListener{

    TextView time_text_view;
    TextView date_text_view;
    Button btn1, btn2;
    DatePickerDialog dpd;
    TimePickerDialog tpd;
    FloatingActionButton fab;
    EditText workshopTitle, instructorNotes, organiserInfo, contactInfo;
    String workshop_title = "",time = "", date="", block="", category="", details= "", organiser = "", contact = "";
    RadioGroup blockRadioGroup, categoryRadioGroup;
    RadioButton blockRadioButton, categoryRadioButton;
    View fragment_view;

    public static PostWorkshop newInstance() {

        Bundle args = new Bundle();

        PostWorkshop fragment = new PostWorkshop();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        return inflater.inflate(R.layout.fragment_post_workshop, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);
        fragment_view = view;

       // getActivity().getActionBar().setTitle("Post Workshop");

        time_text_view = (TextView) view.findViewById(R.id.timeTextView);
        date_text_view = (TextView) view.findViewById(R.id.dateTextView);

        Calendar now = Calendar.getInstance();
        dpd = DatePickerDialog.newInstance(
                    this,
                    now.get(Calendar.YEAR),
                    now.get(Calendar.MONTH),
                    now.get(Calendar.DAY_OF_MONTH)
        );



        tpd = TimePickerDialog.newInstance(
                    this,
                    now.get(Calendar.HOUR_OF_DAY),
                    now.get(Calendar.MINUTE),
                    false
        );


        btn1 = (Button) view.findViewById(R.id.button);
        btn2 = (Button) view.findViewById(R.id.button2);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);


        workshopTitle = (EditText) view.findViewById(R.id.workshopEditText);

        instructorNotes = (EditText) view.findViewById(R.id.instructorEditText);

        organiserInfo = (EditText) view.findViewById(R.id.instructorNameText);

        contactInfo = (EditText) view.findViewById(R.id.instructorContactText);

        fab = (FloatingActionButton) view.findViewById(R.id.fab);
        fab.setOnClickListener(this);

        blockRadioGroup = (RadioGroup) view.findViewById(R.id.block_radio_group);
        int blockId = blockRadioGroup.getCheckedRadioButtonId();
        blockRadioButton = (RadioButton) view.findViewById(blockId);

        categoryRadioGroup = (RadioGroup) view.findViewById(R.id.category_radio_group);
        int categoryId = categoryRadioGroup.getCheckedRadioButtonId();
        categoryRadioButton = (RadioButton) view.findViewById(categoryId);


        }


        @Override
        public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth, int yearEnd, int monthOfYearEnd, int dayOfMonthEnd) {
            date = String.valueOf(year) + "-" + String.valueOf(monthOfYear) + "-" + String.valueOf(dayOfMonth) + " to " + String.valueOf(yearEnd) + "-" + String.valueOf(monthOfYearEnd) + "-" + String.valueOf(dayOfMonthEnd);
            date = String.valueOf(Months.getMonthFromDate(String.valueOf(monthOfYear+1))) + " " + String.valueOf(dayOfMonth);

            date_text_view.setText("Date : " + date);
        }

        @Override
        public void onTimeSet(RadialPickerLayout view, int hourOfDay, int minute, int hourOfDayEnd, int minuteEnd) {
            String hourString = hourOfDay < 10 ? "0"+hourOfDay : ""+hourOfDay;
            String minuteString = minute < 10 ? "0"+minute : ""+minute;
            String hourStringEnd = hourOfDayEnd < 10 ? "0"+hourOfDayEnd : ""+hourOfDayEnd;
            String minuteStringEnd = minuteEnd < 10 ? "0"+minuteEnd : ""+minuteEnd;
            time = hourString+":"+minuteString+" to "+hourStringEnd+":"+minuteStringEnd;
            String displayTime = "Time : " + time;
            time_text_view.setText(displayTime);
        }

        @Override
        public void onClick(View v) {
            switch(v.getId()){
                case R.id.button:
                    dpd.show(getActivity().getFragmentManager(), "Pick a Date");
                    break;
                case R.id.button2:
                    tpd.show(getActivity().getFragmentManager(), "Pick a Time");
                    break;
                case R.id.fab:

                    workshop_title = workshopTitle.getText().toString();
                    details = instructorNotes.getText().toString();
                    organiser = organiserInfo.getText().toString();
                    contact = contactInfo.getText().toString();

                    block = getBlockFromView();
                    category = getCategoryFromView();

                    if( workshop_title.equals("") ){
                        Toast.makeText(getActivity(), "Workshop Title not specified!", Toast.LENGTH_SHORT).show();
                        break;
                    }

                    if( organiser.equals("") ){
                        Toast.makeText(getActivity(), "Organiser not specified!", Toast.LENGTH_SHORT).show();
                        break;
                    }

                    if( contact.equals("") ){
                        Toast.makeText(getActivity(), "Contact Details not specified!", Toast.LENGTH_SHORT).show();
                        break;
                    }

                    if(date == ""){
                        Toast.makeText(getActivity(), "Date not specified!", Toast.LENGTH_SHORT).show();
                        break;
                    }

                    if(time == ""){
                        Toast.makeText(getActivity(), "Time not specified!", Toast.LENGTH_SHORT).show();
                        break;
                    }

                    if(category == ""){
                        Toast.makeText(getActivity(), "Select a category!", Toast.LENGTH_SHORT).show();
                        break;
                    }



                    DatabaseReference ref = FirebaseDatabase.getInstance().getReference("workshops");
                    Workshop wsp = new Workshop(workshop_title, block, date, time,details, category, organiser, contact );
                    ref.push().setValue(wsp);
                    Snackbar.make(getView(), "Workshop Added Successfully!", Snackbar.LENGTH_SHORT).show();
                    break;
            }

    }

    public String getBlockFromView(){

        int blockId = blockRadioGroup.getCheckedRadioButtonId();
        blockRadioButton = (RadioButton) fragment_view.findViewById(blockId);

        if(blockRadioButton!=null)
        {
            return blockRadioButton.getText().toString();
        }

        return "";

    }

    public String getCategoryFromView(){

        int categoryId = categoryRadioGroup.getCheckedRadioButtonId();
        categoryRadioButton = (RadioButton) fragment_view.findViewById(categoryId);

        if(categoryRadioButton != null){
            return categoryRadioButton.getText().toString();
        }

        return "";
    }
}
