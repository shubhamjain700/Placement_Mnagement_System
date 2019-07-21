package com.kanhaiya.placement.response.GetHomeUserResponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Awesome Pojo Generator
 */
public class Data {
    @SerializedName("education")
    @Expose
    private List<Education> education;
    @SerializedName("user_data")
    @Expose
    private User_data user_data;
    @SerializedName("recommended")
    @Expose
    private List<Recommended> recommended;

    public void setEducation(List<Education> education) {
        this.education = education;
    }

    public List<Education> getEducation() {
        return education;
    }

    public void setUser_data(User_data user_data) {
        this.user_data = user_data;
    }

    public User_data getUser_data() {
        return user_data;
    }

    public void setRecommended(List<Recommended> recommended) {
        this.recommended = recommended;
    }

    public List<Recommended> getRecommended() {
        return recommended;
    }
}