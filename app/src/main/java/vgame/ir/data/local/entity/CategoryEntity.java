package vgame.ir.data.local.entity;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.util.List;

@Entity(tableName = "category")
public class CategoryEntity {

    @PrimaryKey
    @SerializedName("id")
    private int id;

    @SerializedName("college_id")
    private int college_id;

    @SerializedName("name")
    private String name;

    @Ignore
    @SerializedName("courses")
    private List<CourseEntity> courses;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCollege_id() {
        return college_id;
    }

    public void setCollege_id(int uni_id) {
        this.college_id = uni_id;
    }

    public List<CourseEntity> getCourses() {
        return courses;
    }

    public void setCourses(List<CourseEntity> courses) {
        this.courses = courses;
    }
}