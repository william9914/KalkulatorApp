package com.calculatorApp.model;
import android.content.Context;
import android.content.MutableContextWrapper;

import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import androidx.lifecycle.MutableLiveData;
import androidx.room.ColumnInfo;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {MainData.class},version = 1,exportSchema = false)

public abstract class RoomDB extends RoomDatabase {
    private static RoomDB sInstance;
    private final MutableLiveData<Boolean> mIsDatabaseCreated = new MutableLiveData<>();

    @VisibleForTesting
    private static final String DATABASE_NAME = "database";

    public abstract MainDao mainDao();

    private void setDatabaseCreated(){
        mIsDatabaseCreated.postValue(true);
    }

    private void updateDatabaseCreated(final Context context){
        if (context.getDatabasePath(DATABASE_NAME).exists()){
            setDatabaseCreated();
        }
    }

    public static RoomDB buildDatabase(final Context context){
        return Room.databaseBuilder(context, RoomDB.class, DATABASE_NAME)
                .addCallback(new Callback() {
                    @Override
                    public void onCreate(@NonNull SupportSQLiteDatabase db) {
                        super.onCreate(db);
                        RoomDB database = RoomDB.getInstance(context);
                        database.setDatabaseCreated();
                    }
                }).allowMainThreadQueries().fallbackToDestructiveMigration().build();
    }

    public static RoomDB getInstance(final Context context){
        if (sInstance == null){
            synchronized (RoomDB.class){
                if (sInstance == null){
                    sInstance = buildDatabase(context);
                    sInstance.updateDatabaseCreated(context.getApplicationContext());
                }
            }
        }
        return sInstance;
    }

}
