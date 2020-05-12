package pro.butovanton.mega

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class MModel {
    @JvmField
    @SerializedName("id")
    @Expose
    var id: String? = null

    @JvmField
    @SerializedName("name")
    @Expose
    var name: String? = null

    @JvmField
    @SerializedName("img")
    @Expose
    var img: String? = null
    override fun toString(): String {
        return "$id, $name, $img\n"
    }

}