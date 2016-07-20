package appcamp.hemang.simplefragmentapp;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by Hemang on 07/07/16.
 */
public class SimpleFragment extends Fragment implements View.OnClickListener {
    String fragString;
    Button fragButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        //THIS PLACE IS FOR INITIALIZING EVERYTHING EXCEPT ANY UI ELEMENT!!
        super.onCreate(savedInstanceState);
        fragString = "Hello ! I am a fragment string";

        /*
        * We did not set the view or attempt to get a reference to our Button member variable, myButton.
        When using Fragment, we need to do this in the onCreateView method.
        * */
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        super.onCreateView(inflater, container, savedInstanceState);
        //THIS IS THE PLACE FOR VIEW ELEMENTS/ UI
        /*
        Let's override this now and see how we set the view and get a reference to our Button.
        * */

        /* First Thing - See that the return type is View. Which means we have to return the view of Fragment

        /* Second Thing - See the arguments LayoutInflater, ViewGroup, Bundle

           1.  We need LayoutInflater as we cannot call setContentView because Fragment provides no such method.
           2.  We use container that was passed in to onCreateView as an argument in the inflate method.
           3.  The container variable is a reference to the layout in activity_ main.xml OR
               is a reference to any of the layout of any activity where you want to embed this Fragment.
               ***SUPER-FLEXIBLE***
               * :D :D
           4.  The third argument of onCreateView is Bundle savedInstanceState,
               which is there to help us maintain the data that our fragments hold.


        */



        /*
        STEP ONE
        Use the inflate method of inflater to inflate our layout contained in fragment_layout.xml
        and initialize view (an object of the type View) with the result.

        The third argument that we pass into inflate is false,
         which means that we don't want our layout to be immediately added to the containing layout.
         We will do this soon from another part of the code.
       */
        View view = inflater.inflate(R.layout.fragment_layout, container, false);


        /*
        STEP TWO
        Now that we have an inflated layout contained in view,
        we can use this to get a reference to UI WIDGETS IN fragment_layout.xml file, like this:
        */
        fragButton = (Button) view.findViewById(R.id.button);

        /*
         STEP THREE
         Add App Logic

         Remember to use getActivity() to get a reference to the Activity that contains Fragment.
         */

        fragButton.setOnClickListener(this);

        /*
        STEP FOUR
        Return View
         */

        return view;

        /*STEP FIVE
        Go to the Activity that contains Fragment
        We need to create an instance of this fragment class and initialize it appropriately.
        This is where FragmentManager will get introduced.

         */

    }

    @Override
    public void onClick(View v) {
        Toast.makeText(getActivity(), "Hey Fragment Toaster!!", Toast.LENGTH_SHORT).show();
    }
}
