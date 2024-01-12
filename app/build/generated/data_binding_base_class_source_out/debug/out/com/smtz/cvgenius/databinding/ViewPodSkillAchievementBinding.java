// Generated by view binder compiler. Do not edit!
package com.smtz.cvgenius.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.material.textfield.TextInputEditText;
import com.smtz.cvgenius.R;
import com.smtz.cvgenius.presentation.details.SkillAchievementViewPod;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;
import soup.neumorphism.NeumorphButton;

public final class ViewPodSkillAchievementBinding implements ViewBinding {
  @NonNull
  private final SkillAchievementViewPod rootView;

  @NonNull
  public final ImageView btnDelete;

  @NonNull
  public final NeumorphButton btnSave;

  @NonNull
  public final RelativeLayout containerCollapsed;

  @NonNull
  public final LinearLayout containerExpanded;

  @NonNull
  public final TextView error;

  @NonNull
  public final TextInputEditText etTitle;

  @NonNull
  public final TextView tvDetailNameCollapsed;

  @NonNull
  public final TextView tvDetailNameExpanded;

  @NonNull
  public final TextView tvTitle;

  private ViewPodSkillAchievementBinding(@NonNull SkillAchievementViewPod rootView,
      @NonNull ImageView btnDelete, @NonNull NeumorphButton btnSave,
      @NonNull RelativeLayout containerCollapsed, @NonNull LinearLayout containerExpanded,
      @NonNull TextView error, @NonNull TextInputEditText etTitle,
      @NonNull TextView tvDetailNameCollapsed, @NonNull TextView tvDetailNameExpanded,
      @NonNull TextView tvTitle) {
    this.rootView = rootView;
    this.btnDelete = btnDelete;
    this.btnSave = btnSave;
    this.containerCollapsed = containerCollapsed;
    this.containerExpanded = containerExpanded;
    this.error = error;
    this.etTitle = etTitle;
    this.tvDetailNameCollapsed = tvDetailNameCollapsed;
    this.tvDetailNameExpanded = tvDetailNameExpanded;
    this.tvTitle = tvTitle;
  }

  @Override
  @NonNull
  public SkillAchievementViewPod getRoot() {
    return rootView;
  }

  @NonNull
  public static ViewPodSkillAchievementBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ViewPodSkillAchievementBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.view_pod_skill_achievement, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ViewPodSkillAchievementBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.btnDelete;
      ImageView btnDelete = ViewBindings.findChildViewById(rootView, id);
      if (btnDelete == null) {
        break missingId;
      }

      id = R.id.btnSave;
      NeumorphButton btnSave = ViewBindings.findChildViewById(rootView, id);
      if (btnSave == null) {
        break missingId;
      }

      id = R.id.containerCollapsed;
      RelativeLayout containerCollapsed = ViewBindings.findChildViewById(rootView, id);
      if (containerCollapsed == null) {
        break missingId;
      }

      id = R.id.containerExpanded;
      LinearLayout containerExpanded = ViewBindings.findChildViewById(rootView, id);
      if (containerExpanded == null) {
        break missingId;
      }

      id = R.id.error;
      TextView error = ViewBindings.findChildViewById(rootView, id);
      if (error == null) {
        break missingId;
      }

      id = R.id.etTitle;
      TextInputEditText etTitle = ViewBindings.findChildViewById(rootView, id);
      if (etTitle == null) {
        break missingId;
      }

      id = R.id.tvDetailNameCollapsed;
      TextView tvDetailNameCollapsed = ViewBindings.findChildViewById(rootView, id);
      if (tvDetailNameCollapsed == null) {
        break missingId;
      }

      id = R.id.tvDetailNameExpanded;
      TextView tvDetailNameExpanded = ViewBindings.findChildViewById(rootView, id);
      if (tvDetailNameExpanded == null) {
        break missingId;
      }

      id = R.id.tvTitle;
      TextView tvTitle = ViewBindings.findChildViewById(rootView, id);
      if (tvTitle == null) {
        break missingId;
      }

      return new ViewPodSkillAchievementBinding((SkillAchievementViewPod) rootView, btnDelete,
          btnSave, containerCollapsed, containerExpanded, error, etTitle, tvDetailNameCollapsed,
          tvDetailNameExpanded, tvTitle);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}