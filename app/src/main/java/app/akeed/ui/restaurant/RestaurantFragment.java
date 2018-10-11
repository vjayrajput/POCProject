package app.akeed.ui.restaurant;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;

import app.akeed.R;
import app.akeed.databinding.FragmentRestaurantBinding;
import app.akeed.model.Restaurant;
import app.akeed.model.RestaurantList;
import app.akeed.ui.detail.RestaurantDetailActivity;
import app.akeed.ui.listener.OnRestaurantItemClickListener;
import app.akeed.utils.Constants;

public class RestaurantFragment extends Fragment {
    FragmentRestaurantBinding binding;
    private RestaurantAdapter adapter;
    private ArrayList<Restaurant> data = new ArrayList<>();
    private int type;

    public RestaurantFragment() {
        // Required empty public constructor
    }

    public static RestaurantFragment newInstance(int type) {
        RestaurantFragment restaurantFragment = new RestaurantFragment();
        Bundle args = new Bundle();
        args.putInt(Constants.KEY_TYPE, type);
        restaurantFragment.setArguments(args);
        return restaurantFragment;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(Constants.KEY_TYPE, type);
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        if (savedInstanceState != null) {
            type = savedInstanceState.getInt(Constants.KEY_TYPE);
        }
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        type = getArguments().getInt(Constants.KEY_TYPE, 0);
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_restaurant, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.recyclerView.setHasFixedSize(true);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.recyclerView.setItemAnimator(new DefaultItemAnimator());
        fetchRestaurantData();
    }

    private void fetchRestaurantData() {

        try {
            String json = loadJSONFromAsset();
            Gson gson = new Gson();
            Type t = new TypeToken<RestaurantList>() {
            }.getType();
            RestaurantList restaurantList = gson.fromJson(json, t);

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


        } catch (Exception e) {
            e.printStackTrace();
        }
        adapter = new RestaurantAdapter(data, new OnRestaurantItemClickListener() {
            @Override
            public void onItemClick(View item, Restaurant restaurant) {
                Intent intent = new Intent(getActivity(), RestaurantDetailActivity.class);
                intent.putExtra("restaurant", restaurant);
                startActivity(intent);
            }
        });
        binding.recyclerView.setAdapter(adapter);
    }

    public String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = getActivity().getAssets().open("restaurant.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

}
