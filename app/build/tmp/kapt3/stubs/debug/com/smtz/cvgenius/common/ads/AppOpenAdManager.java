package com.smtz.cvgenius.common.ads;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u00012\u00020\u0002:\u0001%B\u0011\b\u0016\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004\u00a2\u0006\u0002\u0010\u0005J\u0006\u0010\u0010\u001a\u00020\u0011J\n\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0002J\b\u0010\u0014\u001a\u00020\u000bH\u0002J\u001a\u0010\u0015\u001a\u00020\u00112\u0006\u0010\u0016\u001a\u00020\t2\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H\u0016J\u0010\u0010\u0019\u001a\u00020\u00112\u0006\u0010\u0016\u001a\u00020\tH\u0016J\u0010\u0010\u001a\u001a\u00020\u00112\u0006\u0010\u0016\u001a\u00020\tH\u0016J\u0010\u0010\u001b\u001a\u00020\u00112\u0006\u0010\u0016\u001a\u00020\tH\u0016J\u0018\u0010\u001c\u001a\u00020\u00112\u0006\u0010\u0016\u001a\u00020\t2\u0006\u0010\u0017\u001a\u00020\u0018H\u0016J\u0010\u0010\u001d\u001a\u00020\u00112\u0006\u0010\u0016\u001a\u00020\tH\u0016J\u0010\u0010\u001e\u001a\u00020\u00112\u0006\u0010\u0016\u001a\u00020\tH\u0016J\u0010\u0010\u001f\u001a\u00020\u00112\u0006\u0010 \u001a\u00020!H\u0016J\b\u0010\"\u001a\u00020\u0011H\u0002J\u0010\u0010#\u001a\u00020\u000b2\u0006\u0010$\u001a\u00020\u000fH\u0002R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006&"}, d2 = {"Lcom/smtz/cvgenius/common/ads/AppOpenAdManager;", "Landroidx/lifecycle/DefaultLifecycleObserver;", "Landroid/app/Application$ActivityLifecycleCallbacks;", "myApplication", "Lcom/smtz/cvgenius/CVGeniusApplication;", "(Lcom/smtz/cvgenius/CVGeniusApplication;)V", "appOpenAd", "Lcom/google/android/gms/ads/appopen/AppOpenAd;", "currentActivity", "Landroid/app/Activity;", "isShowingAd", "", "loadCallback", "Lcom/google/android/gms/ads/appopen/AppOpenAd$AppOpenAdLoadCallback;", "loadTime", "", "fetchAd", "", "getAdRequest", "Lcom/google/android/gms/ads/AdRequest;", "isAdAvailable", "onActivityCreated", "activity", "savedInstanceState", "Landroid/os/Bundle;", "onActivityDestroyed", "onActivityPaused", "onActivityResumed", "onActivitySaveInstanceState", "onActivityStarted", "onActivityStopped", "onStart", "owner", "Landroidx/lifecycle/LifecycleOwner;", "showAdIfAvailable", "wasLoadTimeLessThanNHoursAgo", "numHours", "AppOpen", "app_debug"})
public final class AppOpenAdManager implements androidx.lifecycle.DefaultLifecycleObserver, android.app.Application.ActivityLifecycleCallbacks {
    @org.jetbrains.annotations.Nullable
    private com.google.android.gms.ads.appopen.AppOpenAd appOpenAd;
    @org.jetbrains.annotations.Nullable
    private com.google.android.gms.ads.appopen.AppOpenAd.AppOpenAdLoadCallback loadCallback;
    @org.jetbrains.annotations.Nullable
    private android.app.Activity currentActivity;
    @org.jetbrains.annotations.Nullable
    private com.smtz.cvgenius.CVGeniusApplication myApplication;
    private boolean isShowingAd = false;
    private long loadTime = 0L;
    
    /**
     * Constructor
     */
    public AppOpenAdManager(@org.jetbrains.annotations.Nullable
    com.smtz.cvgenius.CVGeniusApplication myApplication) {
        super();
    }
    
    /**
     * LifecycleObserver methods
     */
    @java.lang.Override
    public void onStart(@org.jetbrains.annotations.NotNull
    androidx.lifecycle.LifecycleOwner owner) {
    }
    
    /**
     * Request an ad
     */
    public final void fetchAd() {
    }
    
    /**
     * Utility method to check if ad was loaded more than n hours ago.
     */
    private final boolean wasLoadTimeLessThanNHoursAgo(long numHours) {
        return false;
    }
    
    /**
     * Shows the ad if one isn't already showing.
     */
    private final void showAdIfAvailable() {
    }
    
    /**
     * Creates and returns ad request.
     */
    private final com.google.android.gms.ads.AdRequest getAdRequest() {
        return null;
    }
    
    /**
     * Utility method that checks if ad exists and can be shown.
     */
    private final boolean isAdAvailable() {
        return false;
    }
    
    /**
     * ActivityLifecycleCallback methods
     */
    @java.lang.Override
    public void onActivityCreated(@org.jetbrains.annotations.NotNull
    android.app.Activity activity, @org.jetbrains.annotations.Nullable
    android.os.Bundle savedInstanceState) {
    }
    
    @java.lang.Override
    public void onActivityStarted(@org.jetbrains.annotations.NotNull
    android.app.Activity activity) {
    }
    
    @java.lang.Override
    public void onActivityResumed(@org.jetbrains.annotations.NotNull
    android.app.Activity activity) {
    }
    
    @java.lang.Override
    public void onActivityPaused(@org.jetbrains.annotations.NotNull
    android.app.Activity activity) {
    }
    
    @java.lang.Override
    public void onActivityStopped(@org.jetbrains.annotations.NotNull
    android.app.Activity activity) {
    }
    
    @java.lang.Override
    public void onActivitySaveInstanceState(@org.jetbrains.annotations.NotNull
    android.app.Activity activity, @org.jetbrains.annotations.NotNull
    android.os.Bundle savedInstanceState) {
    }
    
    @java.lang.Override
    public void onActivityDestroyed(@org.jetbrains.annotations.NotNull
    android.app.Activity activity) {
    }
    
    @kotlin.Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0006"}, d2 = {"Lcom/smtz/cvgenius/common/ads/AppOpenAdManager$AppOpen;", "", "()V", "AD_UNIT_ID", "", "LOG_TAG", "app_debug"})
    public static final class AppOpen {
        @org.jetbrains.annotations.NotNull
        public static final java.lang.String LOG_TAG = "AppOpenManager";
        @org.jetbrains.annotations.NotNull
        public static final java.lang.String AD_UNIT_ID = "ca-app-pub-4196466051386479/4220806569";
        @org.jetbrains.annotations.NotNull
        public static final com.smtz.cvgenius.common.ads.AppOpenAdManager.AppOpen INSTANCE = null;
        
        private AppOpen() {
            super();
        }
    }
}