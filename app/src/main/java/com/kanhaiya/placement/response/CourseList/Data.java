package com.kanhaiya.placement.response.CourseList;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Awesome Pojo Generator
 * */
public class Data{
  @SerializedName("id")
  @Expose
  private Integer id;
  @SerializedName("title")
  @Expose
  private String title;
  public void setId(Integer id){
   this.id=id;
  }
  public Integer getId(){
   return id;
  }
  public void setTitle(String title){
   this.title=title;
  }
  public String getTitle(){
   return title;
  }
}