package com.kanhaiya.placement.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Awesome Pojo Generator
 */
public class Data {
    @SerializedName("appeared_in")
    @Expose
    private Integer appeared_in;
    @SerializedName("created")
    @Expose
    private Integer created;
    @SerializedName("mobile")
    @Expose
    private Long mobile;
    @SerializedName("batch")
    @Expose
    private String batch;
    @SerializedName("c_code")
    @Expose
    private String c_code;
    @SerializedName("skills")
    @Expose
    private String skills;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("user_type")
    @Expose
    private Integer user_type;
    @SerializedName("stream")
    @Expose
    private String stream;
    @SerializedName("device_token")
    @Expose
    private String device_token;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("biometric")
    @Expose
    private String biometric;
    @SerializedName("status")
    @Expose
    private Integer status;

    public void setAppeared_in(Integer appeared_in) {
        this.appeared_in = appeared_in;
    }

    public Integer getAppeared_in() {
        return appeared_in;
    }

    public void setCreated(Integer created) {
        this.created = created;
    }

    public Integer getCreated() {
        return created;
    }

    public void setMobile(Long mobile) {
        this.mobile = mobile;
    }

    public Long getMobile() {
        return mobile;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

    public String getBatch() {
        return batch;
    }

    public void setC_code(String c_code) {
        this.c_code = c_code;
    }

    public String getC_code() {
        return c_code;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    public String getSkills() {
        return skills;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setUser_type(Integer user_type) {
        this.user_type = user_type;
    }

    public Integer getUser_type() {
        return user_type;
    }

    public void setStream(String stream) {
        this.stream = stream;
    }

    public String getStream() {
        return stream;
    }

    public void setDevice_token(String device_token) {
        this.device_token = device_token;
    }

    public String getDevice_token() {
        return device_token;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setBiometric(String biometric) {
        this.biometric = biometric;
    }

    public String getBiometric() {
        return biometric;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getStatus() {
        return status;
    }
}