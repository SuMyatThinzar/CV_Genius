package com.smtz.cvgenius.presentation.details.educationDetails;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\u0018\u0000 \'2\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003:\u0001\'B\u0005\u00a2\u0006\u0002\u0010\u0004J\u0012\u0010\u001b\u001a\u00020\u001c2\b\u0010\u001d\u001a\u0004\u0018\u00010\u001eH\u0014J\u0010\u0010\u001f\u001a\u00020\u001c2\u0006\u0010 \u001a\u00020\u0006H\u0016J\u0010\u0010!\u001a\u00020\u001c2\u0006\u0010\"\u001a\u00020\u0018H\u0016J\b\u0010#\u001a\u00020\u001cH\u0002J\b\u0010$\u001a\u00020\u001cH\u0002J\b\u0010%\u001a\u00020\u001cH\u0002J\b\u0010&\u001a\u00020\u001cH\u0002R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082D\u00a2\u0006\u0002\n\u0000R\u001b\u0010\u0007\u001a\u00020\u00028VX\u0096\u0084\u0002\u00a2\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\b\u0010\tR\u000e\u0010\f\u001a\u00020\rX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082.\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00180\u0017X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u001aX\u0082D\u00a2\u0006\u0002\n\u0000\u00a8\u0006("}, d2 = {"Lcom/smtz/cvgenius/presentation/details/educationDetails/EducationDetailActivity;", "Lcom/smtz/cvgenius/core/BaseActivity;", "Lcom/smtz/cvgenius/databinding/ActivityEducationDetailBinding;", "Lcom/smtz/cvgenius/presentation/details/educationDetails/ButtonSaveEducationDelegate;", "()V", "ANIMATION_DURATION", "", "binding", "getBinding", "()Lcom/smtz/cvgenius/databinding/ActivityEducationDetailBinding;", "binding$delegate", "Lkotlin/Lazy;", "isExpanded", "", "mCvModel", "Lcom/smtz/cvgenius/domain/repository/CvModel;", "mCvVO", "Lcom/smtz/cvgenius/domain/model/CvVO;", "mEducationDetailAdapter", "Lcom/smtz/cvgenius/presentation/details/educationDetails/AddEducationDetailsAdapter;", "mEducationDetailViewPod", "Lcom/smtz/cvgenius/presentation/details/educationDetails/EducationDetailViewPod;", "mEducationList", "", "Lcom/smtz/cvgenius/domain/model/EducationDetailVO;", "mToolbarHeight", "", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "onTapDelete", "id", "onTapSaveEducation", "educationDetailVO", "setUpAdapters", "setUpData", "setUpListeners", "setUpViewPods", "Companion", "app_debug"})
public final class EducationDetailActivity extends com.smtz.cvgenius.core.BaseActivity<com.smtz.cvgenius.databinding.ActivityEducationDetailBinding> implements com.smtz.cvgenius.presentation.details.educationDetails.ButtonSaveEducationDelegate {
    private final long ANIMATION_DURATION = 250L;
    private final int mToolbarHeight = 330;
    private boolean isExpanded = true;
    @org.jetbrains.annotations.NotNull
    private com.smtz.cvgenius.presentation.details.educationDetails.AddEducationDetailsAdapter mEducationDetailAdapter;
    private com.smtz.cvgenius.presentation.details.educationDetails.EducationDetailViewPod mEducationDetailViewPod;
    @org.jetbrains.annotations.NotNull
    private com.smtz.cvgenius.domain.repository.CvModel mCvModel;
    @org.jetbrains.annotations.Nullable
    private com.smtz.cvgenius.domain.model.CvVO mCvVO;
    @org.jetbrains.annotations.NotNull
    private java.util.List<com.smtz.cvgenius.domain.model.EducationDetailVO> mEducationList;
    @org.jetbrains.annotations.NotNull
    private final kotlin.Lazy binding$delegate = null;
    @org.jetbrains.annotations.NotNull
    public static final com.smtz.cvgenius.presentation.details.educationDetails.EducationDetailActivity.Companion Companion = null;
    
    public EducationDetailActivity() {
        super();
    }
    
    @java.lang.Override
    @org.jetbrains.annotations.NotNull
    public com.smtz.cvgenius.databinding.ActivityEducationDetailBinding getBinding() {
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
    public void onTapSaveEducation(@org.jetbrains.annotations.NotNull
    com.smtz.cvgenius.domain.model.EducationDetailVO educationDetailVO) {
    }
    
    @java.lang.Override
    public void onTapDelete(long id) {
    }
    
    @kotlin.Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006\u00a8\u0006\u0007"}, d2 = {"Lcom/smtz/cvgenius/presentation/details/educationDetails/EducationDetailActivity$Companion;", "", "()V", "newIntent", "Landroid/content/Intent;", "context", "Landroid/content/Context;", "app_debug"})
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