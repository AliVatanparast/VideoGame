package vgame.ir.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import vgame.ir.data.remote.model.CertificateModel;
import vgame.ir.data.remote.model.Course;

public class User {
    @SerializedName("name")
    private String name;

    @SerializedName("avatar")
    private String avatar;

    @SerializedName("mobile")
    private String mobile;

    @SerializedName("id")
    private Integer id;

    @SerializedName("courses")
    @Expose
    private List<Course> courses = null;

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    @SerializedName("user_certificates")
    @Expose
    private List<CertificateModel> userCertificates = null;

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<CertificateModel> getUserCertificates() {
        return userCertificates;
    }

    public void setUserCertificates(List<CertificateModel> userCertificates) {
        this.userCertificates = userCertificates;
    }
}
