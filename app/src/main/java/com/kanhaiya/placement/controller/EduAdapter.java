package com.kanhaiya.placement.controller;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kanhaiya.placement.R;
import com.kanhaiya.placement.response.StudentFormDataModel.EducationModel;

import java.util.List;

public class EduAdapter extends RecyclerView.Adapter<EduAdapter.Items> {

    List<EducationModel> data;
    LayoutInflater inflater;

    public EduAdapter(Activity activity, List<EducationModel> data) {
        this.data = data;
        inflater = LayoutInflater.from(activity);
    }

    @NonNull
    @Override
    public Items onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new Items(inflater.inflate(R.layout.course_row, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull Items items, int i) {

        items.course.setText(data.get(i).getEducation());
        items.min.setText(data.get(i).getMarks());
        items.max.setText(data.get(i).getMax_marks());

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class Items extends RecyclerView.ViewHolder {
        TextView course, min, max;

        public Items(@NonNull View itemView) {
            super(itemView);
            course = itemView.findViewById(R.id.txt_course);
            min = itemView.findViewById(R.id.edt_min);
            max = itemView.findViewById(R.id.edt_max);
        }

    }
}
