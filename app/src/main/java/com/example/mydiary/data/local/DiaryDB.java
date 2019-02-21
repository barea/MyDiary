package com.example.mydiary.data.local;

import android.content.ContentValues;
import android.database.Cursor;
import android.provider.FontsContract;

import com.example.mydiary.data.model.diary;

public class DiaryDB {

    public static final String Tabel_Name  = "diaries";
    public static final String Columon_Diary_ID = "id";
    public static final String Columon_Diary_Date = "date";
    public static final String Columon_Title = "diary_title";
    public static final String Columon_Discription = "diary_discription";

    DiaryDB(){};

    public abstract static class DiaryTable{


        public static final String create = "CREATE TABLE " + Tabel_Name + " ("+
                Columon_Diary_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
                Columon_Diary_Date +" TEXT NOT NULL, "+
                Columon_Title +" TEXT NOT NULL, "+
                Columon_Discription+" TEXT NOT NULL"+
                " ); ";

    }

    public static ContentValues toContentValues(String date, String title, String description ){

        ContentValues values = new ContentValues();
        values.put(Columon_Diary_Date, date);
        values.put(Columon_Title, title);
        values.put(Columon_Discription, description);
        return values;
    }

    public static diary parseCursor(Cursor cursor){

        return diary.Create(
                cursor.getInt(cursor.getColumnIndexOrThrow(Columon_Diary_ID)),
                cursor.getString(cursor.getColumnIndexOrThrow(Columon_Diary_Date)),
                cursor.getString(cursor.getColumnIndexOrThrow(Columon_Title)),
                cursor.getString(cursor.getColumnIndexOrThrow(Columon_Discription)));

    }
}
