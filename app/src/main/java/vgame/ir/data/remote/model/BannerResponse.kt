package vgame.ir.data.remote.model


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

import vgame.ir.data.model.Attachment

class BannerResponse {
    @SerializedName("error")
    @Expose
    var error: Boolean? = null
    @SerializedName("message")
    @Expose
    var message: String? = null
    @SerializedName("data")
    @Expose
    var data: Data? = null

    inner class Data {
        @SerializedName("sliders")
        @Expose
        var sliders: List<Slider>? = null
    }

    inner class Slider {
        @SerializedName("id")
        @Expose
        var id: Int? = null
        @SerializedName("text")
        @Expose
        var text: String? = null
        @SerializedName("link_type")
        @Expose
        var linkType: Int? = null
        @SerializedName("link")
        @Expose
        var link: String? = null
        @SerializedName("priority")
        @Expose
        var priority: Int? = null
        @SerializedName("attachments")
        @Expose
        var attachments: List<Attachment>? = null
    }
}
