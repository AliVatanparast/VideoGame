package vgame.ir.data.remote.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import vgame.ir.data.model.Attachment;

public class BannerResponse {
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
        @SerializedName("sliders")
        @Expose
        private List<Slider> sliders = null;

        public List<Slider> getSliders() {
            return sliders;
        }

        public void setSliders(List<Slider> sliders) {
            this.sliders = sliders;
        }
    }

    public class Slider {
        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("text")
        @Expose
        private String text;
        @SerializedName("link_type")
        @Expose
        private Integer linkType;
        @SerializedName("link")
        @Expose
        private String link;
        @SerializedName("priority")
        @Expose
        private Integer priority;
        @SerializedName("attachments")
        @Expose
        private List<Attachment> attachments = null;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public Integer getLinkType() {
            return linkType;
        }

        public void setLinkType(Integer linkType) {
            this.linkType = linkType;
        }

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }

        public Integer getPriority() {
            return priority;
        }

        public void setPriority(Integer priority) {
            this.priority = priority;
        }

        public List<Attachment> getAttachments() {
            return attachments;
        }

        public void setAttachments(List<Attachment> attachments) {
            this.attachments = attachments;
        }
    }
}
