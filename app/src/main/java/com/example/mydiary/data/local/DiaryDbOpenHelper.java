package com.example.mydiary.data.local;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DiaryDbOpenHelper extends SQLiteOpenHelper {

    public static final String Database_NAME = "diarybook.db";
    public static final int DATABASE_VERSION = 1;

    public DiaryDbOpenHelper(Context context) {
        super(context, Database_NAME, null, DATABASE_VERSION );
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.beginTransaction();
        try {
            db.execSQL(DiaryDB.DiaryTable.create);
            db.setTransactionSuccessful();
        }
        finally {
            db.endTransaction();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
