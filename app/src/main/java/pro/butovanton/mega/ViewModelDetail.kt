package pro.butovanton.mega

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import pro.butovanton.mega.NetworkService.Companion.instance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

internal class ViewModelDetail : ViewModel() {
    private val networkService: NetworkService?
    private val jsonPlaceHolderApi: JSONPlaceHolderApi
    var modelDetail = MutableLiveData<ModelDetail>()
    fun getModelDetail(id: Int): LiveData<ModelDetail> {
        val call = jsonPlaceHolderApi.getModelDetail(id) as Call<List<ModelDetail>>
        call.enqueue(object : Callback<List<ModelDetail>> {
            override fun onResponse(call: Call<List<ModelDetail>>, response: Response<List<ModelDetail>>) {
                val modelDet = response.body()!![0]
                modelDet.img = NetworkService.BASE_URL + modelDet.img
                modelDetail.setValue(modelDet)
            }

            override fun onFailure(call: Call<List<ModelDetail>>, t: Throwable) {
                Log.d("DEBUG", t.toString())
            }
        })
        return modelDetail
    }

    init {
        networkService = instance
        jsonPlaceHolderApi = networkService!!.jSONApi
    }
}

