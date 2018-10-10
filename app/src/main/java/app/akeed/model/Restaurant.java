package app.akeed.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Restaurant implements Parcelable {



    @SerializedName("id")
    private String id;
    @SerializedName("favourite_count")
    private Integer favouriteCount;
    @SerializedName("is_favourite")
    private Integer isFavourite;
    @SerializedName("name")
    private String name;
    @SerializedName("address")
    private String address;
    @SerializedName("open_status")
    private String openStatus;
    @SerializedName("image_url")
    private String imageUrl;
    @SerializedName("offers")
    private ArrayList<String> offers = null;
    @SerializedName("food_types")
    private ArrayList<String> foodTypes = null;
    @SerializedName("categorys")
    private ArrayList<Category> categorys = null;



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getFavouriteCount() {
        return favouriteCount;
    }

    public void setFavouriteCount(Integer favouriteCount) {
        this.favouriteCount = favouriteCount;
    }

    public Integer getIsFavourite() {
        return isFavourite;
    }

    public void setIsFavourite(Integer isFavourite) {
        this.isFavourite = isFavourite;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getOpenStatus() {
        return openStatus;
    }

    public void setOpenStatus(String openStatus) {
        this.openStatus = openStatus;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public ArrayList<String> getOffers() {
        return offers;
    }

    public void setOffers(ArrayList<String> offers) {
        this.offers = offers;
    }

    public ArrayList<String> getFoodTypes() {
        return foodTypes;
    }

    public void setFoodTypes(ArrayList<String> foodTypes) {
        this.foodTypes = foodTypes;
    }

    public ArrayList<Category> getCategorys() {
        return categorys;
    }

    public void setCategorys(ArrayList<Category> categorys) {
        this.categorys = categorys;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeValue(this.favouriteCount);
        dest.writeValue(this.isFavourite);
        dest.writeString(this.name);
        dest.writeString(this.address);
        dest.writeString(this.openStatus);
        dest.writeString(this.imageUrl);
        dest.writeStringList(this.offers);
        dest.writeStringList(this.foodTypes);
        dest.writeTypedList(this.categorys);
    }

    public Restaurant() {
    }

    protected Restaurant(Parcel in) {
        this.id = in.readString();
        this.favouriteCount = (Integer) in.readValue(Integer.class.getClassLoader());
        this.isFavourite = (Integer) in.readValue(Integer.class.getClassLoader());
        this.name = in.readString();
        this.address = in.readString();
        this.openStatus = in.readString();
        this.imageUrl = in.readString();
        this.offers = in.createStringArrayList();
        this.foodTypes = in.createStringArrayList();
        this.categorys = in.createTypedArrayList(Category.CREATOR);
    }

    public static final Creator<Restaurant> CREATOR = new Creator<Restaurant>() {
        @Override
        public Restaurant createFromParcel(Parcel source) {
            return new Restaurant(source);
        }

        @Override
        public Restaurant[] newArray(int size) {
            return new Restaurant[size];
        }
    };

    @Override
    public String toString() {
        return "Restaurant{" +
                "id='" + id + '\'' +
                ", favouriteCount=" + favouriteCount +
                ", isFavourite=" + isFavourite +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", openStatus='" + openStatus + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", offers=" + offers +
                ", foodTypes=" + foodTypes +
                '}';
    }
}
