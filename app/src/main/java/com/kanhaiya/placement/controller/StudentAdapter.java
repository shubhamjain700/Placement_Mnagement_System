package com.kanhaiya.placement.controller;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.kanhaiya.placement.R;
import com.kanhaiya.placement.response.StuList.Data;

import java.util.List;

public class
StudentAdapter extends RecyclerView.Adapter<StudentAdapter.Items> {

    LayoutInflater inflater;
    Context context;
    List<Data> data;

    public StudentAdapter(Activity activity, List<Data> data) {
        inflater = LayoutInflater.from(activity);
        this.data = data;
        context = activity;
    }

    @NonNull
    @Override
    public Items onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new Items(inflater.inflate(R.layout.stu_list_row, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull Items holder, final int position) {
        holder.name.setText(data.get(position).getName());
        holder.roll.setText(data.get(position).getRoll_no());
        holder.cardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class Items extends RecyclerView.ViewHolder {
        TextView name, roll;
        ImageButton imagebutton;
        CardView cardview;

        public Items(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.txt_name);
            roll = itemView.findViewById(R.id.txt_roll);
            imagebutton = itemView.findViewById(R.id.imagebutton);
            cardview = itemView.findViewById(R.id.cardview);
        }
    }

}
