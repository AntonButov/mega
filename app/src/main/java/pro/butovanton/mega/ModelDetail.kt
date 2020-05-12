package pro.butovanton.mega

import androidx.databinding.BaseObservable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ModelDetail : BaseObservable() {
    @SerializedName("id")
    @Expose
    var id: String? = null

    @SerializedName("name")
    @Expose
    var name: String? = null

    @JvmField
    @SerializedName("img")
    @Expose
    var img: String? = null

    @SerializedName("description")
    @Expose
    var description: String? = null

    @SerializedName("lat")
    @Expose
    var lat: String? = null

    @SerializedName("lon")
    @Expose
    var lon: String? = null

    @SerializedName("www")
    @Expose
    var www: String? = null

    @SerializedName("phone")
    @Expose
    var phone: String? = null
    override fun toString(): String {
        return "$id $name $img $description $lat $lon $www $phone\n"
    }

    val phoneVisible: Boolean
        get() = phone != ""

    val wwwVisible: Boolean
        get() = www != ""
}