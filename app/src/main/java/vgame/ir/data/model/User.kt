package vgame.ir.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

import vgame.ir.data.remote.model.CertificateModel
import vgame.ir.data.remote.model.Course

class User {
    @SerializedName("name")
    var name: String? = null

    @SerializedName("avatar")
    var avatar: String? = null

    @SerializedName("mobile")
    var mobile: String? = null

    @SerializedName("id")
    var id: Int? = null

    @SerializedName("courses")
    @Expose
    var courses: List<Course>? = null

    @SerializedName("user_certificates")
    @Expose
    var userCertificates: List<CertificateModel>? = null
}
