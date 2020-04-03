package vgame.ir.data.local.entity

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

import com.google.gson.annotations.SerializedName

@Entity(tableName = "course")
class CourseEntity {

    @PrimaryKey
    @SerializedName("id")
    var id: Int = 0

    @SerializedName("category_id")
    var uni_id: Int = 0

    @SerializedName("name")
    var name: String? = null

    @SerializedName("description")
    var description: String? = null

    @SerializedName("url")
    var url: String? = null

    @SerializedName("bookmark")
    var isBookmark: Boolean = false

    @SerializedName("teacher_id")
    var teacher_id: String? = null

    @SerializedName("teacher_name")
    var teacher_name: String? = null

    @Ignore
    @SerializedName("chapters")
    var chapters: List<ChapterEntity>? = null

    @SerializedName("members_count")
    var members_count: Int? = null

    @Ignore
    @SerializedName("title")
    var title: String? = null

    fun getCategory_id(): Int {
        return uni_id
    }

    fun setCategory_id(category_id: Int) {
        this.uni_id = category_id
    }
}