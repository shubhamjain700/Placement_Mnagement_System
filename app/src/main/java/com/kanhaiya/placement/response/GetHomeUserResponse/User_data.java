package com.kanhaiya.placement.response.GetHomeUserResponse;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Awesome Pojo Generator
 * */
public class User_data{
  @SerializedName("c_code")
  @Expose
  private String c_code;
  @SerializedName("skills")
  @Expose
  private String skills;
  @SerializedName("appeared_in")
  @Expose
  private Integer appeared_in;
  @SerializedName("stream")
  @Expose
  private String stream;
  @SerializedName("name")
  @Expose
  private String name;
  @SerializedName("mobile")
  @Expose
  private Integer mobile;
  @SerializedName("batch")
  @Expose
  private String batch;
  @SerializedName("id")
  @Expose
  private Integer id;
  @SerializedName("email")
  @Expose
  private String email;
  @SerializedName("status")
  @Expose
  private Integer status;
  public void setC_code(String c_code){
   this.c_code=c_code;
  }
  public String getC_code(){
   return c_code;
  }
  public void setSkills(String skills){
   this.skills=skills;
  }
  public String getSkills(){
   return skills;
  }
  public void setAppeared_in(Integer appeared_in){
   this.appeared_in=appeared_in;
  }
  public Integer getAppeared_in(){
   return appeared_in;
  }
  public void setStream(String stream){
   this.stream=stream;
  }
  public String getStream(){
   return stream;
  }
  public void setName(String name){
   this.name=name;
  }
  public String getName(){
   return name;
  }
  public void setMobile(Integer mobile){
   this.mobile=mobile;
  }
  public Integer getMobile(){
   return mobile;
  }
  public void setBatch(String batch){
   this.batch=batch;
  }
  public String getBatch(){
   return batch;
  }
  public void setId(Integer id){
   this.id=id;
  }
  public Integer getId(){
   return id;
  }
  public void setEmail(String email){
   this.email=email;
  }
  public String getEmail(){
   return email;
  }
  public void setStatus(Integer status){
   this.status=status;
  }
  public Integer getStatus(){
   return status;
  }
}