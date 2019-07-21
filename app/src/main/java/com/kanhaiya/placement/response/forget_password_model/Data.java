package com.kanhaiya.placement.response.forget_password_model;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Awesome Pojo Generator
 * */
public class Data{
  @SerializedName("otp")
  @Expose
  private Integer otp;
  public void setOtp(Integer otp){
   this.otp=otp;
  }
  public Integer getOtp(){
   return otp;
  }
}