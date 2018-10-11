package app.akeed.ui.detail;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;

import app.akeed.BR;
import app.akeed.model.Category;
import app.akeed.model.Restaurant;
import app.akeed.ui.common.ViewPagerAdapter;

public class RestaurantDetailViewModel extends BaseObservable {
    FragmentActivity mContext;
    ViewPagerAdapter adapter;
    Restaurant restaurant = null;

    RestaurantDetailViewModel(FragmentActivity context, Restaurant restaurant) {
        this.restaurant = restaurant;
        mContext = context;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                createViewPager();
            }
        }, 10);

    }

    @Bindable
    public PagerAdapter getPagerAdapter() {
        return adapter;
    }

    private void createViewPager() {
        adapter = new ViewPagerAdapter(mContext.getSupportFragmentManager());
        for (int i = 0; i < restaurant.getCategorys().size(); i++) {
            Category category = restaurant.getCategorys().get(i);
            adapter.addFrag(CategoryFragment.newInstance(category.getItems()), category.getName());
        }
        notifyPropertyChanged(BR.pagerAdapter);
    }

    public void onBackClick() {
        mContext.finish();
    }
}