package app.boilerplate.ui.restaurant.fragment;

import android.databinding.ObservableField;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

import app.boilerplate.data.model.api.Category;

public class RestaurantItemViewModel implements Parcelable {

    public static final Creator<RestaurantItemViewModel> CREATOR = new Creator<RestaurantItemViewModel>() {
        @Override
        public RestaurantItemViewModel createFromParcel(Parcel source) {
            return new RestaurantItemViewModel(source);
        }

        @Override
        public RestaurantItemViewModel[] newArray(int size) {
            return new RestaurantItemViewModel[size];
        }
    };
    public final ObservableField<String> id = new ObservableField<>();
    public final ObservableField<Integer> favouriteCount = new ObservableField<>();
    public final ObservableField<Integer> isFavourite = new ObservableField<>();
    public final ObservableField<String> name = new ObservableField<>();
    public final ObservableField<String> address = new ObservableField<>();
    public final ObservableField<String> openStatus = new ObservableField<>();
    public final ObservableField<String> imageUrl = new ObservableField<>();
    public final ObservableField<ArrayList<String>> offers = new ObservableField<>();
    public final ObservableField<ArrayList<String>> foodTypes = new ObservableField<>();
    public final ObservableField<ArrayList<Category>> categorys = new ObservableField<>();


    public RestaurantItemViewModel(String id,
                                   int favouriteCount,
                                   int isFavourite,
                                   String name,
                                   String address,
                                   String openStatus,
                                   String imageUrl,
                                   ArrayList<String> offers,
                                   ArrayList<String> foodTypes,
                                   ArrayList<Category> categorys) {
        this.id.set(id);
        this.favouriteCount.set(favouriteCount);
        this.isFavourite.set(isFavourite);
        this.name.set(name);
        this.address.set(address);
        this.openStatus.set(openStatus);
        this.imageUrl.set(imageUrl);
        this.offers.set(offers);
        this.foodTypes.set(foodTypes);
        this.categorys.set(categorys);
    }

    protected RestaurantItemViewModel(Parcel in) {
        this.id.set(in.readString());
        this.favouriteCount.set((Integer) in.readValue(Integer.class.getClassLoader()));
        this.isFavourite.set((Integer) in.readValue(Integer.class.getClassLoader()));
        this.name.set(in.readString());
        this.address.set(in.readString());
        this.openStatus.set(in.readString());
        this.imageUrl.set(in.readString());
        this.offers.set(in.createStringArrayList());
        this.foodTypes.set(in.createStringArrayList());
        this.categorys.set(in.createTypedArrayList(Category.CREATOR));
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id.get());
        dest.writeValue(this.favouriteCount.get());
        dest.writeValue(this.isFavourite.get());
        dest.writeString(this.name.get());
        dest.writeString(this.address.get());
        dest.writeString(this.openStatus.get());
        dest.writeString(this.imageUrl.get());
        dest.writeStringList(this.offers.get());
        dest.writeStringList(this.foodTypes.get());
        dest.writeTypedList(this.categorys.get());
    }

}
