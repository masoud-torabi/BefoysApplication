package com.befoys.core.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.befoys.core.configs.Constants;
import com.befoys.core.models.Driver;
import com.befoys.core.models.SiteUser;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;
import java.util.List;

public class DatabaseContext<T> extends OrmLiteSqliteOpenHelper {
    // Fields

    public static final String DB_NAME = Constants.DATABASE_NAME;
    private static final int DB_VERSION = 1;

    // Public methods
    public DatabaseContext(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db, ConnectionSource cs) {
        try {
            // Create Table with given table name with columnName
            TableUtils.createTable(cs, Driver.class);
            TableUtils.createTable(cs, SiteUser.class);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, ConnectionSource cs, int oldVersion, int newVersion) {

    }

    public List getAll(Class clazz) throws SQLException {
        Dao<T, ?> dao = getDao(clazz);
        return dao.queryForAll();
    }

    public  T getById(Class clazz, Object aId) throws SQLException {
        Dao<T, Object> dao = getDao(clazz);
        return dao.queryForId(aId);
    }

    public Dao.CreateOrUpdateStatus createOrUpdate(T obj) throws SQLException {
        Dao<T, ?> dao = (Dao<T, ?>) getDao(obj.getClass());
        return dao.createOrUpdate(obj);
    }

    public  int deleteById(Class clazz, Object aId) throws SQLException {
        Dao<T, Object> dao = getDao(clazz);
        return dao.deleteById(aId);
    }

    public  int deleteAll(Class clazz, List<T> list) throws SQLException {
        Dao<T, Object> dao = getDao(clazz);
        return dao.delete(list);
    }
}
