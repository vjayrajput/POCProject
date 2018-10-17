package app.boilerplate.ui.restaurant.fragment;

public class RestaurantEmptyItemViewModel {

    private final RestaurantEmptyItemViewModelListener mListener;

    public RestaurantEmptyItemViewModel(RestaurantEmptyItemViewModelListener listener) {
        this.mListener = listener;
    }

    public void onRetryClick() {
        mListener.onRetryClick();
    }

    public interface RestaurantEmptyItemViewModelListener {

        void onRetryClick();
    }
}
