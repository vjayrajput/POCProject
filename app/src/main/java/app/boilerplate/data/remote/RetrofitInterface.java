package app.boilerplate.data.remote;

import app.boilerplate.data.model.api.RestaurantList;
import io.reactivex.Single;
import retrofit2.http.GET;


public interface RetrofitInterface {

    @GET("master/restaurant.json")
    Single<RestaurantList> getRestaurantListApiCall();

}
