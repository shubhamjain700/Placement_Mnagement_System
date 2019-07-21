package com.kanhaiya.placement.response.StuList;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Awesome Pojo Generator
 * */
public class Data{
  @SerializedName("attendance_id")
  @Expose
  private Integer attendance_id;
  @SerializedName("graduation")
  @Expose
  private String graduation;
  @SerializedName("roll_no")
  @Expose
  private String roll_no;
  @SerializedName("name")
  @Expose
  private String name;
  @SerializedName("id")
  @Expose
  private Integer id;
  @SerializedName("is_attendance")
  @Expose
  private Integer is_attendance;
  public void setAttendance_id(Integer attendance_id){
   this.attendance_id=attendance_id;
  }
  public Integer getAttendance_id(){
   return attendance_id;
  }
  public void setGraduation(String graduation){
   this.graduation=graduation;
  }
  public String getGraduation(){
   return graduation;
  }
  public void setRoll_no(String roll_no){
   this.roll_no=roll_no;
  }
  public String getRoll_no(){
   return roll_no;
  }
  public void setName(String name){
   this.name=name;
  }
  public String getName(){
   return name;
  }
  public void setId(Integer id){
   this.id=id;
  }
  public Integer getId(){
   return id;
  }
  public void setIs_attendance(Integer is_attendance){
   this.is_attendance=is_attendance;
  }
  public Integer getIs_attendance(){
   return is_attendance;
  }
}