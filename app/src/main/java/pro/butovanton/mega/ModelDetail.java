package pro.butovanton.mega;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ModelDetail extends BaseObservable {

    @SerializedName("id")
    @Expose
    String id;

    @SerializedName("name")
    @Expose
    String name;

    @SerializedName("img")
    @Expose
    String img;

    @SerializedName("description")
    @Expose
    String description;

    @SerializedName("lat")
    @Expose
    String lat;

    @SerializedName("lon")
    @Expose
    String lon;

    @SerializedName("www")
    @Expose
    String www;

    @SerializedName("phone")
    @Expose
    String phone;

    public String toString() {
        return id + " " + name + " " + img + " " + description + " " + lat + " " + lon + " " + www + " " + phone + "\n";
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getLat() {
        return lat;
    }

    public String getLon() {
        return lon;
    }

    public String getPhone() {
        return phone;
    }

    public String getWww() {
        return www;
    }
}
