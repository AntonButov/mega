package pro.butovanton.mega;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface JSONPlaceHolderApi {

    @GET("test.php")
    public Call<List<MModel>> getModel ();

    @GET("test.php")
    public Call<List<ModelDetail>> getModelDetail(@Query("id") int id);
}
