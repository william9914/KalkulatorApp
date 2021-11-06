package com.calculatorApp.model;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class MainData{

    @PrimaryKey(autoGenerate = true)
    public  int id;

    public String workingDb;
    public String resultDb;

}
