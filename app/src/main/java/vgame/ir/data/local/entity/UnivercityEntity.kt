package vgame.ir.data.local.entity

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

import com.google.gson.annotations.SerializedName

@Entity(tableName = "univerciry")
class UnivercityEntity {

    @PrimaryKey
    @SerializedName("id")
    var id: Int = 0

    @SerializedName("url")
    var url: String? = null

    @SerializedName("name")
    var name: String? = null

    @Ignore
    @SerializedName("colleges")
    var colleges: List<CollegeEntity>? = null
}