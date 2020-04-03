package vgame.ir.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Attachment {

    @SerializedName("size_kb")
    @Expose
    var sizeKb: String? = null
    @SerializedName("file_type")
    @Expose
    var fileType: String? = null
    @SerializedName("absolute_url")
    @Expose
    var absoluteUrl: String? = null
}
