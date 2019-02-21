package com.example.mydiary.data.local;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import com.squareup.sqlbrite2.BriteDatabase;
import com.squareup.sqlbrite2.SqlBrite;

import java.util.Collection;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Scheduler;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import retrofit2.http.Query;

import com.example.mydiary.data.model.diary;

import io.reactivex.Scheduler;

public class DiaryDatabaseHelper {

    private final BriteDatabase mDb;
    DiaryDbOpenHelper diaryDbOpenHelper;

    public DiaryDatabaseHelper(DiaryDbOpenHelper diaryDbOpenHelper, Scheduler scheduler) {
        SqlBrite.Builder sqlBrite = new SqlBrite.Builder();
        mDb = (BriteDatabase) sqlBrite.build().wrapDatabaseHelper(diaryDbOpenHelper, scheduler);
    }

    public BriteDatabase getBriteDb() {
        return mDb;
    }

    public void setDiary(String date, String title, String description) {
        BriteDatabase.Transaction transaction = mDb.newTransaction();
        try {

            mDb.insert(DiaryDB.Tabel_Name, DiaryDB.toContentValues(date, title, description), SQLiteDatabase.CONFLICT_NONE);
            transaction.markSuccessful();
        } finally {
            transaction.end();
        }
    }

    public Observable<List<diary>> getDiary() {
        return mDb.createQuery(DiaryDB.Tabel_Name,
                "SELECT * FROM " + DiaryDB.Tabel_Name).mapToList(new Function<Cursor, diary>() {
            @Override
            public diary apply(@NonNull Cursor cursor) throws Exception {
                return DiaryDB.parseCursor(cursor);
            }
        });
    }

    public void deleteDiary(String id) {
        BriteDatabase.Transaction transaction = mDb.newTransaction();
        try {
            mDb.delete(DiaryDB.Tabel_Name, DiaryDB.Columon_Diary_ID + " =?" ,new String[]{id});
            transaction.markSuccessful();
        }finally {
            transaction.end();
        }

    }
    public void  updateDiary(String id, String date, String upttitle, String uptDescription){
        BriteDatabase.Transaction transaction = mDb.newTransaction();
        try {
            mDb.update(DiaryDB.Tabel_Name, DiaryDB.toContentValues(date,upttitle, uptDescription), SQLiteDatabase.CONFLICT_NONE,
                                DiaryDB.Columon_Diary_ID + " =?", new String[]{id});
            transaction.markSuccessful();
        }
        finally {
            transaction.end();
        }
    }
}
