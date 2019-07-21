package com.kanhaiya.placement.model.StudentForm;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Awesome Pojo Generator
 * */
public class StudentFormResponse{
  @SerializedName("message")
  @Expose
  private String message;
  @SerializedName("status")
  @Expose
  private Boolean status;
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