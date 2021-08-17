package co.sharif.namava

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : Application() {

    //bayad token ro makhfi kard masalan ba estefade az NDK.
    //vali baray test man inja gozashtam.
    //ya ba ravesh hay ramz hash ro zakhire konim.

    companion object{
        var tokenAuthorize : String = "5a87b2fa9381992a4afa6c8d9b7c1e0b"
    }
}