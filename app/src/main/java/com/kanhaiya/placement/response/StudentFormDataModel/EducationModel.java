package com.kanhaiya.placement.response.StudentFormDataModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Awesome Pojo Generator
 */
public class EducationModel {

    @SerializedName("max_marks")
    @Expose
    private String max_marks;
    @SerializedName("education_id")
    @Expose
    private String education_id;

    public EducationModel(String education_id,  String education, String roll_no, String marks, String max_marks) {
        this.max_marks = max_marks;
        this.education_id = education_id;
        this.education = education;
        this.roll_no = roll_no;
        this.marks = marks;
    }

    @SerializedName("education")
    @Expose
    private String education;
    @SerializedName("roll_no")
    @Expose
    private String roll_no;
    @SerializedName("marks")
    @Expose
    private String marks;

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public void setMax_marks(String max_marks) {
        this.max_marks = max_marks;
    }

    public String getMax_marks() {
        return max_marks;
    }

    public void setEducation_id(String education_id) {
        this.education_id = education_id;
    }

    public String getEducation_id() {
        return education_id;
    }

    public void setRoll_no(String roll_no) {
        this.roll_no = roll_no;
    }

    public String getRoll_no() {
        return roll_no;
    }

    public void setMarks(String marks) {
        this.marks = marks;
    }

    public String getMarks() {
        return marks;
    }
}