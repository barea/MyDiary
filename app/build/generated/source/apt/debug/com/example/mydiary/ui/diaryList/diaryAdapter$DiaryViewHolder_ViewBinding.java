// Generated code from Butter Knife. Do not modify!
package com.example.mydiary.ui.diaryList;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.example.mydiary.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class diaryAdapter$DiaryViewHolder_ViewBinding implements Unbinder {
  private diaryAdapter.DiaryViewHolder target;

  @UiThread
  public diaryAdapter$DiaryViewHolder_ViewBinding(diaryAdapter.DiaryViewHolder target,
      View source) {
    this.target = target;

    target.id = Utils.findRequiredViewAsType(source, R.id.diary_id, "field 'id'", TextView.class);
    target.date = Utils.findRequiredViewAsType(source, R.id.diary_date, "field 'date'", TextView.class);
    target.title = Utils.findRequiredViewAsType(source, R.id.diary_title, "field 'title'", TextView.class);
    target.discripton = Utils.findRequiredViewAsType(source, R.id.diary_descrption, "field 'discripton'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    diaryAdapter.DiaryViewHolder target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.id = null;
    target.date = null;
    target.title = null;
    target.discripton = null;
  }
}
