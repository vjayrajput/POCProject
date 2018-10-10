package app.akeed.ui.detail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import app.akeed.R;
import app.akeed.model.Category;
import app.akeed.model.Restaurant;
import app.akeed.ui.common.ShopStatus;
import app.akeed.ui.common.ViewPagerAdapter;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RestaurantDetailActivity extends AppCompatActivity {
    private static final String TAG = RestaurantDetailActivity.class.getSimpleName();
    Restaurant restaurant = null;

    @BindView(R.id.htab_tabs)
    TabLayout tabLayout;
    @BindView(R.id.htab_viewpager)
    ViewPager viewPager;
    @BindView(R.id.htab_header)
    ImageView imageView;
    @BindView(R.id.txt_name)
    TextView txtName;
    @BindView(R.id.txt_fav_count)
    TextView txtFavCount;
    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.img_search)
    ImageView imgSearch;
    @BindView(R.id.txt_status)
    TextView txtStatus;

    @OnClick(R.id.img_back)
    public void onBackClick(View view) {
        finish();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_detail);
        ButterKnife.bind(this);

        Bundle data = getIntent().getExtras();
        restaurant = (Restaurant) data.getParcelable("restaurant");

        Picasso.get().load(restaurant.getImageUrl()).into(imageView);
        txtName.setText(restaurant.getName());
        txtFavCount.setText(String.valueOf(restaurant.getFavouriteCount()));

        if (restaurant.getOpenStatus().equals(ShopStatus.CLOSE)) {
            txtStatus.setText(getResources().getString(R.string.shop_status_close));
        } else if (restaurant.getOpenStatus().equals(ShopStatus.OPEN)) {
            txtStatus.setText(getResources().getString(R.string.shop_status_open));
        } else if (restaurant.getOpenStatus().equals(ShopStatus.PRE_OPEN)) {
            txtStatus.setText(getResources().getString(R.string.shop_status_preorder));
        }


        setupViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void setupViewPager(ViewPager viewPager) {
        Log.e(TAG, "setupViewPager category count " + restaurant.getCategorys().size());
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

        for (int i = 0; i < restaurant.getCategorys().size(); i++) {
            Category category = restaurant.getCategorys().get(i);
            adapter.addFrag(CategoryFragment.newInstance(category.getItems()), category.getName());
        }

        viewPager.setAdapter(adapter);
    }

}
