package ru.spbstu.abit.splashscreen

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import ru.spbstu.abit.MainActivity
import ru.spbstu.abit.R
import ru.spbstu.abit.base.BaseActivity

class SplashScreenActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.splash_screen)

        Handler().postDelayed({
            val mainIntent = Intent(this@SplashScreenActivity, MainActivity::class.java)
            this@SplashScreenActivity.startActivity(mainIntent)
            this@SplashScreenActivity.finish()
        }, SPLASH_DISPLAY_LENGTH)
    }

    companion object {
        /** Duration of wait  */
        private const val SPLASH_DISPLAY_LENGTH = 500L
    }
}