package com.kanhaiya.placement.response.GetHomeUserResponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Awesome Pojo Generator
 */
public class Education {
    @SerializedName("max_marks")
    @Expose
    private Integer max_marks;
    @SerializedName("edu_title")
    @Expose
    private String edu_title;
    @SerializedName("user_id")
    @Expose
    private Integer user_id;
    @SerializedName("created")
    @Expose
    private Integer created;
    @SerializedName("education_id")
    @Expose
    private Integer education_id;
    @SerializedName("roll_no")
    @Expose
    private Integer roll_no;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("marks")
    @Expose
    private Integer marks;
    @SerializedName("status")
    @Expose
    private Integer status;

    public void setMax_marks(Integer max_marks) {
        this.max_marks = max_marks;
    }

    public Integer getMax_marks() {
        return max_marks;
    }

    public void setEdu_title(String edu_title) {
        this.edu_title = edu_title;
    }

    public String getEdu_title() {
        return edu_title;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setCreated(Integer created) {
        this.created = created;
    }

    public Integer getCreated() {
        return created;
    }

    public void setEducation_id(Integer education_id) {
        this.education_id = education_id;
    }

    public Integer getEducation_id() {
        return education_id;
    }

    public void setRoll_no(Integer roll_no) {
        this.roll_no = roll_no;
    }

    public Integer getRoll_no() {
        return roll_no;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setMarks(Integer marks) {
        this.marks = marks;
    }

    public Integer getMarks() {
        return marks;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getStatus() {
        return status;
    }
}