package vgame.ir.data.local.entity

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

import com.google.gson.annotations.SerializedName

@Entity(tableName = "chapter")
class ChapterEntity {

    @PrimaryKey
    @SerializedName("id")
    var id: Int = 0

    @SerializedName("title")
    var title: String? = null

    @SerializedName("course_id")
    var course_id: Int = 0

    @Ignore
    @SerializedName("sessions")
    var lessons: List<LessonEntity>? = null

    @Ignore
    @SerializedName("course")
    var course: CourseEntity? = null

    fun getTitle(name: String) {
        this.title = name
    }
}
