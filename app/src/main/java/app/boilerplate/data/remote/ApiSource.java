package app.boilerplate.data.remote;

import app.boilerplate.data.model.api.RestaurantList;
import io.reactivex.Single;

public interface ApiSource {

    Single<RestaurantList> getRestaurantListApiCall();
}
