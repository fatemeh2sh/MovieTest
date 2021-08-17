package co.sharif.namava

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : Application() {

    companion object{
        var tokenAuthorize : String = "5a87b2fa9381992a4afa6c8d9b7c1e0b"
    }
}