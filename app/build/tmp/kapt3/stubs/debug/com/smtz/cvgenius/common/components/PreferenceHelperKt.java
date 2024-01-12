package com.smtz.cvgenius.common.components;

@kotlin.Metadata(mv = {1, 8, 0}, k = 2, xi = 48, d1 = {"\u00008\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0010\u0006\n\u0002\b\u0006\u001a\u0016\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005\u001a\u000e\u0010\u0006\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003\u001a!\u0010\u0007\u001a\u00020\b*\u00020\u00012\u0012\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\b0\nH\u0082\b\u001a2\u0010\f\u001a\u0002H\r\"\n\b\u0000\u0010\r\u0018\u0001*\u00020\u000e*\u00020\u00012\u0006\u0010\u000f\u001a\u00020\u00052\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u0001H\rH\u0086\n\u00a2\u0006\u0002\u0010\u0011\u001a\u001a\u0010\u0012\u001a\u00020\u0013*\u00020\u00012\u0006\u0010\u000f\u001a\u00020\u00052\u0006\u0010\u0014\u001a\u00020\u0013\u001a\u001a\u0010\u0015\u001a\u00020\u000b*\u00020\u000b2\u0006\u0010\u000f\u001a\u00020\u00052\u0006\u0010\u0016\u001a\u00020\u0013\u001a\u001f\u0010\u0017\u001a\u00020\b*\u00020\u00012\u0006\u0010\u000f\u001a\u00020\u00052\b\u0010\u0018\u001a\u0004\u0018\u00010\u000eH\u0086\u0002\u00a8\u0006\u0019"}, d2 = {"customPrefs", "Landroid/content/SharedPreferences;", "context", "Landroid/content/Context;", "name", "", "defaultPrefs", "edit", "", "operation", "Lkotlin/Function1;", "Landroid/content/SharedPreferences$Editor;", "get", "T", "", "key", "defaultValue", "(Landroid/content/SharedPreferences;Ljava/lang/String;Ljava/lang/Object;)Ljava/lang/Object;", "getDouble", "", "default", "putDouble", "double", "set", "value", "app_debug"})
public final class PreferenceHelperKt {
    
    /**
     * Reference
     * https://medium.com/@krupalshah55/manipulating-shared-prefs-with-kotlin-just-two-lines-of-code-29af62440285
     */
    @org.jetbrains.annotations.NotNull
    public static final android.content.SharedPreferences defaultPrefs(@org.jetbrains.annotations.NotNull
    android.content.Context context) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public static final android.content.SharedPreferences customPrefs(@org.jetbrains.annotations.NotNull
    android.content.Context context, @org.jetbrains.annotations.NotNull
    java.lang.String name) {
        return null;
    }
    
    private static final void edit(android.content.SharedPreferences $this$edit, kotlin.jvm.functions.Function1<? super android.content.SharedPreferences.Editor, kotlin.Unit> operation) {
    }
    
    /**
     * puts a key value pair in shared prefs if doesn't exists, otherwise updates value on given [key]
     */
    public static final void set(@org.jetbrains.annotations.NotNull
    android.content.SharedPreferences $this$set, @org.jetbrains.annotations.NotNull
    java.lang.String key, @org.jetbrains.annotations.Nullable
    java.lang.Object value) {
    }
    
    @org.jetbrains.annotations.NotNull
    public static final android.content.SharedPreferences.Editor putDouble(@org.jetbrains.annotations.NotNull
    android.content.SharedPreferences.Editor $this$putDouble, @org.jetbrains.annotations.NotNull
    java.lang.String key, double p2_1484504552) {
        return null;
    }
    
    public static final double getDouble(@org.jetbrains.annotations.NotNull
    android.content.SharedPreferences $this$getDouble, @org.jetbrains.annotations.NotNull
    java.lang.String key, double p2_772401952) {
        return 0.0;
    }
}