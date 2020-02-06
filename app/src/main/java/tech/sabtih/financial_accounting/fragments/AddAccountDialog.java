package tech.sabtih.financial_accounting.fragments;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.UUID;

import tech.sabtih.financial_accounting.R;
import tech.sabtih.financial_accounting.listeners.OnContractInteractionListener;
import tech.sabtih.financial_accounting.models.User;
import tech.sabtih.financial_accounting.utils.AccountingDbHelper;
import tech.sabtih.financial_accounting.utils.UserEntry;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class AddAccountDialog extends DialogFragment {

    Button BDate;
    ImageView bSave;
    ImageView bAdd;

    TextView mName;
    TextView mAmount;
    TextView mPhone;
    TextView mDetails;

    OnContractInteractionListener mListener;


    final Calendar myCalendar = Calendar.getInstance();

    public AddAccountDialog(OnContractInteractionListener mListener) {
        this.mListener = mListener;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View vDialog = inflater.inflate(R.layout.dialog_addaccount, null);

        BDate = vDialog.findViewById(R.id.date);
        bSave = vDialog.findViewById(R.id.BSave);
        bAdd = vDialog.findViewById(R.id.bAdd);
        mDetails = vDialog.findViewById(R.id.details);
        mName = vDialog.findViewById(R.id.username);
        mAmount = vDialog.findViewById(R.id.mAmount);
        mPhone = vDialog.findViewById(R.id.mPhone);




        updateLabel();

        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

        };


        BDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(getContext(), date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });


        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        builder.setView(vDialog)
        ;
        AccountingDbHelper dbHelper = new AccountingDbHelper(getContext());
        final SQLiteDatabase db = dbHelper.getWritableDatabase();

        // db.execSQL("DROP TABLE IF EXISTS "+ UserEntry.TABLE_NAME.getText());

        bSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (validate()) {
                    String userid = UUID.randomUUID().toString();

                    ContentValues values = new ContentValues();
                    values.put(UserEntry.COLUMN_ID.getText(), userid );
                    values.put(UserEntry.COLUMN_NAME.getText(), mName.getText().toString());
                    values.put(UserEntry.COLUMN_PHONE.getText(), mPhone.getText().toString());

                    long rowid = db.insert(UserEntry.TABLE_NAME.getText(), null, values);
                    Log.d(TAG, "onClick: Row Added " + rowid);


                    mListener.onUserCreated(new User(userid,mName.getText().toString(),mPhone.getText().toString()));

                    AddAccountDialog.this.dismiss();
                }
            }
        });

        return builder.create();
    }

    private void updateLabel() {
        String myFormat = "dd/MM/yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        BDate.setText(sdf.format(myCalendar.getTime()));
    }

    public boolean validate() {
        boolean valid = true;

        if (mName.getText().toString().length() < 2) {
            mName.setError(getString(R.string.name_too_short));
            valid = false;
        }

        return valid;


    }
}
