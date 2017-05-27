# AndroidKotlinBoilerplate
Android Boilerplate written in Kotlin.

## Libraries
- Dagger2
- DataBinding
- Fabric(Crashlytics)
- RxJava2
- Retrofit2
- PaperParcel
- Timber
- Picasso
- Google Support Library

## Requirements

- Android SDK
- Android `7.1(API25)`
- Android SDK Tools
- Android SDK Build tools `25.0.1`

## Usage

```
$ cp signing.properties.sample signing.properties
$ cp app/fabric.properties.sample app/fabric.properties
```

If you not use the Fabric, Also todo this.
```diff
diff --git app/build.gradle app/build.gradle
index 3b96c80..ffe331e 100644
--- app/build.gradle
+++ app/build.gradle
@@ -2,7 +2,6 @@ apply plugin: 'com.android.application'
 apply plugin: 'kotlin-android'
 apply plugin: 'kotlin-kapt'

-apply plugin: 'io.fabric'
 apply plugin: 'com.github.ben-manes.versions'
 apply plugin: 'com.vanniktech.android.apk.size'

diff --git build.gradle build.gradle
index 4ff94ff..42ed0d6 100644
--- build.gradle
+++ build.gradle
@@ -12,7 +12,6 @@ buildscript {
         classpath 'com.android.tools.build:gradle:2.2.2'
         classpath "org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlin_version"
         classpath 'com.github.ben-manes:gradle-versions-plugin:0.11.3'
-        classpath 'io.fabric.tools:gradle:1.21.7'
         classpath 'com.vanniktech:gradle-android-apk-size-plugin:0.2.0'
     }
 }
```

## License
MIT
