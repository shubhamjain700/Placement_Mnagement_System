package com.kanhaiya.placement.controller;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kanhaiya.placement.R;
import com.kanhaiya.placement.response.GetHomeUserResponse.Education;

import java.util.List;

class UserViewEducationAdapter extends RecyclerView.Adapter<UserViewEducationAdapter.ViewHolder> {
    private Activity activity;
    private LayoutInflater inflater;
    private List<Education> educationList;

    public UserViewEducationAdapter(Activity activity, List<Education> educationList) {
        this.activity = activity;
        this.educationList = educationList;
        inflater = LayoutInflater.from(activity);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(inflater.inflate(R.layout.education_detail_row, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        viewHolder._class.setText(educationList.get(i).getEdu_title());
        viewHolder.marks.setText(educationList.get(i).getMarks().toString());
        viewHolder.maxMarks.setText(educationList.get(i).getMax_marks().toString());


    }

    @Override
    public int getItemCount() {
        return educationList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView _class, marks, maxMarks;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            _class = itemView.findViewById(R.id._class);
            marks = itemView.findViewById(R.id.marks);
            maxMarks = itemView.findViewById(R.id.maxMarks);
        }
    }
}
