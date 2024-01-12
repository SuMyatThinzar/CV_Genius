package com.smtz.cvgenius.presentation.details.reference;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003B\u0005\u00a2\u0006\u0002\u0010\u0004J\u0012\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\u0014J\u0010\u0010\u0017\u001a\u00020\u00142\u0006\u0010\u0018\u001a\u00020\u0019H\u0016J\u0010\u0010\u001a\u001a\u00020\u00142\u0006\u0010\u001b\u001a\u00020\u0010H\u0016J\b\u0010\u001c\u001a\u00020\u0014H\u0002J\b\u0010\u001d\u001a\u00020\u0014H\u0002J\b\u0010\u001e\u001a\u00020\u0014H\u0002J\b\u0010\u001f\u001a\u00020\u0014H\u0002R\u001b\u0010\u0005\u001a\u00020\u00028VX\u0096\u0084\u0002\u00a2\u0006\f\n\u0004\b\b\u0010\t\u001a\u0004\b\u0006\u0010\u0007R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u0006 "}, d2 = {"Lcom/smtz/cvgenius/presentation/details/reference/ReferenceActivity;", "Lcom/smtz/cvgenius/core/BaseActivity;", "Lcom/smtz/cvgenius/databinding/ActivityReferenceBinding;", "Lcom/smtz/cvgenius/presentation/details/reference/ButtonSaveReferenceDelegate;", "()V", "binding", "getBinding", "()Lcom/smtz/cvgenius/databinding/ActivityReferenceBinding;", "binding$delegate", "Lkotlin/Lazy;", "mAddDetailAdapter", "Lcom/smtz/cvgenius/presentation/details/reference/ReferenceAdapter;", "mCvVO", "Lcom/smtz/cvgenius/domain/model/CvVO;", "mReferenceList", "", "Lcom/smtz/cvgenius/domain/model/ReferenceVO;", "mReferenceViewPod", "Lcom/smtz/cvgenius/presentation/details/reference/ReferenceViewPod;", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "onTapDelete", "id", "", "onTapSaveReference", "referenceVO", "setUpAdapters", "setUpData", "setUpListeners", "setUpViewPods", "app_release"})
public final class ReferenceActivity extends com.smtz.cvgenius.core.BaseActivity<com.smtz.cvgenius.databinding.ActivityReferenceBinding> implements com.smtz.cvgenius.presentation.details.reference.ButtonSaveReferenceDelegate {
    @org.jetbrains.annotations.Nullable
    private com.smtz.cvgenius.domain.model.CvVO mCvVO;
    @org.jetbrains.annotations.NotNull
    private java.util.List<com.smtz.cvgenius.domain.model.ReferenceVO> mReferenceList;
    @org.jetbrains.annotations.NotNull
    private com.smtz.cvgenius.presentation.details.reference.ReferenceAdapter mAddDetailAdapter;
    private com.smtz.cvgenius.presentation.details.reference.ReferenceViewPod mReferenceViewPod;
    @org.jetbrains.annotations.NotNull
    private final kotlin.Lazy binding$delegate = null;
    
    public ReferenceActivity() {
        super();
    }
    
    @java.lang.Override
    @org.jetbrains.annotations.NotNull
    public com.smtz.cvgenius.databinding.ActivityReferenceBinding getBinding() {
        return null;
    }
    
    @java.lang.Override
    protected void onCreate(@org.jetbrains.annotations.Nullable
    android.os.Bundle savedInstanceState) {
    }
    
    private final void setUpData() {
    }
    
    private final void setUpAdapters() {
    }
    
    private final void setUpListeners() {
    }
    
    private final void setUpViewPods() {
    }
    
    @java.lang.Override
    public void onTapSaveReference(@org.jetbrains.annotations.NotNull
    com.smtz.cvgenius.domain.model.ReferenceVO referenceVO) {
    }
    
    @java.lang.Override
    public void onTapDelete(long id) {
    }
}