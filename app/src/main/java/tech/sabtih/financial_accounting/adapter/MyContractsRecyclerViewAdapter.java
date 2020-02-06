package tech.sabtih.financial_accounting.adapter;

import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import tech.sabtih.financial_accounting.R;
import tech.sabtih.financial_accounting.dummy.DummyContent.DummyItem;
import tech.sabtih.financial_accounting.listeners.OnContractInteractionListener;
import tech.sabtih.financial_accounting.models.Contract;
import tech.sabtih.financial_accounting.models.User;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link DummyItem} and makes a call to the
 * specified {@link tech.sabtih.financial_accounting.listeners.OnContractInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyContractsRecyclerViewAdapter extends RecyclerView.Adapter<MyContractsRecyclerViewAdapter.ViewHolder> {

    private final List<User> mValues;
    private final OnContractInteractionListener mListener;

    public MyContractsRecyclerViewAdapter(List<User> items, OnContractInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_contracts, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
       // holder.mIdView.setText(mValues.get(position).id);
       // holder.mContentView.setText(mValues.get(position).content);
        holder.mName.setText(holder.mItem.getName());
        holder.mAmount.setText("0");


        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onUserClick(holder.mItem);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final ImageView Bview;
        public final TextView mName;
        public final TextView mCount;
        public final TextView mAmount;
        public final ImageView Ttype;


        public User mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;

            Bview =  view.findViewById(R.id.bShow);
            mName = (TextView) view.findViewById(R.id.muser);
            mCount = view.findViewById(R.id.mcount);
            mAmount = view.findViewById(R.id.mmoney);
            Ttype = view.findViewById(R.id.mType);


        }

        @Override
        public String toString() {
            return super.toString() + " '" + mName.getText() + "'";
        }
    }
}
