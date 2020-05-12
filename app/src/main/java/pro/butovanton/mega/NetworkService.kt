package pro.butovanton.mega

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NetworkService private constructor() {
    private val mRetrofit: Retrofit
    val jSONApi: JSONPlaceHolderApi
        get() = mRetrofit.create(JSONPlaceHolderApi::class.java)

    companion object {
        private var mInstance: NetworkService? = null
        const val BASE_URL = "http://megakohz.bget.ru/test_task/"
        @JvmStatic
        val instance: NetworkService?
            get() {
                if (mInstance == null) {
                    mInstance = NetworkService()
                }
                return mInstance
            }
    }

    init {
        mRetrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }
}