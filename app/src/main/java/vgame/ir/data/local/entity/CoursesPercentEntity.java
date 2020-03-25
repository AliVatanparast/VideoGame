package vgame.ir.data.local.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity(tableName = "courses_percent")
public class CoursesPercentEntity {

    @SerializedName("id")
    private int id;

    @PrimaryKey
    @SerializedName("course_id")
    private int course_id;

    @SerializedName("url")
    private String url;

    @SerializedName("name")
    private String name;

    @SerializedName("total_lessons")
    private int total_lessons;

    @SerializedName("passed_lessons")
    private String passed_lessons;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCourse_id() {
        return course_id;
    }

    public void setCourse_id(int course_id) {
        this.course_id = course_id;
    }

    public int getTotal_lessons() {
        return total_lessons;
    }

    public void setTotal_lessons(int total_lessons) {
        this.total_lessons = total_lessons;
    }

    public String getPassed_lessons() {
        return passed_lessons;
    }

    public void setPassed_lessons(String passed_lessons) {
        this.passed_lessons = passed_lessons;
    }
}