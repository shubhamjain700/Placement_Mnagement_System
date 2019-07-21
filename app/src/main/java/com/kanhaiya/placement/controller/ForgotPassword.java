package com.kanhaiya.placement.controller;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.kanhaiya.placement.R;
import com.kanhaiya.placement.model.LoginInterface;
import com.kanhaiya.placement.response.forget_password_model.forget_password_response;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ForgotPassword extends Activity {
    private Button resetpasswordbtn;
    private EditText email;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        resetpasswordbtn = findViewById(R.id.rst_pass);
        email = findViewById(R.id.EnterEmail);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please wait...");
        progressDialog.setCancelable(false);

        resetpasswordbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog.show();
                String e_mail = email.getText().toString().trim();
                if (!e_mail.isEmpty()) {
                    email_validation(e_mail);
                } else {
                    Toast.makeText(ForgotPassword.this, "Please Enter Email-Id", Toast.LENGTH_SHORT).show();
                }


            }
        });
    }

    public void email_validation(String e_mail) {
        if (!e_mail.contains("@") || !e_mail.contains(".")) {
            progressDialog.dismiss();
            Toast.makeText(ForgotPassword.this, "Please Enter valid Email-id", Toast.LENGTH_SHORT).show();
        } else if (e_mail.length() < 10) {
            progressDialog.dismiss();
            Toast.makeText(ForgotPassword.this, "Please Enter valid Email-id", Toast.LENGTH_SHORT).show();
        } else {
            forgetPassword(e_mail);
        }
    }

    private void forgetPassword(String e_mail) {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(LoginInterface.BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        LoginInterface apiCall = retrofit.create(LoginInterface.class);
        Call<forget_password_response> responseCall = apiCall.getFpData(e_mail);
        responseCall.enqueue(new Callback<forget_password_response>() {
            @Override
            public void onResponse(Call<forget_password_response> call, Response<forget_password_response> response) {
                if (response.body() != null) {
                    if (response.body().getStatus()) {
                        progressDialog.dismiss();
                        Intent intent = new Intent(ForgotPassword.this, OtpVerification.class);
                        intent.putExtra("otp", response.body().getData().getOtp().toString());
                        intent.putExtra("email", email.getText().toString());
                        intent.putExtra("type", "1");
                        Toast.makeText(ForgotPassword.this, response.body().getData().getOtp().toString(), Toast
                                .LENGTH_SHORT).show();
                        startActivity(intent);
                        finish();
                    } else {
                        progressDialog.dismiss();
                        Toast.makeText(ForgotPassword.this, "Enter Valid Email-Id", Toast.LENGTH_SHORT).show();
                    }


                } else {
                    progressDialog.dismiss();
                    Toast.makeText(ForgotPassword.this, "Body is null", Toast.LENGTH_SHORT).show();
                }
            }


            @Override
            public void onFailure(Call<forget_password_response> call, Throwable t) {
                Log.e("Error", "onFailure of Forget password Api: " + t.getMessage());
                Toast.makeText(ForgotPassword.this, "API Call failed", Toast.LENGTH_SHORT).show();

            }
        });
    }
}
