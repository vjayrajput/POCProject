package app.boilerplate.data.local;

import java.util.List;

import app.boilerplate.data.model.db.OrderItem;
import io.reactivex.Observable;


public interface DbHelper {

    Observable<Boolean> saveOrderItem(OrderItem orderItem);

    Observable<List<OrderItem>> getAllOrderItems();

    Observable<List<OrderItem>> getAllOrderItemsByIds(Long itemId);
}
