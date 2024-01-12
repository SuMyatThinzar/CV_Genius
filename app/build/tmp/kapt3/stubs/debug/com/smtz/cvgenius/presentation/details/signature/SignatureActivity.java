package com.smtz.cvgenius.presentation.details.signature;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000 \u001e2\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\u001eB\u0005\u00a2\u0006\u0002\u0010\u0003J\u0016\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00102\u0006\u0010\u0015\u001a\u00020\u0016J\b\u0010\u0017\u001a\u00020\u0013H\u0016J\u0012\u0010\u0018\u001a\u00020\u00132\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aH\u0014J\b\u0010\u001b\u001a\u00020\u0013H\u0002J\b\u0010\u001c\u001a\u00020\u0013H\u0002J\b\u0010\u001d\u001a\u00020\u0013H\u0002R\u001b\u0010\u0004\u001a\u00020\u00028VX\u0096\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006R\u000e\u0010\t\u001a\u00020\nX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u0010X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001f"}, d2 = {"Lcom/smtz/cvgenius/presentation/details/signature/SignatureActivity;", "Lcom/smtz/cvgenius/core/BaseActivity;", "Lcom/smtz/cvgenius/databinding/ActivitySignatureBinding;", "()V", "binding", "getBinding", "()Lcom/smtz/cvgenius/databinding/ActivitySignatureBinding;", "binding$delegate", "Lkotlin/Lazy;", "drawingView", "Lcom/smtz/cvgenius/common/components/DrawingView;", "mCvModel", "Lcom/smtz/cvgenius/domain/repository/CvModel;", "mCvVO", "Lcom/smtz/cvgenius/domain/model/CvVO;", "mImagePath", "", "mSignature", "displayImageFromBase64", "", "base64Image", "view", "Landroid/view/View;", "onBackPressed", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "requestData", "saveDrawing", "setUpListeners", "Companion", "app_debug"})
public final class SignatureActivity extends com.smtz.cvgenius.core.BaseActivity<com.smtz.cvgenius.databinding.ActivitySignatureBinding> {
    private com.smtz.cvgenius.common.components.DrawingView drawingView;
    @org.jetbrains.annotations.Nullable
    private java.lang.String mImagePath;
    @org.jetbrains.annotations.Nullable
    private java.lang.String mSignature;
    @org.jetbrains.annotations.NotNull
    private com.smtz.cvgenius.domain.repository.CvModel mCvModel;
    @org.jetbrains.annotations.Nullable
    private com.smtz.cvgenius.domain.model.CvVO mCvVO;
    @org.jetbrains.annotations.NotNull
    private final kotlin.Lazy binding$delegate = null;
    @org.jetbrains.annotations.NotNull
    public static final com.smtz.cvgenius.presentation.details.signature.SignatureActivity.Companion Companion = null;
    
    public SignatureActivity() {
        super();
    }
    
    @java.lang.Override
    @org.jetbrains.annotations.NotNull
    public com.smtz.cvgenius.databinding.ActivitySignatureBinding getBinding() {
        return null;
    }
    
    @java.lang.Override
    protected void onCreate(@org.jetbrains.annotations.Nullable
    android.os.Bundle savedInstanceState) {
    }
    
    private final void requestData() {
    }
    
    public final void displayImageFromBase64(@org.jetbrains.annotations.NotNull
    java.lang.String base64Image, @org.jetbrains.annotations.NotNull
    android.view.View view) {
    }
    
    private final void setUpListeners() {
    }
    
    private final void saveDrawing() {
    }
    
    @java.lang.Override
    public void onBackPressed() {
    }
    
    @kotlin.Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006\u00a8\u0006\u0007"}, d2 = {"Lcom/smtz/cvgenius/presentation/details/signature/SignatureActivity$Companion;", "", "()V", "newIntent", "Landroid/content/Intent;", "context", "Landroid/content/Context;", "app_debug"})
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