package vgame.ir.data.remote.model


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class HomeResponse {
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

        @SerializedName("colleges")
        @Expose
        var univercities: List<UnivercityResponse>? = null
    }
}
