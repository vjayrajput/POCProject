package app.boilerplate.ui.restaurant;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import app.boilerplate.ui.restaurant.fragment.RestaurantFragment;

public class RestaurantPageAdapter extends FragmentStatePagerAdapter {
    private int mTabCount;

    public RestaurantPageAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
        this.mTabCount = 0;
    }

    @Override
    public int getCount() {
        return mTabCount;
    }

    public void setCount(int count) {
        mTabCount = count;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return RestaurantFragment.newInstance(0);
            case 1:
                return RestaurantFragment.newInstance(1);
            case 2:
                return RestaurantFragment.newInstance(2);
            case 3:
                return RestaurantFragment.newInstance(3);
            default:
                return null;
        }
    }
}

