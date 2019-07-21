package com.kanhaiya.placement.controller;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kanhaiya.placement.R;
import com.kanhaiya.placement.response.GetHomeUserResponse.Recommended;

import java.util.List;

public class UserViewCompanyCardAdapter extends RecyclerView.Adapter<UserViewCompanyCardAdapter.ViewHold> {
    Activity activity;
    List<Recommended> recommendedList;
    LayoutInflater inflater;

    public UserViewCompanyCardAdapter(Activity activity, List<Recommended> recommendedList) {
        this.activity = activity;
        this.recommendedList = recommendedList;
        inflater = LayoutInflater.from(activity);
    }

    @NonNull
    @Override
    public ViewHold onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHold(inflater.inflate(R.layout.user_view_company_card, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHold viewHold, int i) {
        viewHold.companyName.setText(recommendedList.get(i).getName());
        viewHold.designation.setText(recommendedList.get(i).getDesignation());
        viewHold.package_.setText(recommendedList.get(i).getPackage()+"");
        viewHold.place.setText(recommendedList.get(i).getLocation());

    }

    @Override
    public int getItemCount() {
        return recommendedList.size();
    }

    public class ViewHold extends RecyclerView.ViewHolder {
        TextView companyName, experience, designation, place, package_;

        public ViewHold(@NonNull View itemView) {

            super(itemView);
            companyName = itemView.findViewById(R.id.company_user_view);
            experience = itemView.findViewById(R.id.experience_user_view);
            designation = itemView.findViewById(R.id.designation_user_view);
            place = itemView.findViewById(R.id.place_user_view);
            package_ = itemView.findViewById(R.id.package_user_view);
        }
    }
}
