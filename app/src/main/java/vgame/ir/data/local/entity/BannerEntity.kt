package vgame.ir.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

import com.google.gson.annotations.SerializedName

@Entity(tableName = "banner")
class BannerEntity {

    @PrimaryKey
    @SerializedName("id")
    var id: Int = 0

    @SerializedName("url")
    var url: String? = null
}