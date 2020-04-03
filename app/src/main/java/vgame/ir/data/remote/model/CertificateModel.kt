package vgame.ir.data.remote.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

import vgame.ir.data.model.Attachment

class CertificateModel {

    @SerializedName("id")
    @Expose
    var id: Int? = null
    @SerializedName("user_id")
    @Expose
    var userId: Int? = null
    @SerializedName("certificate_id")
    @Expose
    var certificateId: Int? = null
    @SerializedName("created_at")
    @Expose
    var createdAt: Any? = null
    @SerializedName("updated_at")
    @Expose
    var updatedAt: Any? = null
    @SerializedName("certificate")
    @Expose
    var certificate: Certificate? = null

    inner class Certificate {
        @SerializedName("id")
        @Expose
        var id: Int? = null
        @SerializedName("name")
        @Expose
        var name: String? = null
        @SerializedName("model")
        @Expose
        var model: String? = null
        @SerializedName("model_id")
        @Expose
        var modelId: Int? = null
        @SerializedName("created_at")
        @Expose
        var createdAt: String? = null
        @SerializedName("updated_at")
        @Expose
        var updatedAt: String? = null
        @SerializedName("attachments")
        @Expose
        var attachments: List<Attachment>? = null

    }
}
