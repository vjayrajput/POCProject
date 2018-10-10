package app.akeed.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class RestaurantList {

    @SerializedName("popular")
    private ArrayList<Restaurant> popularRestaurant = null;

    @SerializedName("fastest")
    private ArrayList<Restaurant> fastestRestaurant = null;

    @SerializedName("favourite")
    private ArrayList<Restaurant> favouriteRestaurant = null;

    @SerializedName("new")
    private ArrayList<Restaurant> newRestaurant = null;

    public ArrayList<Restaurant> getPopularRestaurant() {
        return popularRestaurant;
    }

    public void setPopularRestaurant(ArrayList<Restaurant> popularRestaurant) {
        this.popularRestaurant = popularRestaurant;
    }

    public ArrayList<Restaurant> getFastestRestaurant() {
        return fastestRestaurant;
    }

    public void setFastestRestaurant(ArrayList<Restaurant> fastestRestaurant) {
        this.fastestRestaurant = fastestRestaurant;
    }

    public ArrayList<Restaurant> getFavouriteRestaurant() {
        return favouriteRestaurant;
    }

    public void setFavouriteRestaurant(ArrayList<Restaurant> favouriteRestaurant) {
        this.favouriteRestaurant = favouriteRestaurant;
    }

    public ArrayList<Restaurant> getNewRestaurant() {
        return newRestaurant;
    }

    public void setNewRestaurant(ArrayList<Restaurant> newRestaurant) {
        this.newRestaurant = newRestaurant;
    }

}
