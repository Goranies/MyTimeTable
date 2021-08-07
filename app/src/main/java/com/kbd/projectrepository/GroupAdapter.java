package com.kbd.projectrepository;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class GroupAdapter extends RecyclerView.Adapter<GroupAdapter.Viewholder> {

    private Context context;
    private ArrayList<Group> groupArrayList;

    public GroupAdapter(Context context, ArrayList<Group> groupArrayList) {
        this.context = context;
        this.groupArrayList = groupArrayList;
    }

    @NonNull
    @Override
    public GroupAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.group_layout, parent, false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GroupAdapter.Viewholder holder, int position) {
        Group group = groupArrayList.get(position);
        holder.groupName.setText(group.getGroupName());
    }

    @Override
    public int getItemCount() {
        return groupArrayList.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        private TextView groupName;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            groupName = itemView.findViewById(R.id.wizard_card_editText_groupName);
        }
    }
}
