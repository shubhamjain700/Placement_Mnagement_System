package com.kanhaiya.placement.controller;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.gson.Gson;
import com.kanhaiya.placement.R;
import com.kanhaiya.placement.model.LoginInterface;
import com.kanhaiya.placement.model.StudentForm.StudentFormResponse;
import com.kanhaiya.placement.response.CourseList.Data;
import com.kanhaiya.placement.response.CourseList.EducationDetail;
import com.kanhaiya.placement.response.StudentFormDataModel.EducationModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class StudentForm extends AppCompatActivity {

    RecyclerView recyclerView;
    EduAdapter adapter;
    List<EducationModel> data = new ArrayList<>();
    List<Data> courseDetails = new ArrayList<>();
    EditText email, st_name, mobile, password, stream, skill, batch, total_apCompanies;
    ImageView signature;
    Button btn;
    static int id = 0;
    ArrayAdapter<String> courseAdapter;
    List<String> courseList = new ArrayList<>();
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_form);

        email = findViewById(R.id.edt_email);
        st_name = findViewById(R.id.stdfrm_std_name);
        mobile = findViewById(R.id.edt_mobile);
        password = findViewById(R.id.edt_pass);
        stream = findViewById(R.id.edt_stream);
        skill = findViewById(R.id.edt_skill);
        batch = findViewById(R.id.edt_batch);
        total_apCompanies = findViewById(R.id.stdfrm_totalCompanies);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please wait...");
        progressDialog.setCancelable(false);

        signature = findViewById(R.id.stdfrm_signature);
        btn = findViewById(R.id.submit_btn);

        recyclerView = findViewById(R.id.list_edu);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new EduAdapter(this, data);
        recyclerView.setAdapter(adapter);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog.show();
                validation();

            }
        });

        findViewById(R.id.add_more).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addMore();
            }
        });

    }

    private void validation() {
        if (!email.getText().toString().contains("@") || !email.getText().toString().contains(".") || email.getText().toString().trim().length() < 10) {
            progressDialog.dismiss();
            Toast.makeText(StudentForm.this, "Enter Valid email id", Toast.LENGTH_SHORT).show();
        } else if (st_name.getText().toString().trim().isEmpty()) {
            progressDialog.dismiss();
            Toast.makeText(StudentForm.this, "Enter Valid name", Toast.LENGTH_SHORT).show();
        } else if (mobile.getText().toString().trim().length() < 10) {
            progressDialog.dismiss();
            Toast.makeText(StudentForm.this, "Enter Valid mobile number", Toast.LENGTH_SHORT).show();
        } else if (password.getText().toString().trim().length() < 4) {
            progressDialog.dismiss();
            Toast.makeText(StudentForm.this, "Password must be greater then 4", Toast.LENGTH_SHORT).show();
        } else if (stream.getText().toString().trim().length() < 3) {
            progressDialog.dismiss();
            Toast.makeText(StudentForm.this, "Enter Valid stream", Toast.LENGTH_SHORT).show();
        } else if (batch.getText().toString().trim().length() < 4) {
            progressDialog.dismiss();
            Toast.makeText(StudentForm.this, "Invalid Batch", Toast.LENGTH_SHORT).show();
        } else submitFormDetails();

    }

    private void addMore() {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.add_qualification_form);
        getCourseList();

        //Add qualification form details

        final Spinner eduSpinner = dialog.findViewById(R.id.course_spinner);
        final EditText edtRoll = dialog.findViewById(R.id.edt_roll);
        final EditText edtMarks = dialog.findViewById(R.id.edt_marks);
        final EditText edtTotalMarks = dialog.findViewById(R.id.edt_total_marks);
        courseAdapter = new ArrayAdapter<>(StudentForm.this, R.layout.education_details, courseList);

        Button dialogCancel = dialog.findViewById(R.id.btn_cancel);
        dialogCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                courseList.clear();
                courseDetails.clear();
                dialog.dismiss();
            }
        });
        eduSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position > 0) {
                    id = courseDetails.get(position - 1).getId();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Button dialogSubmit = dialog.findViewById(R.id.btn_submit);
        dialogSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data.add(new EducationModel(id + "", eduSpinner.getSelectedItem().toString(),
                        edtRoll.getText().toString(),
                        edtMarks.getText().toString(),
                        edtTotalMarks.getText().toString()));
                adapter.notifyDataSetChanged();
                courseList.clear();
                courseDetails.clear();
                dialog.dismiss();
            }
        });
        eduSpinner.setAdapter(courseAdapter);

        dialog.show();
    }

    private void getCourseList() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(LoginInterface.BASE_URL_HOME).addConverterFactory(GsonConverterFactory.create()).build();
        LoginInterface loginInterface = retrofit.create(LoginInterface.class);
        Call<EducationDetail> response = loginInterface.getCourseList();
        response.enqueue(new Callback<EducationDetail>() {
            @Override
            public void onResponse(Call<EducationDetail> call, Response<EducationDetail> response) {
                if (response.body() != null) {
                    if (response.body().getStatus()) {
                        EducationDetail edu = response.body();
                        courseDetails.addAll(edu.getData());
                        courseList.add("Select Course");
                        for (int i = 0; i < edu.getData().size(); i++) {
                            courseList.add(edu.getData().get(i).getTitle());
                            courseAdapter.notifyDataSetChanged();
                        }


                    } else {
                        Toast.makeText(StudentForm.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                } else
                    Toast.makeText(StudentForm.this, "Course List is empty", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<EducationDetail> call, Throwable t) {

            }
        });

    }

    private void submitFormDetails() {
        Toast.makeText(this, "hi", Toast.LENGTH_SHORT).show();
        HashMap<String, String> param = new HashMap<>();
        param.put("user_id", "23");
        param.put("name", st_name.getText().toString());
        param.put("email", email.getText().toString());
        param.put("stream", stream.getText().toString());
        param.put("batch", batch.getText().toString());
        param.put("password", password.getText().toString());
        param.put("skills", skill.getText().toString());
        param.put("biometric", "jijijijiiji");
        param.put("mobile", mobile.getText().toString());
        param.put("qualification", new Gson().toJson(data));
        Retrofit retrofit = new Retrofit.Builder().baseUrl(LoginInterface.BASE_URL_HOME).addConverterFactory(GsonConverterFactory.create()).build();
        LoginInterface loginInterface = retrofit.create(LoginInterface.class);
        Log.e("data", param.toString());
        Call<StudentFormResponse> response = loginInterface.submitStu(param);
        response.enqueue(new Callback<StudentFormResponse>() {
            @Override
            public void onResponse(Call<StudentFormResponse> call, Response<StudentFormResponse> response) {
                progressDialog.dismiss();
                if (response.body() != null) {
                    if (response.body().getStatus()) {
                        Toast.makeText(StudentForm.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(StudentForm.this,ListCandidateActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }
            }

            @Override
            public void onFailure(Call<StudentFormResponse> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(StudentForm.this, "" + t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });

    }

}
