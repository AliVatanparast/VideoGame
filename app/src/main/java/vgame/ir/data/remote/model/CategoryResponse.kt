package vgame.ir.data.remote.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class CategoryResponse {

    @SerializedName("id")
    @Expose
    var id: Int? = null
    @SerializedName("name")
    @Expose
    var name: String? = null
    @SerializedName("major_id")
    @Expose
    var majorId: Int? = null
    @SerializedName("courses")
    @Expose
    var courses: List<Course>? = null
}
