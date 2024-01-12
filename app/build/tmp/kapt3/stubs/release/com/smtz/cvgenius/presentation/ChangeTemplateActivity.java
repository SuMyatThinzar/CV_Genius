package com.smtz.cvgenius.presentation;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0002\u0018\u0000 \u00182\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\u0018B\u0005\u00a2\u0006\u0002\u0010\u0003J\u0012\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0014J\b\u0010\u0013\u001a\u00020\u0010H\u0014J\b\u0010\u0014\u001a\u00020\u0010H\u0002J\u0010\u0010\u0015\u001a\u00020\u00102\u0006\u0010\u0016\u001a\u00020\u0017H\u0002R\u001b\u0010\u0004\u001a\u00020\u00028VX\u0096\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006R\u000e\u0010\t\u001a\u00020\nX\u0082.\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0019"}, d2 = {"Lcom/smtz/cvgenius/presentation/ChangeTemplateActivity;", "Lcom/smtz/cvgenius/core/BaseActivity;", "Lcom/smtz/cvgenius/databinding/ActivityChangeTemplateBinding;", "()V", "binding", "getBinding", "()Lcom/smtz/cvgenius/databinding/ActivityChangeTemplateBinding;", "binding$delegate", "Lkotlin/Lazy;", "mBannerAdView", "Lcom/google/android/gms/ads/AdView;", "mCvVO", "Lcom/smtz/cvgenius/domain/model/CvVO;", "mTemplateViewPod", "Lcom/smtz/cvgenius/presentation/preview/templateViewPods/BaseViewPod;", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "onPause", "setUpTemplate", "setUpViewPod", "resumeId", "", "Companion", "app_release"})
public final class ChangeTemplateActivity extends com.smtz.cvgenius.core.BaseActivity<com.smtz.cvgenius.databinding.ActivityChangeTemplateBinding> {
    @org.jetbrains.annotations.Nullable
    private com.smtz.cvgenius.domain.model.CvVO mCvVO;
    private com.smtz.cvgenius.presentation.preview.templateViewPods.BaseViewPod mTemplateViewPod;
    private com.google.android.gms.ads.AdView mBannerAdView;
    @org.jetbrains.annotations.NotNull
    private final kotlin.Lazy binding$delegate = null;
    @org.jetbrains.annotations.NotNull
    public static final com.smtz.cvgenius.presentation.ChangeTemplateActivity.Companion Companion = null;
    
    public ChangeTemplateActivity() {
        super();
    }
    
    @java.lang.Override
    @org.jetbrains.annotations.NotNull
    public com.smtz.cvgenius.databinding.ActivityChangeTemplateBinding getBinding() {
        return null;
    }
    
    @java.lang.Override
    protected void onCreate(@org.jetbrains.annotations.Nullable
    android.os.Bundle savedInstanceState) {
    }
    
    private final void setUpTemplate() {
    }
    
    private final void setUpViewPod(int resumeId) {
    }
    
    @java.lang.Override
    protected void onPause() {
    }
    
    @kotlin.Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006\u00a8\u0006\u0007"}, d2 = {"Lcom/smtz/cvgenius/presentation/ChangeTemplateActivity$Companion;", "", "()V", "newIntent", "Landroid/content/Intent;", "context", "Landroid/content/Context;", "app_release"})
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