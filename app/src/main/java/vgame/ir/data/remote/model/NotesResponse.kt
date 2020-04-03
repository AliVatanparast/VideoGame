package vgame.ir.data.remote.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class NotesResponse {
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

        @SerializedName("note")
        @Expose
        var note: Note? = null

    }

    inner class Note {

        @SerializedName("id")
        @Expose
        var id: Int? = null
        @SerializedName("body")
        @Expose
        var body: String? = null
        @SerializedName("on_time")
        @Expose
        var onTime: Any? = null
        @SerializedName("user_id")
        @Expose
        var userId: Int? = null
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

    }
}
