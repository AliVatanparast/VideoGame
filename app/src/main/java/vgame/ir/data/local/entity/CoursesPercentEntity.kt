package vgame.ir.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

import com.google.gson.annotations.SerializedName

@Entity(tableName = "courses_percent")
class CoursesPercentEntity {

    @SerializedName("id")
    var id: Int = 0

    @PrimaryKey
    @SerializedName("course_id")
    var course_id: Int = 0

    @SerializedName("url")
    var url: String? = null

    @SerializedName("name")
    var name: String? = null

    @SerializedName("total_lessons")
    var total_lessons: Int = 0

    @SerializedName("passed_lessons")
    var passed_lessons: String? = null
}