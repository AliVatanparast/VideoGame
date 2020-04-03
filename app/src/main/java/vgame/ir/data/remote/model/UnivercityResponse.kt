package vgame.ir.data.remote.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

import vgame.ir.data.model.Attachment

class UnivercityResponse {
    @SerializedName("id")
    @Expose
    var id: Int? = null
    @SerializedName("name")
    @Expose
    var name: String? = null
    @SerializedName("created_at")
    @Expose
    var createdAt: String? = null
    @SerializedName("updated_at")
    @Expose
    var updatedAt: String? = null
    @SerializedName("majors")
    @Expose
    var collegeResponses: List<CollegeResponse>? = null
    @SerializedName("attachments")
    @Expose
    var attachments: List<Attachment>? = null
}
