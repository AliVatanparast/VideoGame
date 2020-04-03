package vgame.ir.data.remote.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.util.Objects

import vgame.ir.data.model.Attachment

class Course {

    @SerializedName("id")
    @Expose
    var id: Int? = null
    @SerializedName("title")
    @Expose
    var title: String? = null
    @SerializedName("description")
    @Expose
    var description: String? = null
    @SerializedName("price")
    @Expose
    var price: String? = null
    @SerializedName("teacher_id")
    @Expose
    var teacherId: Int? = null
    @SerializedName("category_id")
    @Expose
    var categoryId: Int? = null
    @SerializedName("is_active")
    @Expose
    var isActive: Int? = null
    @SerializedName("created_at")
    @Expose
    var createdAt: String? = null
    @SerializedName("updated_at")
    @Expose
    var updatedAt: String? = null
    @SerializedName("attachments")
    @Expose
    var attachments: List<Attachment>? = null
    @SerializedName("category")
    @Expose
    var category: CoursesResponse.Category? = null
    @SerializedName("teacher")
    @Expose
    var teacher: CoursesResponse.Teacher? = null

    @SerializedName("pivot")
    @Expose
    var pivot: Pivot? = null

    @SerializedName("members_count")
    @Expose
    var members_count: Int? = null

    var hight_light: String? = null

    inner class Pivot {

        @SerializedName("user_id")
        @Expose
        var userId: Int? = null
        @SerializedName("course_id")
        @Expose
        var courseId: Int? = null
        @SerializedName("is_active")
        @Expose
        var isActive: Int? = null

    }

    override fun equals(o: Any?): Boolean {
        if (this === o) return true
        if (o == null || javaClass != o.javaClass) return false
        val course = o as Course?
        return id == course!!.id && title == course.title
    }

    override fun hashCode(): Int {
        return Objects.hash(id, title)
    }
}
