package com.example.mydiary.ui.diaryList;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import com.example.mydiary.R;
import com.example.mydiary.data.local.DiaryDatabaseHelper;
import com.example.mydiary.data.local.DiaryDbOpenHelper;

import java.text.DateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.schedulers.Schedulers;

public class DespalyDiary extends AppCompatActivity {

    DiaryDatabaseHelper mDiaryDatabaseHelper = new DiaryDatabaseHelper(new DiaryDbOpenHelper(this),
            Schedulers.io());


   // @BindView(R.id.d_date) TextView diaryDate;
    @BindView(R.id.d_title) EditText diaryTitle;
    @BindView(R.id.d_descrption) EditText diarydescrption;

    String diaryId,dateforupdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_despaly_diary);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        diaryId = intent.getStringExtra("diaryId");
        dateforupdate = intent.getStringExtra("diaryDate");
        diaryTitle.setText(intent.getStringExtra("diaryTitle"));
        diarydescrption.setText(intent.getStringExtra("diarydiscription"));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_desply_diary, menu);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        if(diaryId == null)
        {
            getSupportActionBar().setTitle("new diary");
            MenuItem itemDelete = menu.findItem(R.id.action_delete);
            MenuItem itemUpdate = menu.findItem(R.id.action_update);
            itemDelete.setVisible(false);
            itemUpdate.setVisible(false);
        }
        else
        {
            getSupportActionBar().setTitle("Edit Task");
            MenuItem itemcancle = menu.findItem(R.id.action_cancle);
            MenuItem itemSave = menu.findItem(R.id.action_save);
            itemcancle.setVisible(false);
            itemSave.setVisible(false);
        }

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        String currentDateTimeString = DateFormat.getDateInstance().format(new Date());
        switch (item.getItemId()) {

            case android.R.id.home:
                this.finish();

            case R.id.action_save:
                String descrption = String.valueOf(diarydescrption.getText());
                String title = String.valueOf(diaryTitle.getText());

                if(descrption.matches("") && title.matches(""))
                {
                    DespalyDiary.this.finish();
                }
                else if((descrption.matches("") && title != null))
                {
                    mDiaryDatabaseHelper.setDiary(currentDateTimeString,"No Title",descrption);
                    DespalyDiary.this.finish();
                }
                else
                {
                    mDiaryDatabaseHelper.setDiary(currentDateTimeString,title,descrption);
                    DespalyDiary.this.finish();

                }

            case R.id.action_cancle:
                DespalyDiary.this.finish();

            case R.id.action_delete:
                mDiaryDatabaseHelper.deleteDiary(diaryId);
                DespalyDiary.this.finish();

            case R.id.action_update:
                mDiaryDatabaseHelper.updateDiary(diaryId, dateforupdate,String.valueOf(diaryTitle.getText()), String.valueOf(diarydescrption.getText()));
                DespalyDiary.this.finish();
        }
        return true;
    }
}
