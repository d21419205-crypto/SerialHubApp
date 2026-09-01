# SerialHub Android App

This is a lightweight Android WebView app for:
https://serialhub12.blogspot.com/

## What it does
- Opens the live SerialHub Blogger site.
- New Blogger posts automatically appear in the app.
- JavaScript, storage, images and media are enabled.
- Android back button navigates inside the website.
- No separate content database or hosting is required.

## Build
Open this folder in Android Studio.
Let Gradle sync, then use:
Build > Build Bundle(s) / APK(s) > Build APK(s)

For Google Play, build an Android App Bundle (AAB) and sign it with your own release key.

## Ads
The app loads the website itself, so website ad scripts may load as part of the web content. Whether a specific Monetag/Adsterra format is permitted and served inside an Android WebView depends on the current network and platform policies. Do not add or duplicate ad code in the app unless the ad network explicitly permits it.
