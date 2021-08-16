package com.kbd.projectrepository;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class GroupAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

//    private static final int TYPE_HEADER = 0;
    private static final int TYPE_FOOTER = 1;
    private static final int TYPE_ITEM = 2;

    private Context context;
    private ArrayList<Group> groupArrayList;

    public GroupAdapter(Context context, ArrayList<Group> groupArrayList) {
        this.context = context;
        this.groupArrayList = groupArrayList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(viewType == TYPE_ITEM) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.group_layout, parent, false);
            return new ItemViewHolder(view);
        } else if(viewType == TYPE_FOOTER) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.footer_layout, parent, false);
            return new FooterViewHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        if (holder instanceof FooterViewHolder) {
            FooterViewHolder footerHolder = (FooterViewHolder) holder;
            footerHolder.footerButton.setText("그룹 추가");
            footerHolder.footerButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //그룹 추가 버튼 누를 시 할 일
                    Log.d("POSITION_size",Integer.toString(groupArrayList.size()+1));
                    WizardActivity w = (WizardActivity) context;
                    w.insertGroup(new Group(groupArrayList.size()+1));
                }
            });
        } else if (holder instanceof ItemViewHolder) {
            ItemViewHolder itemViewHolder = (ItemViewHolder) holder;
            itemViewHolder.groupName.setText(groupArrayList.get(position).getGroupName());

            itemViewHolder.deleteGroup.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //그룹 삭제 버튼을 누르면 할 일
                    Log.d("POSITION",Integer.toString(position));
                    WizardActivity w = (WizardActivity) context;
                    w.deleteGroup(((ItemViewHolder) holder).groupName.getText().toString());

                    groupArrayList.remove(position);
                    notifyItemRemoved(position);
                    notifyDataSetChanged();
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return groupArrayList.size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == groupArrayList.size()) {
            return TYPE_FOOTER;
        }
        return TYPE_ITEM;
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {
        private TextView groupName;
        private ImageButton editName;
        private ImageButton addTime;
        private ImageButton deleteGroup;
        private InputMethodManager imm;
        private String prevName;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            groupName = itemView.findViewById(R.id.wizard_card_editText_groupName);
            editName = itemView.findViewById(R.id.wizard_card_button_editName);
            addTime = itemView.findViewById(R.id.wizard_card_button_addTime);
            deleteGroup = itemView.findViewById(R.id.wizard_card_button_deleteGroup);

            imm = (InputMethodManager) context.getSystemService(context.INPUT_METHOD_SERVICE);

            groupName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    if(hasFocus) {
                        imm.showSoftInput(v, 0);
                        prevName = groupName.getText().toString();
                    } else {
                        groupName.setFocusable(false);
                        groupName.setFocusableInTouchMode(false);
                        WizardActivity w = (WizardActivity) context;
                        w.renameGroup(prevName, groupName.getText().toString());
                    }
                }
            });

            groupName.setOnKeyListener(new View.OnKeyListener() {
                @Override
                public boolean onKey(View v, int keyCode, KeyEvent event) {
                    if(event.getAction() == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                        imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                        return true;
                    }
                    return false;
                }
            });

            editName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    groupName.setFocusable(true);
                    groupName.setFocusableInTouchMode(true);
                    groupName.requestFocus();
                }
            });

            addTime.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //강의 시간 추가 버튼을 누르면 할 일
                }
            });
        }
    }

    public class FooterViewHolder extends RecyclerView.ViewHolder {
        Button footerButton;

        public FooterViewHolder(@NonNull View itemView) {
            super(itemView);
            footerButton = itemView.findViewById(R.id.footer_button_addGroup);
        }
    }
}
