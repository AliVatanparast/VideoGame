package vgame.ir.data.remote.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class CollegeResponse {
    @SerializedName("id")
    @Expose
    var id: Int? = null
    @SerializedName("name")
    @Expose
    var name: String? = null
    @SerializedName("college_id")
    @Expose
    var collegeId: Int? = null
    @SerializedName("categories")
    @Expose
    var categories: List<CategoryResponse>? = null
}
