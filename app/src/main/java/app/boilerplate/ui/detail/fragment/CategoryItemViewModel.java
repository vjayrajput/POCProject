package app.boilerplate.ui.detail.fragment;

import android.databinding.ObservableField;
import android.os.Parcel;
import android.os.Parcelable;

public class CategoryItemViewModel implements Parcelable {

    public static final Creator<CategoryItemViewModel> CREATOR = new Creator<CategoryItemViewModel>() {
        @Override
        public CategoryItemViewModel createFromParcel(Parcel source) {
            return new CategoryItemViewModel(source);
        }

        @Override
        public CategoryItemViewModel[] newArray(int size) {
            return new CategoryItemViewModel[size];
        }
    };

    public final ObservableField<String> id = new ObservableField<>();
    public final ObservableField<String> name = new ObservableField<>();
    public final ObservableField<String> description = new ObservableField<>();
    public final ObservableField<String> displayPrice = new ObservableField<>();
    public final ObservableField<Integer> price = new ObservableField<>();
    public final ObservableField<Integer> quantity = new ObservableField<>();
    public final ObservableField<Integer> maxQuantity = new ObservableField<>();
    public final ObservableField<Integer> type = new ObservableField<>();


    public CategoryItemViewModel(String id,
                                 String name,
                                 String description,
                                 String displayPrice,
                                 int price,
                                 int quantity,
                                 int maxQuantity,
                                 int type) {
        this.id.set(id);
        this.name.set(name);
        this.description.set(description);
        this.displayPrice.set(displayPrice);
        this.price.set(price);
        this.quantity.set(quantity);
        this.maxQuantity.set(maxQuantity);
        this.type.set(type);
    }

    protected CategoryItemViewModel(Parcel in) {
        this.id.set(in.readString());
        this.name.set(in.readString());
        this.description.set(in.readString());
        this.displayPrice.set(in.readString());
        this.price.set((Integer) in.readValue(Integer.class.getClassLoader()));
        this.quantity.set((Integer) in.readValue(Integer.class.getClassLoader()));
        this.maxQuantity.set((Integer) in.readValue(Integer.class.getClassLoader()));
        this.type.set((Integer) in.readValue(Integer.class.getClassLoader()));
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id.get());
        dest.writeString(this.name.get());
        dest.writeString(this.description.get());
        dest.writeString(this.displayPrice.get());
        dest.writeValue(this.price.get());
        dest.writeValue(this.quantity.get());
        dest.writeValue(this.maxQuantity.get());
        dest.writeValue(this.type.get());
    }

}
