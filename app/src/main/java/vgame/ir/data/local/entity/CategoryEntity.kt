package vgame.ir.data.local.entity

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

import com.google.gson.annotations.SerializedName

@Entity(tableName = "category")
class CategoryEntity {

    @PrimaryKey
    @SerializedName("id")
    var id: Int = 0

    @SerializedName("college_id")
    var college_id: Int = 0

    @SerializedName("name")
    var name: String? = null

    @Ignore
    @SerializedName("courses")
    var courses: List<CourseEntity>? = null
}