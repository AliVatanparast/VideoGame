package vgame.ir.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

import com.google.gson.annotations.SerializedName

@Entity(tableName = "college")
class CollegeEntity {

    @PrimaryKey
    @SerializedName("id")
    var id: Int = 0

    @SerializedName("uni_id")
    var uni_id: Int = 0

    @SerializedName("name")
    var name: String? = null
}