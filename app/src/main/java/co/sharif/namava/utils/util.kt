package co.sharif.namava.utils

import android.app.Activity
import android.os.Build
import android.os.SystemClock
import android.view.WindowManager
import androidx.core.content.ContextCompat
import co.sharif.namava.R

fun setColorStatusBar(activity: Activity) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        try {
            val window = activity.window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.statusBarColor = ContextCompat.getColor(activity, R.color.purple_500)
        } catch (e: Exception) {
        }
    }
}

fun getTimestamp(time:Long): String{
    val value:Int = (time / 60).toInt()
    val seconds:Int = (time % 60).toInt()
    val hours = (value / 60)
    val minutes:Int = (value % 60)

    return if(hours != 0)
        "$hours : $minutes : $seconds"
    else
        "$minutes : $seconds"
}