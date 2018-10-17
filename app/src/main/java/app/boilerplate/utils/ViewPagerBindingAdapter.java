package app.boilerplate.utils;

import android.databinding.BindingAdapter;
import android.databinding.BindingMethod;
import android.databinding.BindingMethods;
import android.databinding.InverseBindingAdapter;
import android.databinding.InverseBindingListener;
import android.databinding.adapters.ListenerUtil;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;

import app.boilerplate.R;

@BindingMethods({
        @BindingMethod(type = ViewPager.class, attribute = "android:offscreenPageLimit", method = "setOffscreenPageLimit"),
        @BindingMethod(type = ViewPager.class, attribute = "android:adapter", method = "setAdapter"),
        @BindingMethod(type = ViewPager.class, attribute = "android:currentPage", method = "setCurrentItem"),
})
public final class ViewPagerBindingAdapter {

    private ViewPagerBindingAdapter() {
        throw new UnsupportedOperationException();
    }

    @InverseBindingAdapter(attribute = "android:currentPage", event = "android:currentPageAttrChanged")
    public static int getCurrentPage(@NonNull final ViewPager pager) {
        return pager.getCurrentItem();
    }

    @BindingAdapter(value = {"android:onPageScrolled", "android:onPageSelected", "android:onPageScrollStateChanged",
            "android:currentPageAttrChanged"}, requireAll = false)
    public static void onSetAdapter(@NonNull final ViewPager pager, final OnPageScrolled scrolled, final OnPageSelected selected,
                                    final OnPageScrollStateChanged scrollStateChanged, final InverseBindingListener currentPageAttrChanged) {

        final ViewPager.OnPageChangeListener newValue;
        if (scrolled == null && selected == null && scrollStateChanged == null && currentPageAttrChanged == null) {
            newValue = null;
        } else {
            newValue = new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(final int position, final float positionOffset, final int positionOffsetPixels) {
                    if (scrolled != null) {
                        scrolled.onPageScrolled(position, positionOffset, positionOffsetPixels);
                    }
                }

                @Override
                public void onPageSelected(final int position) {
                    if (selected != null) {
                        selected.onPageSelected(position);
                    }
                    if (currentPageAttrChanged != null) {
                        currentPageAttrChanged.onChange();
                    }
                }

                @Override
                public void onPageScrollStateChanged(final int state) {
                    if (scrollStateChanged != null) {
                        scrollStateChanged.onPageScrollStateChanged(state);
                    }
                }
            };
        }
        final ViewPager.OnPageChangeListener oldValue = ListenerUtil.trackListener(pager, newValue, R.id.page_change_listener);
        if (oldValue != null) {
            pager.removeOnPageChangeListener(oldValue);
        }
        if (newValue != null) {
            pager.addOnPageChangeListener(newValue);
        }
    }

    public interface OnPageScrolled {
        void onPageScrolled(int position, float positionOffset, int positionOffsetPixels);
    }

    public interface OnPageSelected {
        void onPageSelected(int position);
    }

    public interface OnPageScrollStateChanged {
        void onPageScrollStateChanged(int state);
    }
}