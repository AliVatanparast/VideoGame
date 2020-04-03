package vgame.ir.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

import com.google.gson.annotations.SerializedName

@Entity(tableName = "time_spent")
class TimeSpentEntity {

    @PrimaryKey(autoGenerate = true)
    @SerializedName("id")
    var id: Int = 0

    @SerializedName("lesson_id")
    var lesson_id: Int = 0

    @SerializedName("time")
    var time: Long = 0
}