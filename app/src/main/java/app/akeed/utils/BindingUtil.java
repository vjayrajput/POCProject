package app.akeed.utils;

import android.databinding.BindingAdapter;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.SpannableString;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import app.akeed.R;
import app.akeed.ui.common.ShopStatus;

import static android.text.Spanned.SPAN_EXCLUSIVE_EXCLUSIVE;
import static android.text.Spanned.SPAN_INCLUSIVE_INCLUSIVE;

public class BindingUtil {

    private static final String TAG = BindingUtil.class.getSimpleName();


    @BindingAdapter({"setUpWithViewpager"})
    public static void setUpWithViewpager(final TabLayout tabLayout, ViewPager viewPager) {
        viewPager.addOnAdapterChangeListener(new ViewPager.OnAdapterChangeListener() {
            @Override
            public void onAdapterChanged(@NonNull ViewPager viewPager,
                                         @Nullable PagerAdapter oldAdapter, @Nullable PagerAdapter newAdapter) {
                if (oldAdapter == null && newAdapter == null) {
                    return;
                }
                tabLayout.setupWithViewPager(viewPager);
            }
        });
    }

    @BindingAdapter({"imageUrl"})
    public static void loadImage(ImageView image, String imageUrl) {
        Picasso.get().load(imageUrl).into(image);
    }

    @BindingAdapter({"name", "address"})
    public static void setNameAddress(TextView view, String name, String address) {

        Typeface nameFont = ResourcesCompat.getFont(view.getContext(), R.font.avenir_ltstd_black);
        Typeface addressFont = ResourcesCompat.getFont(view.getContext(), R.font.avenir_ltstd_light);

        int nameTextSize = (int) view.getContext().getResources().getDimension(R.dimen.sp13);
        int addressTextSize = (int) view.getContext().getResources().getDimension(R.dimen.sp10);

        String str = name + " " + address;
        SpannableString ss1 = new SpannableString(str);
        ss1.setSpan(new AbsoluteSizeSpan(nameTextSize), 0, name.length(), SPAN_INCLUSIVE_INCLUSIVE);
        ss1.setSpan(new AbsoluteSizeSpan(addressTextSize), name.length() + 1, str.length(), SPAN_INCLUSIVE_INCLUSIVE);
        ss1.setSpan(new ForegroundColorSpan(view.getContext().getResources().getColor(R.color.scootsy_black)), 0, name.length(), 0);
        ss1.setSpan(new ForegroundColorSpan(view.getContext().getResources().getColor(R.color.scootsy_black)), name.length() + 1, str.length(), 0);
        ss1.setSpan(new CustomTypefaceSpan("", nameFont), 0, name.length(), SPAN_EXCLUSIVE_EXCLUSIVE);
        ss1.setSpan(new CustomTypefaceSpan("", addressFont), name.length() + 1, str.length(), SPAN_EXCLUSIVE_EXCLUSIVE);

        view.setText(ss1);

    }

    @BindingAdapter({"type"})
    public static void setType(TextView view, ArrayList<String> foodTypes) {

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < foodTypes.size(); i++) {
            sb.append("\u25CF ");
            sb.append(foodTypes.get(i));
            sb.append("   ");
        }
        view.setText(sb);

    }

    @BindingAdapter({"status"})
    public static void setStatus(TextView view, String status) {
        if (status.equals(String.valueOf(ShopStatus.CLOSE))) {
            view.setText(view.getContext().getResources().getString(R.string.shop_status_close));
        } else if (status.equals(String.valueOf(ShopStatus.OPEN))) {
            view.setText(view.getContext().getResources().getString(R.string.shop_status_open));
        } else if (status.equals(String.valueOf(ShopStatus.PRE_OPEN))) {
            view.setText(view.getContext().getResources().getString(R.string.shop_status_preorder));
        }
    }

}
