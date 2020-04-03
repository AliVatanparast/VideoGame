package vgame.ir.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

import com.google.gson.annotations.SerializedName

@Entity(tableName = "lesson")
class LessonEntity {

    @PrimaryKey
    @SerializedName("id")
    var id: Int = 0

    @SerializedName("title")
    var title: String? = null

    @SerializedName("description")
    var description: String? = null

    @SerializedName("voise_url")
    var voise_url: String? = null

    @SerializedName("voice_comment")
    var voice_comment: String? = null

    @SerializedName("word_url")
    var word_url: String? = null

    @SerializedName("course_chapter_id")
    var course_chapter_id: Int = 0

    @SerializedName("chapter_name")
    var chapter_name: String? = null
}
