package com.smtz.cvgenius.presentation.preview;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\u0000\u0086\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0015\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\t\u0018\u0000 ?2\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001?B\u0005\u00a2\u0006\u0002\u0010\u0003J\u0018\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020!H\u0002J$\u0010\"\u001a\u00020\u001d2\u0006\u0010 \u001a\u00020!2\u0012\u0010#\u001a\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\u001d0$H\u0002J\u0012\u0010%\u001a\u00020\u001d2\b\u0010&\u001a\u0004\u0018\u00010\'H\u0014J\b\u0010(\u001a\u00020\u001dH\u0014J-\u0010)\u001a\u00020\u001d2\u0006\u0010*\u001a\u00020\u00052\u000e\u0010+\u001a\n\u0012\u0006\b\u0001\u0012\u00020-0,2\u0006\u0010.\u001a\u00020/H\u0016\u00a2\u0006\u0002\u00100J\u0010\u00101\u001a\u00020\u001d2\u0006\u00102\u001a\u000203H\u0016J\u0018\u00104\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010\u000b\u001a\u00020\fH\u0002J \u00105\u001a\u00020\u001d2\u0006\u00106\u001a\u0002072\u0006\u00108\u001a\u00020-2\u0006\u0010\u001e\u001a\u00020\u001fH\u0002J\b\u00109\u001a\u00020\u001dH\u0002J\b\u0010:\u001a\u00020\u001dH\u0002J\u0010\u0010;\u001a\u00020\u001d2\u0006\u0010<\u001a\u00020\u0005H\u0002J\u0010\u0010=\u001a\u00020\u001d2\u0006\u0010>\u001a\u000203H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082D\u00a2\u0006\u0002\n\u0000R\u001b\u0010\u0006\u001a\u00020\u00028VX\u0096\u0084\u0002\u00a2\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u0007\u0010\bR\u001c\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0016X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0017\u001a\u0004\u0018\u00010\u0018X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u001aX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u0005X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006@"}, d2 = {"Lcom/smtz/cvgenius/presentation/preview/PreviewActivity;", "Lcom/smtz/cvgenius/core/BaseActivity;", "Lcom/smtz/cvgenius/databinding/ActivityPreviewBinding;", "()V", "REQUEST_CODE_PERMISSION_STORAGE", "", "binding", "getBinding", "()Lcom/smtz/cvgenius/databinding/ActivityPreviewBinding;", "binding$delegate", "Lkotlin/Lazy;", "cacheFile", "Ljava/io/File;", "getCacheFile", "()Ljava/io/File;", "setCacheFile", "(Ljava/io/File;)V", "mBannerAdView", "Lcom/google/android/gms/ads/AdView;", "mCvId", "", "mCvModel", "Lcom/smtz/cvgenius/domain/repository/CvModel;", "mCvVO", "Lcom/smtz/cvgenius/domain/model/CvVO;", "mTemplateViewPod", "Lcom/smtz/cvgenius/presentation/preview/templateViewPods/BaseViewPod;", "mViewPodId", "convertAndSaveViewPodAsPdf", "", "context", "Landroid/content/Context;", "viewPodLayout", "Landroid/view/View;", "handleCacheFile", "callback", "Lkotlin/Function1;", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "onRequestPermissionsResult", "requestCode", "permissions", "", "", "grantResults", "", "(I[Ljava/lang/String;[I)V", "onWindowFocusChanged", "hasFocus", "", "printCacheFile", "printPdfDocument", "pdfDocument", "Landroid/graphics/pdf/PdfDocument;", "printJobName", "setUpListeners", "setUpTemplate", "setUpViewPod", "resumeId", "setViewsClickable", "clickable", "Companion", "app_debug"})
public final class PreviewActivity extends com.smtz.cvgenius.core.BaseActivity<com.smtz.cvgenius.databinding.ActivityPreviewBinding> {
    @org.jetbrains.annotations.NotNull
    private final kotlin.Lazy binding$delegate = null;
    private final int REQUEST_CODE_PERMISSION_STORAGE = 100;
    private com.google.android.gms.ads.AdView mBannerAdView;
    @org.jetbrains.annotations.Nullable
    private java.io.File cacheFile;
    @org.jetbrains.annotations.NotNull
    private com.smtz.cvgenius.domain.repository.CvModel mCvModel;
    private long mCvId = 0L;
    @org.jetbrains.annotations.Nullable
    private com.smtz.cvgenius.domain.model.CvVO mCvVO;
    private com.smtz.cvgenius.presentation.preview.templateViewPods.BaseViewPod mTemplateViewPod;
    private int mViewPodId = 1111;
    @org.jetbrains.annotations.NotNull
    public static final com.smtz.cvgenius.presentation.preview.PreviewActivity.Companion Companion = null;
    
    public PreviewActivity() {
        super();
    }
    
    @java.lang.Override
    @org.jetbrains.annotations.NotNull
    public com.smtz.cvgenius.databinding.ActivityPreviewBinding getBinding() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.io.File getCacheFile() {
        return null;
    }
    
    public final void setCacheFile(@org.jetbrains.annotations.Nullable
    java.io.File p0) {
    }
    
    @java.lang.Override
    protected void onCreate(@org.jetbrains.annotations.Nullable
    android.os.Bundle savedInstanceState) {
    }
    
    @java.lang.Override
    public void onWindowFocusChanged(boolean hasFocus) {
    }
    
    private final void setUpTemplate() {
    }
    
    private final void setUpViewPod(int resumeId) {
    }
    
    private final void setUpListeners() {
    }
    
    private final void handleCacheFile(android.view.View viewPodLayout, kotlin.jvm.functions.Function1<? super java.io.File, kotlin.Unit> callback) {
    }
    
    private final void printCacheFile(android.content.Context context, java.io.File cacheFile) {
    }
    
    private final void convertAndSaveViewPodAsPdf(android.content.Context context, android.view.View viewPodLayout) {
    }
    
    private final void setViewsClickable(boolean clickable) {
    }
    
    @java.lang.Override
    protected void onDestroy() {
    }
    
    @java.lang.Override
    public void onRequestPermissionsResult(int requestCode, @org.jetbrains.annotations.NotNull
    java.lang.String[] permissions, @org.jetbrains.annotations.NotNull
    int[] grantResults) {
    }
    
    private final void printPdfDocument(android.graphics.pdf.PdfDocument pdfDocument, java.lang.String printJobName, android.content.Context context) {
    }
    
    @kotlin.Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006\u00a8\u0006\u0007"}, d2 = {"Lcom/smtz/cvgenius/presentation/preview/PreviewActivity$Companion;", "", "()V", "newIntent", "Landroid/content/Intent;", "context", "Landroid/content/Context;", "app_debug"})
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