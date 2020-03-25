package vgame.ir.data.local.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity(tableName = "lesson")
public class LessonEntity {

    @PrimaryKey
    @SerializedName("id")
    private int id;

    @SerializedName("title")
    private String title;

    @SerializedName("description")
    private String description;

    @SerializedName("voise_url")
    private String voise_url;

    @SerializedName("voice_comment")
    private String voice_comment;

    @SerializedName("word_url")
    private String word_url;

    @SerializedName("course_chapter_id")
    private int course_chapter_id;

    @SerializedName("chapter_name")
    private String chapter_name;

    public String getChapter_name() {
        return chapter_name;
    }

    public void setChapter_name(String chapter_name) {
        this.chapter_name = chapter_name;
    }

    public String getVoise_url() {
        return voise_url;
    }

    public void setVoise_url(String voise_url) {
        this.voise_url = voise_url;
    }

    public String getVoice_comment() {
        return voice_comment;
    }

    public void setVoice_comment(String voice_comment) {
        this.voice_comment = voice_comment;
    }

    public String getWord_url() {
        return word_url;
    }

    public void setWord_url(String word_url) {
        this.word_url = word_url;
    }

    public int getCourse_chapter_id() {
        return course_chapter_id;
    }

    public void setCourse_chapter_id(int course_chapter_id) {
        this.course_chapter_id = course_chapter_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
