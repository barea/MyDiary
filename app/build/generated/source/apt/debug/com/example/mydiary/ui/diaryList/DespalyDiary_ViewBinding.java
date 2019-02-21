// Generated code from Butter Knife. Do not modify!
package com.example.mydiary.ui.diaryList;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.EditText;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.example.mydiary.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class DespalyDiary_ViewBinding implements Unbinder {
  private DespalyDiary target;

  @UiThread
  public DespalyDiary_ViewBinding(DespalyDiary target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public DespalyDiary_ViewBinding(DespalyDiary target, View source) {
    this.target = target;

    target.diaryTitle = Utils.findRequiredViewAsType(source, R.id.d_title, "field 'diaryTitle'", EditText.class);
    target.diarydescrption = Utils.findRequiredViewAsType(source, R.id.d_descrption, "field 'diarydescrption'", EditText.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    DespalyDiary target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.diaryTitle = null;
    target.diarydescrption = null;
  }
}
