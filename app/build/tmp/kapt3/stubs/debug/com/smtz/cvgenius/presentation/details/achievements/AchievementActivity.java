package com.smtz.cvgenius.presentation.details.achievements;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\u0000h\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u0000 +2\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003:\u0001+B\u0005\u00a2\u0006\u0002\u0010\u0004J\u0012\u0010\u001b\u001a\u00020\u001c2\b\u0010\u001d\u001a\u0004\u0018\u00010\u001eH\u0014J\u0018\u0010\u001f\u001a\u00020\u001c2\u0006\u0010 \u001a\u00020\u00062\u0006\u0010!\u001a\u00020\"H\u0016J\u001c\u0010#\u001a\u00020\u001c2\b\u0010$\u001a\u0004\u0018\u00010%2\b\u0010&\u001a\u0004\u0018\u00010\u0010H\u0016J\b\u0010\'\u001a\u00020\u001cH\u0002J\b\u0010(\u001a\u00020\u001cH\u0002J\b\u0010)\u001a\u00020\u001cH\u0002J\b\u0010*\u001a\u00020\u001cH\u0002R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082D\u00a2\u0006\u0002\n\u0000R\u001b\u0010\u0007\u001a\u00020\u00028VX\u0096\u0084\u0002\u00a2\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\b\u0010\tR\u000e\u0010\f\u001a\u00020\rX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0016X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0017\u001a\u0004\u0018\u00010\u0018X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u001aX\u0082D\u00a2\u0006\u0002\n\u0000\u00a8\u0006,"}, d2 = {"Lcom/smtz/cvgenius/presentation/details/achievements/AchievementActivity;", "Lcom/smtz/cvgenius/core/BaseActivity;", "Lcom/smtz/cvgenius/databinding/ActivityAchievementBinding;", "Lcom/smtz/cvgenius/presentation/details/ButtonSaveSkillAchievementDelegate;", "()V", "ANIMATION_DURATION", "", "binding", "getBinding", "()Lcom/smtz/cvgenius/databinding/ActivityAchievementBinding;", "binding$delegate", "Lkotlin/Lazy;", "isExpanded", "", "mAchievementList", "", "Lcom/smtz/cvgenius/domain/model/AchievementVO;", "mAchievementViewPod", "Lcom/smtz/cvgenius/presentation/details/SkillAchievementViewPod;", "mAddDetailAdapter", "Lcom/smtz/cvgenius/presentation/details/AddSkillAchievementAdapter;", "mCvModel", "Lcom/smtz/cvgenius/domain/repository/CvModel;", "mCvVO", "Lcom/smtz/cvgenius/domain/model/CvVO;", "mToolbarHeight", "", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "onTapDelete", "id", "skillOrAchievement", "", "onTapSave", "skillsVO", "Lcom/smtz/cvgenius/domain/model/SkillsVO;", "achievementVO", "setUpAdapters", "setUpData", "setUpListeners", "setUpViewPods", "Companion", "app_debug"})
public final class AchievementActivity extends com.smtz.cvgenius.core.BaseActivity<com.smtz.cvgenius.databinding.ActivityAchievementBinding> implements com.smtz.cvgenius.presentation.details.ButtonSaveSkillAchievementDelegate {
    private final long ANIMATION_DURATION = 250L;
    private final int mToolbarHeight = 330;
    private boolean isExpanded = true;
    @org.jetbrains.annotations.NotNull
    private com.smtz.cvgenius.presentation.details.AddSkillAchievementAdapter mAddDetailAdapter;
    private com.smtz.cvgenius.presentation.details.SkillAchievementViewPod mAchievementViewPod;
    @org.jetbrains.annotations.NotNull
    private com.smtz.cvgenius.domain.repository.CvModel mCvModel;
    @org.jetbrains.annotations.Nullable
    private com.smtz.cvgenius.domain.model.CvVO mCvVO;
    @org.jetbrains.annotations.NotNull
    private java.util.List<com.smtz.cvgenius.domain.model.AchievementVO> mAchievementList;
    @org.jetbrains.annotations.NotNull
    private final kotlin.Lazy binding$delegate = null;
    @org.jetbrains.annotations.NotNull
    public static final com.smtz.cvgenius.presentation.details.achievements.AchievementActivity.Companion Companion = null;
    
    public AchievementActivity() {
        super();
    }
    
    @java.lang.Override
    @org.jetbrains.annotations.NotNull
    public com.smtz.cvgenius.databinding.ActivityAchievementBinding getBinding() {
        return null;
    }
    
    @java.lang.Override
    protected void onCreate(@org.jetbrains.annotations.Nullable
    android.os.Bundle savedInstanceState) {
    }
    
    private final void setUpData() {
    }
    
    private final void setUpViewPods() {
    }
    
    private final void setUpListeners() {
    }
    
    private final void setUpAdapters() {
    }
    
    @java.lang.Override
    public void onTapSave(@org.jetbrains.annotations.Nullable
    com.smtz.cvgenius.domain.model.SkillsVO skillsVO, @org.jetbrains.annotations.Nullable
    com.smtz.cvgenius.domain.model.AchievementVO achievementVO) {
    }
    
    @java.lang.Override
    public void onTapDelete(long id, @org.jetbrains.annotations.NotNull
    java.lang.String skillOrAchievement) {
    }
    
    @kotlin.Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006\u00a8\u0006\u0007"}, d2 = {"Lcom/smtz/cvgenius/presentation/details/achievements/AchievementActivity$Companion;", "", "()V", "newIntent", "Landroid/content/Intent;", "context", "Landroid/content/Context;", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull
        public final android.content.Intent newIntent(@org.jetbrains.annotations.NotNull
        android.content.Context context) {
            return null;
        }
    }
}