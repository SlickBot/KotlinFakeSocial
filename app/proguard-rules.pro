# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in C:\Users\SlickyPC\AppData\Local\Android\android-sdk/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# https://github.com/square/okio/issues/60
-dontwarn okio.**
-dontwarn retrofit2.**
-dontwarn javax.annotation.**
-dontwarn com.squareup.okhttp.**
#-dontwarn retrofit.appengine.UrlFetchClient

-keepattributes Signature
-keepattributes Exceptions

-keep class retrofit.** { *; }
-keep class com.ulj.slicky.kotlinfakesocial.model.** { *; }
-dontwarn com.ulj.slicky.kotlinfakesocial.model.**

#-keep class * implements android.os.Parcelable { public static final android.os.Parcelable$Creator *; }
#-keep class org.parceler.Parceler$$Parcels

-keepclasseswithmembers class * {
    @retrofit.http.* <methods>;
}
-keepclassmembernames interface * {
    @retrofit.http.* <methods>;
}
-dontwarn retrofit2.Platform$Java8