package vgame.ir.data.remote.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

import java.util.Objects

class ChatMessage {
    @SerializedName("id")
    @Expose
    var id: Int? = null
    @SerializedName("body")
    @Expose
    var body: String? = null
    @SerializedName("user_id")
    @Expose
    var userId: Int? = null
    @SerializedName("reply_id")
    @Expose
    var replyId: Int? = null
    @SerializedName("on_time")
    @Expose
    var onTime: String? = null
    @SerializedName("created_at")
    @Expose
    var createdAt: String? = null

    @SerializedName("username")
    @Expose
    var user_name: String? = null

    @SerializedName("reply_user")
    @Expose
    var reply_user: String? = null

    @SerializedName("reply_message")
    @Expose
    var reply_message: String? = null

    override fun equals(o: Any?): Boolean {
        if (this === o) return true
        if (o == null || javaClass != o.javaClass) return false
        val teacher = o as ChatMessage?
        return id == teacher!!.id
    }
}
