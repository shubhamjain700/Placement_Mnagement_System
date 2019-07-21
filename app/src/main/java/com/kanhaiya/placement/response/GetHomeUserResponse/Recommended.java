package com.kanhaiya.placement.response.GetHomeUserResponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Awesome Pojo Generator
 */
public class Recommended {
    @SerializedName("is_enrolled")
    @Expose
    private Integer is_enrolled;
    @SerializedName("package")
    @Expose
    private Integer package_;
    @SerializedName("drive_date")
    @Expose
    private Integer drive_date;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("location")
    @Expose
    private String location;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("designation")
    @Expose
    private String designation;

    public void setIs_enrolled(Integer is_enrolled) {
        this.is_enrolled = is_enrolled;
    }

    public Integer getIs_enrolled() {
        return is_enrolled;
    }

    public void setPackage(Integer package_) {
        this.package_ = package_;
    }

    public Integer getPackage() {
        return package_;
    }

    public void setDrive_date(Integer drive_date) {
        this.drive_date = drive_date;
    }

    public Integer getDrive_date() {
        return drive_date;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLocation() {
        return location;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getDesignation() {
        return designation;
    }
}