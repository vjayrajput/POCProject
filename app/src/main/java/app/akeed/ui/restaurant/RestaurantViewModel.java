package app.akeed.ui.restaurant;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;

import app.akeed.BR;
import app.akeed.R;
import app.akeed.ui.common.ViewPagerAdapter;

public class RestaurantViewModel extends BaseObservable {
    FragmentActivity mContext;
    ViewPagerAdapter adapter;
    private int currentPage = 0;
    private int tabSelected = 0;

    RestaurantViewModel(FragmentActivity context) {
        mContext = context;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                createViewPager();
            }
        }, 100);
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public void pageSelected(int pos) {
        this.tabSelected = pos;
        notifyPropertyChanged(BR.tabSelected);
    }

    public void popularTabSelected(ViewPager viewPager) {
        this.tabSelected = 0;
        notifyPropertyChanged(BR.tabSelected);
        viewPager.setCurrentItem(this.tabSelected);
    }

    public void newTabSelected(ViewPager viewPager) {
        this.tabSelected = 1;
        notifyPropertyChanged(BR.tabSelected);
        viewPager.setCurrentItem(this.tabSelected);
    }

    public void fastestTabSelected(ViewPager viewPager) {
        this.tabSelected = 2;
        notifyPropertyChanged(BR.tabSelected);
        viewPager.setCurrentItem(this.tabSelected);
    }

    public void favouriteTabSelected(ViewPager viewPager) {
        this.tabSelected = 3;
        notifyPropertyChanged(BR.tabSelected);
        viewPager.setCurrentItem(this.tabSelected);
    }

    @Bindable
    public int getTabSelected() {
        return tabSelected;
    }

    @Bindable
    public PagerAdapter getPagerAdapter() {
        return adapter;
    }

    private void createViewPager() {
        adapter = new ViewPagerAdapter(mContext.getSupportFragmentManager());
        adapter.addFrag(RestaurantFragment.newInstance(0), mContext.getString(R.string.tab_popular));
        adapter.addFrag(RestaurantFragment.newInstance(1), mContext.getString(R.string.tab_new));
        adapter.addFrag(RestaurantFragment.newInstance(2), mContext.getString(R.string.tab_fastest));
        adapter.addFrag(RestaurantFragment.newInstance(3), mContext.getString(R.string.tab_fevourite));

        notifyPropertyChanged(BR.pagerAdapter);
    }
}
