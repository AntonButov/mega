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
    MutableLiveData<ModelDetail> modelDetail = new MutableLiveData<ModelDetail>();

    public ViewModelDetail() {
        networkService = NetworkService.getInstance();
        jsonPlaceHolderApi = networkService.getJSONApi();
    }

    public LiveData<ModelDetail> getModelDetail(int id)    {

        jsonPlaceHolderApi.getModelDetail(id).enqueue(new Callback<List<ModelDetail>>() {
            @Override
            public void onResponse(Call<List<ModelDetail>> call, Response<List<ModelDetail>> response) {
                ModelDetail modelDet = response.body().get(0);
                modelDet.setImg(NetworkService.BASE_URL + modelDet.getImg());
                modelDetail.setValue(modelDet);
            }

            @Override
            public void onFailure(Call<List<ModelDetail>> call, Throwable t) {
                Log.d("DEBUG", t.toString());
            }
        });
        return modelDetail ;
    }

}
