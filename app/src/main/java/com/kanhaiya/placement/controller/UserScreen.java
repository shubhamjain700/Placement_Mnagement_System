package com.kanhaiya.placement.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.kanhaiya.placement.R;
import com.kanhaiya.placement.model.LoginInterface;
import com.kanhaiya.placement.response.GetHomeUserResponse.Education;
import com.kanhaiya.placement.response.GetHomeUserResponse.GetHomeUser;
import com.kanhaiya.placement.response.GetHomeUserResponse.Recommended;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UserScreen extends AppCompatActivity {

    private TextView name, rollno, appearedIn, stream, batch;
    List<Recommended> recommendedCompanyList = new ArrayList<>();
    List<Education> educationList = new ArrayList<>();
    UserViewEducationAdapter educationAdapter;
    UserViewCompanyCardAdapter userViewCompanyCardAdapter;
    RecyclerView recyclerView, educationRecyclerView;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_screen);


        name = findViewById(R.id.user_view_name);
        rollno = findViewById(R.id.user_view_rollNo);
        appearedIn = findViewById(R.id.user_view_appearedIn);
        stream = findViewById(R.id.user_view_stream);
        batch = findViewById(R.id.user_view_batch);
        button = findViewById(R.id.change_password);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserScreen.this, ChangePassword.class);
                intent.putExtra("type", "2");
                intent.putExtra("id", getIntent().getStringExtra("userId"));
                startActivity(intent);
            }
        });

        //Recycler View
        recyclerView = findViewById(R.id.user_view_recyclerView);
        educationRecyclerView = findViewById(R.id.education_detail);


        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        educationRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        educationAdapter=new UserViewEducationAdapter(this,educationList);
        educationRecyclerView.setAdapter(educationAdapter);
        userViewCompanyCardAdapter = new UserViewCompanyCardAdapter(this, recommendedCompanyList);
        recyclerView.setAdapter(userViewCompanyCardAdapter);

        getUserData(getIntent().getStringExtra("userId"));

    }

    private void getUserData(String userId) {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(LoginInterface.BASE_URL_HOME).addConverterFactory(GsonConverterFactory.create()).build();
        LoginInterface api = retrofit.create(LoginInterface.class);
        Call<GetHomeUser> response = api.getHomeUser(userId);
        response.enqueue(new Callback<GetHomeUser>() {
            @Override
            public void onResponse(Call<GetHomeUser> call, Response<GetHomeUser> response) {
                if (response.body() != null) {
                    if (response.body().getStatus()) {
                        GetHomeUser data = response.body();
                        updateUI(data);
                        recommendedCompanyList.addAll(data.getData().getRecommended());
                        educationList.addAll(data.getData().getEducation());
                        educationAdapter.notifyDataSetChanged();
                        userViewCompanyCardAdapter.notifyDataSetChanged();
                    } else
                        Toast.makeText(UserScreen.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                } else Toast.makeText(UserScreen.this, "nullBody", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<GetHomeUser> call, Throwable t) {
                Log.e("Failure", "onFailure: " + t.getMessage());

            }
        });

    }

    private void updateUI(GetHomeUser data) {
        name.setText(data.getData().getUser_data().getName());
        rollno.setText(data.getData().getEducation().get(0).getRoll_no().toString());
        appearedIn.setText(data.getData().getUser_data().getAppeared_in().toString());
        stream.setText(data.getData().getUser_data().getStream());
        batch.setText(data.getData().getUser_data().getBatch());
    }
}
