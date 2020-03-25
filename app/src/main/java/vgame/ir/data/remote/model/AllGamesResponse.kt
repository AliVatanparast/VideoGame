package vgame.ir.data.remote.model


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class AllGamesResponse {
    @SerializedName("count")
    @Expose
    var count: Int? = null
    @SerializedName("next")
    @Expose
    var next: String? = null
    @SerializedName("previous")
    @Expose
    var previous: String? = null
    @SerializedName("results")
    @Expose
    var results: List<Game>? = null

    inner class Game {
        @SerializedName("id")
        @Expose
        var id: Int? = null
        @SerializedName("background_image")
        @Expose
        var background_image: String? = null
    }
}
