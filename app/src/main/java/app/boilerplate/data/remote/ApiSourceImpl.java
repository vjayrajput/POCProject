package app.boilerplate.data.remote;

import app.boilerplate.data.model.api.RestaurantList;
import io.reactivex.Single;
import retrofit2.Retrofit;


public class ApiSourceImpl implements ApiSource {

    RetrofitInterface retrofitInterface;

    public ApiSourceImpl(Retrofit retrofit) {
        retrofitInterface = retrofit.create(RetrofitInterface.class);
    }

    @Override
    public Single<RestaurantList> getRestaurantListApiCall() {
        return retrofitInterface.getRestaurantListApiCall();
    }
}
