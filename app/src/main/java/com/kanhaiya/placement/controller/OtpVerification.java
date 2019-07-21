package com.kanhaiya.placement.controller;

import android.app.ProgressDialog;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.kanhaiya.placement.R;
import com.kanhaiya.placement.databinding.ActivityOtpVerificationBinding;

public class OtpVerification extends AppCompatActivity {

    ActivityOtpVerificationBinding binding;
    private ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_otp_verification);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please wait...");
        progressDialog.setCancelable(false);
        binding.showEmailId.setText("Enter the OTP we just sent you on " + getIntent().getStringExtra("email") + " to verify the account");

        binding.resendOtp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog.show();
                Toast.makeText(OtpVerification.this, getIntent().getStringExtra("otp"), Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        });
        binding.otpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog.show();
                String otp1 = getIntent().getStringExtra("otp");
                binding.resendOtp.setText(otp1);
                if (otp1.equals(binding.enterOtp.getText().toString())) {
                    Intent intent = new Intent(OtpVerification.this, ChangePassword.class);
                    intent.putExtra("type", getIntent().getStringExtra("type"));
                    intent.putExtra("email", getIntent().getStringExtra("email"));
                    intent.putExtra("otp", getIntent().getStringExtra("otp"));
                    progressDialog.dismiss();
                    startActivity(intent);
                    finish();
                } else {
                    progressDialog.dismiss();
                    Toast.makeText(OtpVerification.this, "Invalid OTP", Toast.LENGTH_SHORT);
                }
            }
        });

    }
}
