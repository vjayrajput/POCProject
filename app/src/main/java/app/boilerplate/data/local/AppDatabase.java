package app.boilerplate.data.local;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import app.boilerplate.data.local.dao.OrderItemDao;
import app.boilerplate.data.model.db.OrderItem;


@Database(entities = {OrderItem.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract OrderItemDao orderItemDao();
}
