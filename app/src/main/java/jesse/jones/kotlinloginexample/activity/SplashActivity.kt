package jesse.jones.kotlinloginexample.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import jesse.jones.kotlinloginexample.R
import jesse.jones.kotlinloginexample.activity.auth.AuthActivity

/**
 * @author Jesse Jones
 */
class SplashActivity : AppCompatActivity() {

    private val SPLASH_TIME_OUT: Long = 3000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)


        Handler().postDelayed({
            //delay, then open main activity
            startActivity(Intent(this, AuthActivity::class.java))
            // close this activity
            finish()
        }, SPLASH_TIME_OUT)
    }
}