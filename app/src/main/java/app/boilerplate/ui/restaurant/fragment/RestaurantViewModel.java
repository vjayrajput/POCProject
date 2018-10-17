package app.boilerplate.ui.restaurant.fragment;

import android.arch.lifecycle.MutableLiveData;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;

import java.util.ArrayList;
import java.util.List;

import app.boilerplate.data.DataManager;
import app.boilerplate.data.model.api.Restaurant;
import app.boilerplate.data.model.api.RestaurantList;
import app.boilerplate.ui.base.BaseViewModel;
import app.boilerplate.utils.rx.SchedulerProvider;

public class RestaurantViewModel extends BaseViewModel<RestaurantNavigator> {

    private static final String TAG = RestaurantViewModel.class.getSimpleName();

    private final ObservableList<RestaurantItemViewModel> restaurantItemViewModels = new ObservableArrayList<>();

    private final MutableLiveData<List<RestaurantItemViewModel>> restaurantItemsLiveData;

    private int type = 0;

    public RestaurantViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
        restaurantItemsLiveData = new MutableLiveData<>();
        fetchRestaurantList();
    }

    public void setRestaurantType(int type) {
        this.type = type;
    }

    public void addRestaurantItemsToList(List<RestaurantItemViewModel> openSourceItems) {
        restaurantItemViewModels.clear();
        restaurantItemViewModels.addAll(openSourceItems);
    }

    public void fetchRestaurantList() {
        setIsLoading(true);
        getCompositeDisposable().add(getDataManager()
                .getRestaurantListApiCall()
                .subscribeOn(getSchedulerProvider().io())
                .observeOn(getSchedulerProvider().ui())
                .subscribe(restaurantList -> {
                    if (restaurantList != null) {
                        restaurantItemsLiveData.setValue(getViewModelList(restaurantList));
                    }
                    setIsLoading(false);
                }, throwable -> {
                    setIsLoading(false);
                    getNavigator().handleError(throwable);
                }));
    }

    public ObservableList<RestaurantItemViewModel> getRestaurantItemViewModels() {
        return restaurantItemViewModels;
    }

    public MutableLiveData<List<RestaurantItemViewModel>> getRestaurantRepos() {
        return restaurantItemsLiveData;
    }

    public List<RestaurantItemViewModel> getViewModelList(RestaurantList restaurantList) {
        List<RestaurantItemViewModel> restaurantItemViewModels = new ArrayList<>();
        ArrayList<Restaurant> data = new ArrayList<>();
        switch (type) {
            case 0:
                data = restaurantList.getPopularRestaurant();
                break;
            case 1:
                data = restaurantList.getNewRestaurant();
                break;
            case 2:
                data = restaurantList.getFastestRestaurant();
                break;
            case 3:
                data = restaurantList.getFavouriteRestaurant();
                break;
            default:
                data = restaurantList.getPopularRestaurant();
                break;
        }

        for (Restaurant restaurant : data) {
            restaurantItemViewModels.add(new RestaurantItemViewModel(
                    restaurant.getId(),
                    restaurant.getFavouriteCount(),
                    restaurant.getIsFavourite(),
                    restaurant.getName(),
                    restaurant.getAddress(),
                    restaurant.getOpenStatus(),
                    restaurant.getImageUrl(),
                    restaurant.getOffers(),
                    restaurant.getFoodTypes(),
                    restaurant.getCategorys()));
        }
        return restaurantItemViewModels;
    }
}
