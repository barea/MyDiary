package com.example.mydiary.ui.diaryList;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mydiary.R;
import com.example.mydiary.data.model.diary;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class diaryAdapter extends RecyclerView.Adapter<diaryAdapter.DiaryViewHolder>{

    private List<diary> mDiary;
    private Context context;

    public diaryAdapter(){
        mDiary = new ArrayList<>();
    }

    public void setDiary(List<diary> diary) {
        mDiary = diary;
    }

    @Override
    public int getItemCount() {
        return mDiary.size();
    }

    @Override
    public DiaryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.diary_item, parent, false);
        return new DiaryViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final DiaryViewHolder holder, int position) {
        final diary diary = mDiary.get(position);
        holder.id.setText(String.valueOf(diary.id()));
        holder.id.setVisibility(View.GONE);
        holder.date.setText(diary.timestamp());
        holder.title.setText(diary.title());
        holder.discripton.setText(diary.description());
        holder.discripton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, DespalyDiary.class);
                i.putExtra("diaryId", holder.id.getText());
                i.putExtra("diaryDate", holder.date.getText());
                i.putExtra("diaryTitle",holder.title.getText());
                i.putExtra("diarydiscription",holder.discripton.getText());
                context.startActivity(i);
            }
        });
    }

    class DiaryViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.diary_id) TextView id;
        @BindView(R.id.diary_date) TextView date;
        @BindView(R.id.diary_title) TextView title;
        @BindView(R.id.diary_descrption) TextView discripton;

        public DiaryViewHolder(View itemView) {
            super(itemView);
            context = itemView.getContext();
            ButterKnife.bind(this, itemView);
        }
    }

}
