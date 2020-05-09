package pro.butovanton.mega;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ViewModelModel extends ViewModel {

    private NetworkService networkService;
    private JSONPlaceHolderApi jsonPlaceHolderApi;
    MutableLiveData<List<MModel>> model = new MutableLiveData<List<MModel>>();

    public ViewModelModel() {
        networkService = NetworkService.getInstance();
        jsonPlaceHolderApi = networkService.getJSONApi();
    }

    public LiveData<List<MModel>> getModel() {

            jsonPlaceHolderApi.getModel().enqueue(new Callback<List<MModel>>() {
                @Override
                public void onResponse(Call<List<MModel>> call, Response<List<MModel>> response) {
                    model.setValue(response.body());
                }

                @Override
                public void onFailure(Call<List<MModel>> call, Throwable t) {
                    Log.d("DEBUG", t.toString());
                }
            });

        return model;
    }

}
