package com.droidking.recyclerviewexample.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.droidking.recyclerviewexample.R;
import com.droidking.recyclerviewexample.database.MyDatabase;
import com.droidking.recyclerviewexample.model.Information;

import java.util.List;

/**
 * Created by Droid King on 21/07/2015.
 */
public class MainRecyclerViewAdapter extends RecyclerView.Adapter<MainRecyclerViewAdapter.MyMainViewHolder> {


    private List<Information> mainInfo;
    MyDatabase myDatabase;

    public MainRecyclerViewAdapter(List<Information> mainInfo) {
        this.mainInfo = mainInfo;
    }

    public class MyMainViewHolder extends RecyclerView.ViewHolder {
        TextView groupNameTextView, groupNotesTextView;
        ImageView groupImgFull;
        LinearLayout linearLayout;

        public MyMainViewHolder(View itemView) {
            super(itemView);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.mainLayout);
            groupNameTextView = (TextView) itemView.findViewById(R.id.group_name_TextView);
            groupNotesTextView = (TextView) itemView.findViewById(R.id.group_notes_TextView);
            groupImgFull = (ImageView) itemView.findViewById(R.id.group_Img_Full);
        }

    }


    @Override
    public MyMainViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_single_raw, parent, false);
        MyMainViewHolder holder = new MyMainViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(final MyMainViewHolder holder, final int position) {
        holder.groupNameTextView.setText(mainInfo.get(position).getGROUP_NAME());
        holder.groupNotesTextView.setText(mainInfo.get(position).getGROUP_NOTES());
        holder.groupImgFull.setImageResource(mainInfo.get(position).getGROUP_FULL_ICON_ID());


    }



    @Override
    public int getItemCount() {
        return mainInfo.size();
    }


}