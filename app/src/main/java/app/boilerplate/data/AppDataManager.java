package app.boilerplate.data;

import android.content.Context;

import com.google.gson.Gson;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import app.boilerplate.data.local.DbHelper;
import app.boilerplate.data.local.prefs.PreferencesHelper;
import app.boilerplate.data.model.api.RestaurantList;
import app.boilerplate.data.model.db.OrderItem;
import app.boilerplate.data.remote.ApiSource;
import io.reactivex.Observable;
import io.reactivex.Single;

@Singleton
public class AppDataManager implements DataManager {

    private final ApiSource mApiSource;

    private final Context mContext;

    private final DbHelper mDbHelper;

    private final Gson mGson;

    private final PreferencesHelper mPreferencesHelper;

    @Inject
    public AppDataManager(Context context, DbHelper dbHelper, PreferencesHelper preferencesHelper, ApiSource apiSource, Gson gson) {
        mContext = context;
        mDbHelper = dbHelper;
        mPreferencesHelper = preferencesHelper;
        mApiSource = apiSource;
        mGson = gson;
    }


    @Override
    public String getAccessToken() {
        return mPreferencesHelper.getAccessToken();
    }

    @Override
    public void setAccessToken(String accessToken) {
        mPreferencesHelper.setAccessToken(accessToken);
    }

    @Override
    public int getCurrentUserLoggedInMode() {
        return mPreferencesHelper.getCurrentUserLoggedInMode();
    }

    @Override
    public void setCurrentUserLoggedInMode(LoggedInMode mode) {
        mPreferencesHelper.setCurrentUserLoggedInMode(mode);
    }


    @Override
    public Single<RestaurantList> getRestaurantListApiCall() {
        return mApiSource.getRestaurantListApiCall();
    }

    @Override
    public Observable<Boolean> saveOrderItem(OrderItem orderItem) {
        return mDbHelper.saveOrderItem(orderItem);
    }

    @Override
    public Observable<List<OrderItem>> getAllOrderItems() {
        return mDbHelper.getAllOrderItems();
    }

    @Override
    public Observable<List<OrderItem>> getAllOrderItemsByIds(Long itemId) {
        return mDbHelper.getAllOrderItemsByIds(itemId);
    }
}
