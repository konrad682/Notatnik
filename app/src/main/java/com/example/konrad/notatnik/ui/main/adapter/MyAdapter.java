package com.example.konrad.notatnik.ui.main.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.konrad.notatnik.R;
import com.example.konrad.notatnik.ui.main.storage.StorageTask;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private ArrayList<StorageTask> mAddedTaskArrayList = new ArrayList<>();
    private OnItemClickListener listener;
    private OnItemLongClickListener long_listener;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView mTitleTextView;
        private TextView mModificationTextView;


        public MyViewHolder(View itemView) {
            super(itemView);
            mTitleTextView = itemView.findViewById(R.id.TitleTextView);
            mModificationTextView = itemView.findViewById(R.id.modificationTextView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if(listener != null && position != RecyclerView.NO_POSITION)
                    listener.onItemClick(mAddedTaskArrayList.get(position),position);
                }
            });

            itemView.setOnLongClickListener(new View.OnLongClickListener(){
                @Override
                public boolean onLongClick(View v) {
                    int position = getAdapterPosition();
                    if(listener != null && position != RecyclerView.NO_POSITION)
                    long_listener.onItemLongClick(position);

                    return true;
                }
            });

        }
    }

    public MyAdapter(ArrayList<StorageTask> mAddedTaskArrayList) {
        this.mAddedTaskArrayList = mAddedTaskArrayList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

       View itemView = LayoutInflater.from(parent.getContext())
               .inflate(R.layout.currency_layout,parent,false);

        MyViewHolder myViewHolder = new MyViewHolder(itemView);

        return myViewHolder;

    }

    public interface OnItemClickListener{
        void onItemClick(StorageTask storagetask,int position);
    }

    public interface OnItemLongClickListener{
        boolean onItemLongClick(int position);
    }



    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener = listener;
    }
    public void setOnItemLongClickListener(OnItemLongClickListener long_listener){
        this.long_listener = long_listener;
    }


    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.mTitleTextView.setText(mAddedTaskArrayList.get(position).getmTitle());
        holder.mModificationTextView.setText("Last modification: " + mAddedTaskArrayList.get(position).getmLastModificationDate());
    }

    @Override
    public int getItemCount() {
        return mAddedTaskArrayList.size();
    }

    public void deleteTask(int position){
        mAddedTaskArrayList.remove(position);
    }

}