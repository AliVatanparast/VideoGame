package vgame.ir.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

import java.util.Objects

class Teacher {

    @SerializedName("id")
    @Expose
    var id: Int? = null
    @SerializedName("description")
    @Expose
    var description: String? = null
    @SerializedName("user_id")
    @Expose
    var userId: Int? = null
    @SerializedName("courses_count")
    @Expose
    var coursesCount: Int? = null
    @SerializedName("user")
    @Expose
    var user: User? = null

    @SerializedName("avatar_url")
    @Expose
    var avatar_url: String? = null

    override fun equals(o: Any?): Boolean {
        if (this === o) return true
        if (o == null || javaClass != o.javaClass) return false
        val teacher = o as Teacher?
        return id == teacher!!.id
    }

    override fun hashCode(): Int {
        return Objects.hash(id)
    }

}
