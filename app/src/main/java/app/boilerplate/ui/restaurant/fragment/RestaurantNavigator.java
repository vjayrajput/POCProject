package app.boilerplate.ui.restaurant.fragment;

public interface RestaurantNavigator {

    void handleError(Throwable throwable);

    void openRestaurantDetailActivity();
}
