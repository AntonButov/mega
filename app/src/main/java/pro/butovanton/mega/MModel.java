package pro.butovanton.mega;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MModel {

    @SerializedName("id")
    @Expose
    String id;

    @SerializedName("name")
    @Expose
    String name;

    @SerializedName("img")
    @Expose
    String img;

 public String toString() {
     return id + ", " + name + "\n";
 }
}
