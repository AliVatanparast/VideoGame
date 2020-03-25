package vgame.ir.data.remote.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import vgame.ir.data.local.entity.ChapterEntity;
import vgame.ir.data.model.Attachment;

public class LessonDetailResponse {
    @SerializedName("error")
    @Expose
    private Boolean error;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private Data data;

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public class Data {

        @SerializedName("session")
        @Expose
        private Session session;

        public Session getSession() {
            return session;
        }

        public void setSession(Session session) {
            this.session = session;
        }

    }

    public class Session {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("description")
        @Expose
        private String description;
        @SerializedName("course_chapter_id")
        @Expose
        private Integer courseChapterId;
        @SerializedName("created_at")
        @Expose
        private String createdAt;
        @SerializedName("updated_at")
        @Expose
        private String updatedAt;
        @SerializedName("attachments")
        @Expose
        private List<Attachment> attachments = null;
        @SerializedName("voice_comment")
        @Expose
        private VoiceComment voiceComment;
        @SerializedName("title")
        @Expose
        private String title;

        @SerializedName("chapter")
        @Expose
        private ChapterEntity chapter;
        private String course_image;

        public String getCourse_image() {
            return course_image;
        }

        public void setCourse_image(String course_image) {
            this.course_image = course_image;
        }

        public ChapterEntity getChapter() {
            return chapter;
        }

        public void setChapter(ChapterEntity chapter) {
            this.chapter = chapter;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        private String hight_light;

        public String getHight_light() {
            return hight_light;
        }

        public void setHight_light(String hight_light) {
            this.hight_light = hight_light;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public Integer getCourseChapterId() {
            return courseChapterId;
        }

        public void setCourseChapterId(Integer courseChapterId) {
            this.courseChapterId = courseChapterId;
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

        public VoiceComment getVoiceComment() {
            return voiceComment;
        }

        public void setVoiceComment(VoiceComment voiceComment) {
            this.voiceComment = voiceComment;
        }

    }

    public class VoiceComment {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("body")
        @Expose
        private String body;
        @SerializedName("attachment_id")
        @Expose
        private Integer attachmentId;
        @SerializedName("course_chapter_session_id")
        @Expose
        private Integer courseChapterSessionId;
        @SerializedName("at_time")
        @Expose
        private String atTime;
        @SerializedName("created_at")
        @Expose
        private String createdAt;
        @SerializedName("updated_at")
        @Expose
        private String updatedAt;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getBody() {
            return body;
        }

        public void setBody(String body) {
            this.body = body;
        }

        public Integer getAttachmentId() {
            return attachmentId;
        }

        public void setAttachmentId(Integer attachmentId) {
            this.attachmentId = attachmentId;
        }

        public Integer getCourseChapterSessionId() {
            return courseChapterSessionId;
        }

        public void setCourseChapterSessionId(Integer courseChapterSessionId) {
            this.courseChapterSessionId = courseChapterSessionId;
        }

        public String getAtTime() {
            return atTime;
        }

        public void setAtTime(String atTime) {
            this.atTime = atTime;
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

    }
}
