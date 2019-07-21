package com.kanhaiya.placement.response.forget_password_model;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Awesome Pojo Generator
 * */
public class forget_password_response{
  @SerializedName("data")
  @Expose
  private Data data;
  @SerializedName("message")
  @Expose
  private String message;
  @SerializedName("status")
  @Expose
  private Boolean status;
  public void setData(Data data){
   this.data=data;
  }
  public Data getData(){
   return data;
  }
  public void setMessage(String message){
   this.message=message;
  }
  public String getMessage(){
   return message;
  }
  public void setStatus(Boolean status){
   this.status=status;
  }
  public Boolean getStatus(){
   return status;
  }
}