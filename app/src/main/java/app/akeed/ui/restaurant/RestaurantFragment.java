package app.akeed.ui.restaurant;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import app.akeed.model.Restaurant;
import app.akeed.model.RestaurantList;
import app.akeed.ui.detail.RestaurantDetailActivity;
import app.akeed.ui.listener.OnRestaurantItemClickListener;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class RestaurantFragment extends Fragment {
    @BindView(R.id.my_recycler_view)
    RecyclerView recyclerView;
    private RestaurantAdapter adapter;
    private ArrayList<Restaurant> data = new ArrayList<>();
    private RecyclerView.LayoutManager layoutManager;
    private int type;
    private Unbinder unbinder;

    public RestaurantFragment() {
        // Required empty public constructor
    }

    public static RestaurantFragment newInstance(int type) {
        RestaurantFragment restaurantFragment = new RestaurantFragment();
        Bundle args = new Bundle();
        args.putInt("type", type);
        restaurantFragment.setArguments(args);
        return restaurantFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        type = getArguments().getInt("type", 0);
        View root = inflater.inflate(R.layout.fragment_restaurant, container, false);
        unbinder = ButterKnife.bind(this, root);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
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
        adapter = new RestaurantAdapter(getActivity(), data, new OnRestaurantItemClickListener() {
            @Override
            public void onItemClick(View item, Restaurant restaurant) {
                Intent intent = new Intent(getActivity(), RestaurantDetailActivity.class);
                intent.putExtra("restaurant", restaurant);
                startActivity(intent);
            }
        });
        recyclerView.setAdapter(adapter);
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

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
