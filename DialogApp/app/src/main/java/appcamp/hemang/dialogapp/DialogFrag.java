package appcamp.hemang.dialogapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.view.View;

public class DialogFrag extends DialogFragment{


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        super.onCreateDialog(savedInstanceState);

        //Use a Builder to build a structure for dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());


        /*Two ways of writing methods in Android */
        /*
            First Way : Toast.makeText(args).show();

            Second Way (called Chaining) : Toast.makeText(args)
                                                .show();

         */

        //build the structure
        builder.setMessage("Hello! Select an Option")
                .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Snackbar.make(getActivity().findViewById(R.id.activity_main), "Pressed Cancel", Snackbar.LENGTH_SHORT)
                                .show();
                    }
                })
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Snackbar.make(getActivity().findViewById(R.id.activity_main) , "Okay", Snackbar.LENGTH_LONG)
                                .show();
                    }
                });

        /*  Note */

        // Usually inside Snackbar.make(view, text, time)
        // In place of view, we write getView()
        // But here we want our snackbar to come on mainActivity's view not inside dialog bar,
        // and thus we call the activity's view and not the fragment's view
        // we call activity's view using
        // getActivity().findViewById(R.id.<activity's layout file's id>


        //Create the built structure in to a dialog
        Dialog dialog =  builder.create();


        //return the dialog object
        return dialog;
    }
}