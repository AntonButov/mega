package pro.butovanton.mega

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import pro.butovanton.mega.NetworkService.Companion.instance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ViewModelModel : ViewModel() {
    private val networkService: NetworkService?
    private val jsonPlaceHolderApi: JSONPlaceHolderApi
    var model = MutableLiveData<List<MModel>>()
    fun getModel(): LiveData<List<MModel>> {
        var call = jsonPlaceHolderApi.model as Call<List<MModel>>
          call.enqueue(object : Callback<List<MModel>> {
            override fun onResponse(call: Call<List<MModel>>, response: Response<List<MModel>>) {
                val models = response.body()!!
                for (model in models) model.img = NetworkService.BASE_URL + model.img
                model.setValue(response.body())
            }

            override fun onFailure(call: Call<List<MModel>>, t: Throwable) {
                Log.d("DEBUG", t.toString())
            }
        })
        return model
    }

    init {
        networkService = instance
        jsonPlaceHolderApi = networkService!!.jSONApi
    }
}

