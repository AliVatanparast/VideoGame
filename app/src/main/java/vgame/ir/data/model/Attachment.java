package vgame.ir.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Attachment {

    @SerializedName("size_kb")
    @Expose
    private String sizeKb;
    @SerializedName("file_type")
    @Expose
    private String fileType;
    @SerializedName("absolute_url")
    @Expose
    private String absoluteUrl;

    public String getSizeKb() {
        return sizeKb;
    }

    public void setSizeKb(String sizeKb) {
        this.sizeKb = sizeKb;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getAbsoluteUrl() {
        return absoluteUrl;
    }

    public void setAbsoluteUrl(String absoluteUrl) {
        this.absoluteUrl = absoluteUrl;
    }
}
