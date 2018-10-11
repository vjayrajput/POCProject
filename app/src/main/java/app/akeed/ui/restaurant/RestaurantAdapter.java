package app.akeed.ui.restaurant;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import app.akeed.R;
import app.akeed.databinding.ItemRestaurantBinding;
import app.akeed.model.Restaurant;
import app.akeed.ui.listener.OnRestaurantItemClickListener;

public class RestaurantAdapter extends RecyclerView.Adapter<RestaurantAdapter.MyViewHolder> {
    private final OnRestaurantItemClickListener listener;
    private ArrayList<Restaurant> dataSet;

    public RestaurantAdapter(ArrayList<Restaurant> data, OnRestaurantItemClickListener listener) {
        this.dataSet = data;
        this.listener = listener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemRestaurantBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_restaurant, parent, false);
        return new MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final Restaurant restaurant = dataSet.get(position);
        holder.binding.setRestaurant(restaurant);
        holder.binding.imgFavourite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                restaurant.setIsFavourite(restaurant.getIsFavourite() == 0 ? 1 : 0);
            }
        });
        holder.binding.getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(v, restaurant);
            }
        });
    }


    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private final ItemRestaurantBinding binding;

        public MyViewHolder(final ItemRestaurantBinding itemBinding) {
            super(itemBinding.getRoot());
            this.binding = itemBinding;
        }
    }
}
