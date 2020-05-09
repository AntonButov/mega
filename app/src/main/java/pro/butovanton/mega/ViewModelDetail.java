package pro.butovanton.mega;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


class ViewModelDetail extends ViewModel {

    private NetworkService networkService;
    private JSONPlaceHolderApi jsonPlaceHolderApi;
    MutableLiveData<List<ModelDetail>> modelDetail = new MutableLiveData<List<ModelDetail>>();

    public ViewModelDetail() {
        networkService = NetworkService.getInstance();
        jsonPlaceHolderApi = networkService.getJSONApi();
    }

    public LiveData<List<ModelDetail>> getModelDetail(int id)    {

        jsonPlaceHolderApi.getModelDetail(id).enqueue(new Callback<List<ModelDetail>>() {
            @Override
            public void onResponse(Call<List<ModelDetail>> call, Response<List<ModelDetail>> response) {
                modelDetail.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<ModelDetail>> call, Throwable t) {
                Log.d("DEBUG", t.toString());
            }
        });
        return modelDetail ;
    }

}
