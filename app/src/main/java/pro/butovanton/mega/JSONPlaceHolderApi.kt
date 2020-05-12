package pro.butovanton.mega

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface JSONPlaceHolderApi {
    @get:GET("test.php")
    val model: Call<List<MModel?>?>?

    @GET("test.php")
    fun getModelDetail(@Query("id") id: Int): Call<List<ModelDetail?>?>?
}