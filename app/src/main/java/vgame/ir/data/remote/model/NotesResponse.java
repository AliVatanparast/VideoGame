package vgame.ir.data.remote.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NotesResponse {
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

    public class Data {

        @SerializedName("note")
        @Expose
        private Note note;

        public Note getNote() {
            return note;
        }

        public void setNote(Note note) {
            this.note = note;
        }

    }

    public class Note {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("body")
        @Expose
        private String body;
        @SerializedName("on_time")
        @Expose
        private Object onTime;
        @SerializedName("user_id")
        @Expose
        private Integer userId;
        @SerializedName("model")
        @Expose
        private String model;
        @SerializedName("model_id")
        @Expose
        private Integer modelId;
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

        public Object getOnTime() {
            return onTime;
        }

        public void setOnTime(Object onTime) {
            this.onTime = onTime;
        }

        public Integer getUserId() {
            return userId;
        }

        public void setUserId(Integer userId) {
            this.userId = userId;
        }

        public String getModel() {
            return model;
        }

        public void setModel(String model) {
            this.model = model;
        }

        public Integer getModelId() {
            return modelId;
        }

        public void setModelId(Integer modelId) {
            this.modelId = modelId;
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

    public void setData(Data data) {
        this.data = data;
    }
}
