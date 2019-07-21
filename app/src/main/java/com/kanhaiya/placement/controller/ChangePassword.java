package com.kanhaiya.placement.controller;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.kanhaiya.placement.R;
import com.kanhaiya.placement.model.LoginInterface;
import com.kanhaiya.placement.model.SharedPrefManager;
import com.kanhaiya.placement.response.forget_password2_model.forget_password2;

import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ChangePassword extends AppCompatActivity {

    private Button changePassword;
    private EditText enterpassword;
    private EditText confirmPassword;
    private SharedPrefManager sharedPrefManager;
    private ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        changePassword = findViewById(R.id.submit_btn);
        enterpassword = findViewById(R.id.enterPassword);
        confirmPassword = findViewById(R.id.confirmPassword);
        sharedPrefManager = new SharedPrefManager(this);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please wait...");
        progressDialog.setCancelable(false);
        changePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog.show();

                password_validation(enterpassword.getText().toString(), confirmPassword.getText().toString());

            }
        });
    }

    private void password_validation(String password, String confirmPassword) {
        if (password.equals(confirmPassword)) {
            if (password.length() < 8 && confirmPassword.length() < 8) {
                progressDialog.dismiss();
                Toast.makeText(ChangePassword.this, "Password must be greater then 8 character", Toast.LENGTH_SHORT).show();
            } else {
                if (getIntent().getStringExtra("type").equals("1")) {
                    forget_change_password(password);
                }
                else if(getIntent().getStringExtra("type").equals("2")){
                    change_pwd(getIntent().getStringExtra("id"),password);
                }
            }
        } else {
            progressDialog.dismiss();
            Toast.makeText(ChangePassword.this, "Reenter the confirm Password", Toast.LENGTH_SHORT).show();
        }
    }

    private void forget_change_password(final String password) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(LoginInterface.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        LoginInterface API = retrofit.create(LoginInterface.class);
        Call<forget_password2> call = API.getFpData(getIntent().getStringExtra("email"),
                getIntent().getStringExtra("otp"),
                password);
        call.enqueue(new Callback<forget_password2>() {
            @Override
            public void onResponse(Call<forget_password2> call, Response<forget_password2> response) {

                progressDialog.dismiss();
                if (response.body() != null) {
                    if (response.body().getStatus()) {
                        Toast.makeText(ChangePassword.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        Log.e("Response", "onResponse: " + response.body().toString());
                        Intent intent=new Intent(ChangePassword.this,SignInActivity.class);
                        Toast.makeText(ChangePassword.this,"Login with new Password",Toast.LENGTH_SHORT).show();

                        //Bug here invoking null object method
//                        new SignInActivity().changePasswordLogin(response.body().getData().getEmail(), password);
                        startActivity(intent);
                        finish();
                    }
                }
            }

            @Override
            public void onFailure(Call<forget_password2> call, Throwable t) {
                progressDialog.dismiss();

            }
        });

    }

    private void change_pwd(String user_id, String pwd) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(LoginInterface.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        LoginInterface API = retrofit.create(LoginInterface.class);
        Call<JsonObject> call = API.getdata(user_id, pwd);
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                progressDialog.dismiss();
                if (response.body() != null) {
                    JsonObject jsonObject = response.body();
                    JSONObject jsonObject1 = null;
                    try {
                        jsonObject1 = new JSONObject(jsonObject.toString());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    Toast.makeText(ChangePassword.this, jsonObject1.optString("message"), Toast.LENGTH_SHORT).show();

                }

            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Log.d("Error", t.getMessage());
                progressDialog.dismiss();
            }
        });
    }

}
