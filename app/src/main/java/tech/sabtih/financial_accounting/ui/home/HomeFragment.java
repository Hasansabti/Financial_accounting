package tech.sabtih.financial_accounting.ui.home;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import tech.sabtih.financial_accounting.R;
import tech.sabtih.financial_accounting.adapter.MyContractsRecyclerViewAdapter;
import tech.sabtih.financial_accounting.fragments.AddAccountDialog;
import tech.sabtih.financial_accounting.listeners.OnContractInteractionListener;
import tech.sabtih.financial_accounting.listeners.OnListInteractionListener;
import tech.sabtih.financial_accounting.models.Contract;
import tech.sabtih.financial_accounting.models.User;
import tech.sabtih.financial_accounting.utils.AccountingDbHelper;
import tech.sabtih.financial_accounting.utils.UserEntry;

public class HomeFragment extends Fragment implements OnContractInteractionListener, View.OnLongClickListener {

    private HomeViewModel homeViewModel;

    RecyclerView contracts;
    ImageButton addacc;
    MyContractsRecyclerViewAdapter adapter;

    OnListInteractionListener mListener;

    AccountingDbHelper dbhelper;

    ArrayList<User> users;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        mListener = (OnListInteractionListener) getActivity();

        dbhelper = AccountingDbHelper.newInstance(getContext());
        SQLiteDatabase db = dbhelper.getReadableDatabase();
         Cursor cursor = db.query(UserEntry.TABLE_NAME.getText(),null,null,null,null,null,null);

         users = new ArrayList<>();

         while (cursor.moveToNext()){
             User user;

             String id = cursor.getString(cursor.getColumnIndex(UserEntry.COLUMN_ID.getText()));
             String name = cursor.getString(cursor.getColumnIndex(UserEntry.COLUMN_NAME.getText()));
             String phone = cursor.getString(cursor.getColumnIndex(UserEntry.COLUMN_PHONE.getText()));

             user = new User(id,name,phone);

             users.add(user);


         }




        contracts = root.findViewById(R.id.mContracts);
        adapter = new MyContractsRecyclerViewAdapter(users,this);
        contracts.setLayoutManager(new LinearLayoutManager(contracts.getContext()));
        contracts.setAdapter(adapter);



        addacc = root.findViewById(R.id.bAdd);

        addacc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getActivity().getSupportFragmentManager();
                AddAccountDialog addd = new AddAccountDialog(HomeFragment.this);
                addd.show(fm,"addacc");

            }
        });



        final TextView textView = root.findViewById(R.id.text_home);
        homeViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }

    @Override
    public void onContractClick(Contract contract) {

    }

    @Override
    public void onUserClick(User mItem) {

    }

    @Override
    public void onUserLongClick(User mItem) {
        mListener.onSelectModeStarted();
    }

    @Override
    public void onUserCreated(User user) {
            users.add(user);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void selectionUpdated(int selected) {
        mListener.onSelectionUpdated(selected);

        if(selected == 0){
            adapter.setSelectionmode(false);
        }
    }


    @Override
    public boolean onLongClick(View v) {

        return false;
    }

    public void selectionCanceled() {
        adapter.setSelectionmode(false);
        adapter.notifyDataSetChanged();
    }

    public void deleteSelected() {
        ArrayList<User> deleted = new ArrayList<>();
        for(User user : users){
            if(user.isSelected()){
                deleted.add(user);
            }
        }
        SQLiteDatabase db = dbhelper.getWritableDatabase();
        for(User user : deleted){
            users.remove(user);

            db.execSQL("delete from "+UserEntry.TABLE_NAME.getText()+" where "+UserEntry.COLUMN_ID.getText() +" = '" +user.getUid()+"'");

        }

        adapter.setSelected(adapter.getSelected()-deleted.size());

        adapter.notifyDataSetChanged();
        adapter.setSelectionmode(false);
        mListener.onSelectModeEnded();
    }
}