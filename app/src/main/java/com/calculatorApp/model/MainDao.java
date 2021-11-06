package com.calculatorApp.model;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import static androidx.room.OnConflictStrategy.REPLACE;

    @Dao
    public interface MainDao{
        @Query("SELECT * FROM maindata")
        List<MainData> getAll();

        @Insert
        void insertAll(MainData...mainData);

        @Delete
        void delete(MainData mainData);

    }

