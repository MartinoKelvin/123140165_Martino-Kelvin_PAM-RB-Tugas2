
## Nama     : Martino Kelvin

## NIM      : 123140165


### Result
<img width="575" height="1280" alt="image" src="https://github.com/user-attachments/assets/05ea7d33-13ee-4a03-9c67-7e306671439e" />

---

### Fitur - Fitur :
1. Flow yang mensimulasikan data berita baru setiap 2 detik
   <img width="680" height="540" alt="image" src="https://github.com/user-attachments/assets/84c43110-400c-481c-8da9-857e994c0d61" />

2. Filter berita berdasarkan kategori tertentu
  <img width="570" height="314" alt="image" src="https://github.com/user-attachments/assets/62e3a3e1-60b5-4f69-9dc4-2b05285a8052" />

3. Coroutines untuk mengambil detail berita secara async
   <img width="874" height="364" alt="image" src="https://github.com/user-attachments/assets/e31c859d-91c7-4dba-9adc-b03a56fcd3b2" />



This is a Kotlin Multiplatform project targeting Android, iOS.

* [/composeApp](./composeApp/src) is for code that will be shared across your Compose Multiplatform applications.
  It contains several subfolders:
  - [commonMain](./composeApp/src/commonMain/kotlin) is for code that’s common for all targets.
  - Other folders are for Kotlin code that will be compiled for only the platform indicated in the folder name.
    For example, if you want to use Apple’s CoreCrypto for the iOS part of your Kotlin app,
    the [iosMain](./composeApp/src/iosMain/kotlin) folder would be the right place for such calls.
    Similarly, if you want to edit the Desktop (JVM) specific part, the [jvmMain](./composeApp/src/jvmMain/kotlin)
    folder is the appropriate location.

* [/iosApp](./iosApp/iosApp) contains iOS applications. Even if you’re sharing your UI with Compose Multiplatform,
  you need this entry point for your iOS app. This is also where you should add SwiftUI code for your project.

### Build and Run Android Application

To build and run the development version of the Android app, use the run configuration from the run widget
in your IDE’s toolbar or build it directly from the terminal:
- on macOS/Linux
  ```shell
  ./gradlew :composeApp:assembleDebug
  ```
- on Windows
  ```shell
  .\gradlew.bat :composeApp:assembleDebug
  ```

### Build and Run iOS Application

To build and run the development version of the iOS app, use the run configuration from the run widget
in your IDE’s toolbar or open the [/iosApp](./iosApp) directory in Xcode and run it from there.

---

Learn more about [Kotlin Multiplatform](https://www.jetbrains.com/help/kotlin-multiplatform-dev/get-started.html)…
