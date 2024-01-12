package com.smtz.cvgenius.presentation.template;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\b\u0018\u0000  2\u00020\u00012\u00020\u0002:\u0001 B\u0005\u00a2\u0006\u0002\u0010\u0003J\u0012\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\u0014J\u0010\u0010\u0017\u001a\u00020\u00142\u0006\u0010\u0018\u001a\u00020\u0019H\u0016J\b\u0010\u001a\u001a\u00020\u0014H\u0002J\b\u0010\u001b\u001a\u00020\u0014H\u0002J\b\u0010\u001c\u001a\u00020\u0014H\u0002J\b\u0010\u001d\u001a\u00020\u0014H\u0002J\b\u0010\u001e\u001a\u00020\u0014H\u0002J\b\u0010\u001f\u001a\u00020\u0014H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\tX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\n0\tX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\n0\tX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u0006!"}, d2 = {"Lcom/smtz/cvgenius/presentation/template/SampleTemplateActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "Lcom/smtz/cvgenius/common/delegates/SampleTemplateDelegate;", "()V", "binding", "Lcom/smtz/cvgenius/databinding/ActivitySampleTemplateBinding;", "cvModel", "Lcom/smtz/cvgenius/domain/repository/CvModel;", "freeTemplateList", "", "Lcom/smtz/cvgenius/domain/model/TemplateVO;", "mBannerAdView", "Lcom/google/android/gms/ads/AdView;", "mSampleTemplateAdapter", "Lcom/smtz/cvgenius/presentation/template/SampleTemplateAdapter;", "secondTemplateList", "thirdTemplateList", "type", "", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "onTapSampleTemplate", "templateId", "", "setUpAdapters", "setUpListeners", "setUpMarginsAndPaddingAccordingToAndroidVersions", "setUpTabLayout", "setUpTemplates", "setUpToolBar", "Companion", "app_release"})
public final class SampleTemplateActivity extends androidx.appcompat.app.AppCompatActivity implements com.smtz.cvgenius.common.delegates.SampleTemplateDelegate {
    private com.smtz.cvgenius.databinding.ActivitySampleTemplateBinding binding;
    private java.lang.String type;
    @org.jetbrains.annotations.NotNull
    private final com.smtz.cvgenius.presentation.template.SampleTemplateAdapter mSampleTemplateAdapter = null;
    private com.google.android.gms.ads.AdView mBannerAdView;
    @org.jetbrains.annotations.NotNull
    private com.smtz.cvgenius.domain.repository.CvModel cvModel;
    @org.jetbrains.annotations.NotNull
    private java.util.List<com.smtz.cvgenius.domain.model.TemplateVO> freeTemplateList;
    @org.jetbrains.annotations.NotNull
    private java.util.List<com.smtz.cvgenius.domain.model.TemplateVO> secondTemplateList;
    @org.jetbrains.annotations.NotNull
    private java.util.List<com.smtz.cvgenius.domain.model.TemplateVO> thirdTemplateList;
    @org.jetbrains.annotations.NotNull
    private static final java.lang.String EXTRA_TYPE = "EXTRA TYPE";
    @org.jetbrains.annotations.NotNull
    public static final java.lang.String CREATE_NEW = "CREATE NEW";
    @org.jetbrains.annotations.NotNull
    public static final java.lang.String CHANGE_ID = "CHANGE ID";
    @org.jetbrains.annotations.NotNull
    public static final com.smtz.cvgenius.presentation.template.SampleTemplateActivity.Companion Companion = null;
    
    public SampleTemplateActivity() {
        super();
    }
    
    @java.lang.Override
    protected void onCreate(@org.jetbrains.annotations.Nullable
    android.os.Bundle savedInstanceState) {
    }
    
    private final void setUpToolBar() {
    }
    
    private final void setUpTabLayout() {
    }
    
    private final void setUpAdapters() {
    }
    
    private final void setUpListeners() {
    }
    
    private final void setUpTemplates() {
    }
    
    @java.lang.Override
    public void onTapSampleTemplate(int templateId) {
    }
    
    private final void setUpMarginsAndPaddingAccordingToAndroidVersions() {
    }
    
    @kotlin.Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0016\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0004R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\f"}, d2 = {"Lcom/smtz/cvgenius/presentation/template/SampleTemplateActivity$Companion;", "", "()V", "CHANGE_ID", "", "CREATE_NEW", "EXTRA_TYPE", "newIntent", "Landroid/content/Intent;", "context", "Landroid/content/Context;", "type", "app_release"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull
        public final android.content.Intent newIntent(@org.jetbrains.annotations.NotNull
        android.content.Context context, @org.jetbrains.annotations.NotNull
        java.lang.String type) {
            return null;
        }
    }
}