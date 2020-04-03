package vgame.ir.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

import com.google.gson.annotations.SerializedName

@Entity(tableName = "default_note")
class DefaultNoteEntity {

    @PrimaryKey(autoGenerate = true)
    @SerializedName("id")
    var id: Int = 0

    @SerializedName("text")
    var text: String? = null

    @SerializedName("lesson_id")
    var lesson_id: Int = 0

    @SerializedName("course_id")
    var course_id: Int = 0

    @SerializedName("lesson_name")
    var lesson_name: String? = null

    @SerializedName("chapter_name")
    var chapter_name: String? = null
}