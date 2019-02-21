package com.example.mydiary.ui.diaryList;

import android.content.Context;
import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.design.widget.FloatingActionButton;
import android.view.Menu;
import android.view.View;

import com.example.mydiary.R;
import com.example.mydiary.data.local.DiaryDatabaseHelper;
import com.example.mydiary.data.local.DiaryDbOpenHelper;
import com.example.mydiary.data.model.diary;
import com.squareup.sqlbrite2.BriteDatabase;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    DiaryDatabaseHelper mDiaryDatabaseHelper = new DiaryDatabaseHelper(new DiaryDbOpenHelper(this),
            Schedulers.io());

    diaryAdapter mDiaryAdabter = new diaryAdapter();
    private Context context;
    private BriteDatabase mDb ;

    @BindView(R.id.recycler_view) RecyclerView mRecyclerView;
    @BindView(R.id.coordinator_layout)
    CoordinatorLayout coordinatorLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mRecyclerView.setAdapter(mDiaryAdabter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        load();

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, DespalyDiary.class);
                i.putExtra("add diary", "add" );
                MainActivity.this.startActivity(i);
            }
        });
    }

    @Override
    public void onResume(){
        super.onResume();
        load();
    }

    public void load(){

        mDiaryDatabaseHelper.getDiary()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<List<diary>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<diary> diarys) {
                        mDiaryAdabter.setDiary(diarys);
                        mDiaryAdabter.notifyDataSetChanged();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getSupportActionBar().setTitle("My diary list");
        return super.onCreateOptionsMenu(menu);
    }
}
