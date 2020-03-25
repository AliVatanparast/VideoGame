package vgame.ir.data.local.entity;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.util.List;

@Entity(tableName = "univerciry")
public class UnivercityEntity {

    @PrimaryKey
    @SerializedName("id")
    private int id;

    @SerializedName("url")
    private String url;

    @SerializedName("name")
    private String name;

    @Ignore
    @SerializedName("colleges")
    private List<CollegeEntity> college;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String name) {
        this.url = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<CollegeEntity> getColleges() {
        return college;
    }

    public void setColleges(List<CollegeEntity> colleges) {
        this.college = colleges;
    }
}