package app.akeed.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import app.akeed.BR;

public class Item extends BaseObservable implements Parcelable {
    public static final Creator<Item> CREATOR = new Creator<Item>() {
        @Override
        public Item createFromParcel(Parcel source) {
            return new Item(source);
        }

        @Override
        public Item[] newArray(int size) {
            return new Item[size];
        }
    };
    @SerializedName("id")
    private String id;
    @SerializedName("name")
    private String name;
    @SerializedName("display_price")
    private String displayPrice;
    @SerializedName("price")
    private Integer price;
    @SerializedName("quantity")
    private Integer quantity;
    @SerializedName("max_quantity")
    private Integer maxQuantity;
    @SerializedName("type")
    private Integer type;
    @SerializedName("description")
    private String description;

    public Item() {
    }

    protected Item(Parcel in) {
        this.id = in.readString();
        this.name = in.readString();
        this.displayPrice = in.readString();
        this.price = (Integer) in.readValue(Integer.class.getClassLoader());
        this.quantity = (Integer) in.readValue(Integer.class.getClassLoader());
        this.maxQuantity = (Integer) in.readValue(Integer.class.getClassLoader());
        this.type = (Integer) in.readValue(Integer.class.getClassLoader());
        this.description = in.readString();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Bindable
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Bindable
    public String getDisplayPrice() {
        return displayPrice;
    }

    public void setDisplayPrice(String displayPrice) {
        this.displayPrice = displayPrice;
    }

    @Bindable
    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    @Bindable
    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
        notifyPropertyChanged(BR.quantity);
    }

    @Bindable
    public Integer getMaxQuantity() {
        return maxQuantity;
    }

    public void setMaxQuantity(Integer maxQuantity) {
        this.maxQuantity = maxQuantity;
    }

    @Bindable
    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @Bindable
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.name);
        dest.writeString(this.displayPrice);
        dest.writeValue(this.price);
        dest.writeValue(this.quantity);
        dest.writeValue(this.maxQuantity);
        dest.writeValue(this.type);
        dest.writeString(this.description);
    }

    @Override
    public String toString() {
        return "Item{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", displayPrice='" + displayPrice + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                ", maxQuantity=" + maxQuantity +
                ", type=" + type +
                ", description='" + description + '\'' +
                '}';
    }
}
