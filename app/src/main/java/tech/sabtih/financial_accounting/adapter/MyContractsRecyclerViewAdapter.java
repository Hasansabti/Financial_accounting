package tech.sabtih.financial_accounting.adapter;

import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import tech.sabtih.financial_accounting.R;
import tech.sabtih.financial_accounting.listeners.OnContractInteractionListener;
import tech.sabtih.financial_accounting.models.User;
import tech.sabtih.financial_accounting.ui.home.HomeFragment;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link User} and makes a call to the
 * specified {@link tech.sabtih.financial_accounting.listeners.OnContractInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyContractsRecyclerViewAdapter extends RecyclerView.Adapter<MyContractsRecyclerViewAdapter.ViewHolder> {

    private final List<User> mValues;
    private final OnContractInteractionListener mListener;
    private boolean selectionmode = false;

    int selected = 0;

    public MyContractsRecyclerViewAdapter(List<User> items, OnContractInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }


    public void setSelectionmode(boolean state){
        selectionmode = state;
        if(!state){
            for(User user : mValues){
                user.setSelected(false);
            }
        }
    }

    public int getSelected() {
        return selected;
    }

    public void setSelected(int selected) {
        this.selected = selected;
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
        if(selectionmode && holder.mItem.isSelected()){
            holder.mView.setBackgroundColor(((HomeFragment)mListener ).getActivity().getColor(R.color.colorSelection) );
        }else{
            holder.mView.setBackgroundColor(Color.TRANSPARENT);

        }

        holder.mView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                selectionmode = true;
                mListener.onUserLongClick(holder.mItem);
                holder.mItem.setSelected(true);
                selected++;
                mListener.selectionUpdated(selected);
                notifyDataSetChanged();
                return false;
            }
        });


        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onUserClick(holder.mItem);

                    if(selectionmode){
                        if(holder.mItem.isSelected()){
                            selected--;
                            if(selected == 0){

                            }
                        }else{
                            selected++;
                        }
                            holder.mItem.setSelected(!holder.mItem.isSelected());

                        mListener.selectionUpdated(selected);
                            notifyDataSetChanged();

                    }
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
