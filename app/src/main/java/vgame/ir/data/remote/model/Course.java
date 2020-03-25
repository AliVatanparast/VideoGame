package vgame.ir.data.remote.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Objects;

import vgame.ir.data.model.Attachment;

public class Course {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("price")
    @Expose
    private String price;
    @SerializedName("teacher_id")
    @Expose
    private Integer teacherId;
    @SerializedName("category_id")
    @Expose
    private Integer categoryId;
    @SerializedName("is_active")
    @Expose
    private Integer isActive;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("attachments")
    @Expose
    private List<Attachment> attachments = null;
    @SerializedName("category")
    @Expose
    private CoursesResponse.Category category;
    @SerializedName("teacher")
    @Expose
    private CoursesResponse.Teacher teacher;

    @SerializedName("pivot")
    @Expose
    private Pivot pivot;

    @SerializedName("members_count")
    @Expose
    private Integer members_count;

    private String hight_light;

    public String getHight_light() {
        return hight_light;
    }

    public void setHight_light(String hight_light) {
        this.hight_light = hight_light;
    }

    public Integer getMembers_count() {
        return members_count;
    }

    public void setMembers_count(Integer members_count) {
        this.members_count = members_count;
    }

    public Pivot getPivot() {
        return pivot;
    }

    public void setPivot(Pivot pivot) {
        this.pivot = pivot;
    }

    public class Pivot {

        @SerializedName("user_id")
        @Expose
        private Integer userId;
        @SerializedName("course_id")
        @Expose
        private Integer courseId;
        @SerializedName("is_active")
        @Expose
        private Integer isActive;

        public Integer getUserId() {
            return userId;
        }

        public void setUserId(Integer userId) {
            this.userId = userId;
        }

        public Integer getCourseId() {
            return courseId;
        }

        public void setCourseId(Integer courseId) {
            this.courseId = courseId;
        }

        public Integer getIsActive() {
            return isActive;
        }

        public void setIsActive(Integer isActive) {
            this.isActive = isActive;
        }

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return Objects.equals(id, course.id) &&
                Objects.equals(title, course.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getIsActive() {
        return isActive;
    }

    public void setIsActive(Integer isActive) {
        this.isActive = isActive;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public List<Attachment> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<Attachment> attachments) {
        this.attachments = attachments;
    }

    public CoursesResponse.Category getCategory() {
        return category;
    }

    public void setCategory(CoursesResponse.Category category) {
        this.category = category;
    }

    public CoursesResponse.Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(CoursesResponse.Teacher teacher) {
        this.teacher = teacher;
    }
}
