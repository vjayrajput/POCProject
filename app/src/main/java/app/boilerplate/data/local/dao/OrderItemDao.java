package app.boilerplate.data.local.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import app.boilerplate.data.model.db.OrderItem;

@Dao
public interface OrderItemDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(OrderItem orderItem);

    @Query("SELECT * FROM order_item")
    List<OrderItem> loadAll();

    @Query("SELECT * FROM order_item WHERE itemId IN (:itemIds)")
    List<OrderItem> loadAllByIds(Long itemIds);

}
