package com.kanhaiya.placement.controller;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Toast;

import com.kanhaiya.placement.R;
import com.kanhaiya.placement.databinding.ActivityListCandidateBinding;
import com.kanhaiya.placement.model.LoginInterface;
import com.kanhaiya.placement.model.SharedPrefManager;
import com.kanhaiya.placement.response.StuList.StudentList;
import com.kanhaiya.placement.response.company_list.CompanyList;
import com.kanhaiya.placement.response.company_list.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ListCandidateActivity extends AppCompatActivity {
    ActivityListCandidateBinding binding;
    List<String> companyNames = new ArrayList<>();
    List<com.kanhaiya.placement.response.StuList.Data> studentList = new ArrayList<>();
    List<Data> ccData = new ArrayList<>();
    ArrayAdapter<String> spinAdapter;
    StudentAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_list_candidate);

        binding.changePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListCandidateActivity.this, ChangePassword.class);
                intent.putExtra("type", "2");
                intent.putExtra("id", getIntent().getStringExtra("id"));
                startActivity(intent);
            }
        });

        binding.AddStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ListCandidateActivity.this,StudentForm.class);
                startActivity(intent);
            }
        });

        binding.myList.setLayoutManager(new LinearLayoutManager(this));
        getCompany();
        adapter = new StudentAdapter(this, studentList);
        binding.myList.setAdapter(adapter);

        spinAdapter = new ArrayAdapter(this, R.layout.spin_text, companyNames);

        binding.spinCompany.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position > 0) {
                    getStudentList(ccData.get(position - 1).getId() + "");

                } else {
                    Toast.makeText(ListCandidateActivity.this, "Select to karo...", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        binding.spinCompany.setAdapter(spinAdapter);


    }

    private void getStudentList(String id) {
        studentList.clear();
        adapter.notifyDataSetChanged();
        HashMap<String, String> param = new HashMap<>();
        param.put("user_id", "23");
        param.put("company_id", id);

        Retrofit retrofit = new Retrofit.Builder().baseUrl(LoginInterface.BASE_URL_HOME).addConverterFactory(GsonConverterFactory.create()).build();
        LoginInterface service = retrofit.create(LoginInterface.class);
        Call<StudentList> call = service.getStudent(param);
        call.enqueue(new Callback<StudentList>() {
            @Override
            public void onResponse(Call<StudentList> call, Response<StudentList> response) {
                if (response.body() != null) {
                    StudentList data = response.body();
                    if (data.getStatus()) {
                        studentList.addAll(data.getData());
                        adapter.notifyDataSetChanged();
                    } else {
                        Toast.makeText(ListCandidateActivity.this, data.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                }
            }

            @Override
            public void onFailure(Call<StudentList> call, Throwable t) {
                Toast.makeText(ListCandidateActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getCompany() {

        HashMap<String, String> param = new HashMap<>();
        param.put("user_id", new SharedPrefManager(this).getData("logInResponse").getId() + "");

        Retrofit retrofit = new Retrofit.Builder().baseUrl(LoginInterface.BASE_URL_HOME).addConverterFactory(GsonConverterFactory.create()).build();
        LoginInterface service = retrofit.create(LoginInterface.class);
        Call<CompanyList> call = service.getCompany(param);
        call.enqueue(new Callback<CompanyList>() {
            @Override
            public void onResponse(Call<CompanyList> call, Response<CompanyList> response) {
                if (response.body() != null) {
                    CompanyList data = response.body();
                    if (data.getStatus()) {
                        ccData = data.getData();
                        companyNames.add("Select Company");
                        for (int i = 0; i < data.getData().size(); i++) {
                            companyNames.add(data.getData().get(i).getName());
                            spinAdapter.notifyDataSetChanged();
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<CompanyList> call, Throwable t) {
                Toast.makeText(ListCandidateActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
