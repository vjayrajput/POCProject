package app.boilerplate.data.model.db;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "order_item")
public class OrderItem {

    @PrimaryKey
    public Long id;

    @ColumnInfo(name = "restaurant_id")
    public String restaurantId;


    @ColumnInfo(name = "categoryId")
    public String categoryId;


    @ColumnInfo(name = "itemId")
    public String itemId;


    @ColumnInfo(name = "quantity")
    public int quantity;

    @ColumnInfo(name = "price")
    public int price;
}
