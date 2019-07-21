package com.kanhaiya.placement.controller;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.kanhaiya.placement.R;
import com.kanhaiya.placement.model.LogInResponse;
import com.kanhaiya.placement.model.LoginInterface;
import com.kanhaiya.placement.model.SharedPrefManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class SignInActivity extends AppCompatActivity {
    private TextView forgotPassword;
    private ProgressDialog progressDialog;
    private EditText loginId, password;
    private Button signIn;
    private SharedPrefManager sharedPrefManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        forgotPassword = findViewById(R.id.forget_pass);
        loginId = findViewById(R.id.email_mobileNo);
        password = findViewById(R.id.password);
        signIn = findViewById(R.id.sign_in_btn);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please wait...");
        progressDialog.setCancelable(false);
        sharedPrefManager = new SharedPrefManager(this);


        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(SignInActivity.this, ForgotPassword.class);
                startActivity(intent);
            }
        });


        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog.show();
                String logIn = loginId.getText().toString().trim();
                String pass = password.getText().toString().trim();
                validation(logIn, pass);

            }
        });

    }

    private void validation(String logInId, String pass) {
        if (logInId.length() < 4 || pass.length() < 4) {
            Toast.makeText(SignInActivity.this, "Enter Valid Id and Password", Toast.LENGTH_SHORT).show();
            progressDialog.dismiss();

        } else {
            Toast.makeText(SignInActivity.this, "Please Wait", Toast.LENGTH_SHORT).show();
            login(logInId, pass);


        }
    }

    public void changePasswordLogin(String emailId, String password) {

        login(emailId, password);
    }

    private void login(String logInId, String pass) {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(LoginInterface.BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        LoginInterface api = retrofit.create(LoginInterface.class);
        Call<LogInResponse> response = api.getData(logInId, pass, "device_token");
        response.enqueue(new Callback<LogInResponse>() {
            @Override
            public void onResponse(Call<LogInResponse> call, Response<LogInResponse> response) {
                if (response.body() != null) {
                    LogInResponse data = response.body();
                    if (data.getStatus()) {
                       sharedPrefManager.saveData("logInResponse", response.body().getData());
                        progressDialog.dismiss();
                        if (data.getData().getUser_type() == 2) {
                            Intent intent = new Intent(SignInActivity.this, ListCandidateActivity.class);
                            intent.putExtra("id", data.getData().getId().toString());
                            startActivity(intent);
                        } else if (data.getData().getUser_type() == 3) {
                            Intent intent = new Intent(SignInActivity.this, UserScreen.class);
                            intent.putExtra("userId", data.getData().getId().toString());
                            startActivity(intent);
                        } else {
                            Toast.makeText(SignInActivity.this, "User Type does not exist", Toast.LENGTH_SHORT).show();
                        }
                        finish();
                    } else {
                        Toast.makeText(SignInActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                    }

                } else {
                    Toast.makeText(SignInActivity.this, "Empty body", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }
            }

            @Override
            public void onFailure(Call<LogInResponse> call, Throwable t) {
                Log.e("Error", "onFailure: " + t.getMessage());
                Toast.makeText(SignInActivity.this, "API Call Failed", Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();

            }
        });


    }
}

