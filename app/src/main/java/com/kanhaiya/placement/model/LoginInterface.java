package com.kanhaiya.placement.model;

import com.google.gson.JsonObject;
import com.kanhaiya.placement.model.StudentForm.StudentFormResponse;
import com.kanhaiya.placement.response.CourseList.EducationDetail;
import com.kanhaiya.placement.response.GetHomeUserResponse.GetHomeUser;
import com.kanhaiya.placement.response.StuList.StudentList;
import com.kanhaiya.placement.response.company_list.CompanyList;
import com.kanhaiya.placement.response.forget_password2_model.forget_password2;
import com.kanhaiya.placement.response.forget_password_model.forget_password_response;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface LoginInterface {
    String BASE_URL = "http://192.168.43.224/placement/index.php/api/users/";
    String BASE_URL_HOME = "http://192.168.43.224/placement/index.php/api/home/";


    @FormUrlEncoded
    @POST("login")
    Call<LogInResponse> getData(
            @Field("username") String userName,
            @Field("password") String password,
            @Field("device_token") String device_token);

    @FormUrlEncoded
    @POST("change_password")
    Call<JsonObject> getdata(
            @Field("user_id") String user_id,
            @Field("password") String pwd
    );

    @FormUrlEncoded
    @POST("forget_password")
    Call<forget_password_response> getFpData(
            @Field("username") String username
    );

    @FormUrlEncoded
    @POST("forget_password")
    Call<forget_password2> getFpData(
            @Field("username") String username,
            @Field("otp") String otp,
            @Field("password") String password
    );

    @FormUrlEncoded
    @POST("get_company_list")
    Call<CompanyList> getCompany(@FieldMap HashMap<String, String> username);

    @FormUrlEncoded
    @POST("get_student_list")
    Call<StudentList> getStudent(@FieldMap HashMap<String, String> username);

    @FormUrlEncoded
    @POST("get_home_user")
    Call<GetHomeUser> getHomeUser(
            @Field("user_id") String userId
    );

    @GET("get_qualification")
    Call<EducationDetail> getCourseList();

    @FormUrlEncoded
    @POST("register_student")
    Call<StudentFormResponse> submitStu
            (@FieldMap HashMap<String, String> param

            );

}
