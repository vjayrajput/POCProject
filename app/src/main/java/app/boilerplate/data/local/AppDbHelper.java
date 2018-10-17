package app.boilerplate.data.local;

import java.util.List;
import java.util.concurrent.Callable;

import javax.inject.Inject;
import javax.inject.Singleton;

import app.boilerplate.data.model.db.OrderItem;
import io.reactivex.Observable;

@Singleton
public class AppDbHelper implements DbHelper {

    private final AppDatabase mAppDatabase;

    @Inject
    public AppDbHelper(AppDatabase appDatabase) {
        this.mAppDatabase = appDatabase;
    }

    @Override
    public Observable<Boolean> saveOrderItem(OrderItem orderItem) {
        return Observable.fromCallable(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                mAppDatabase.orderItemDao().insert(orderItem);
                return true;
            }
        });
    }

    @Override
    public Observable<List<OrderItem>> getAllOrderItems() {
        return Observable.fromCallable(new Callable<List<OrderItem>>() {
            @Override
            public List<OrderItem> call() throws Exception {
                return mAppDatabase.orderItemDao().loadAll();
            }
        });
    }

    @Override
    public Observable<List<OrderItem>> getAllOrderItemsByIds(Long itemId) {
        return Observable.fromCallable(new Callable<List<OrderItem>>() {
            @Override
            public List<OrderItem> call() throws Exception {
                return mAppDatabase.orderItemDao().loadAllByIds(itemId);
            }
        });
    }

}
