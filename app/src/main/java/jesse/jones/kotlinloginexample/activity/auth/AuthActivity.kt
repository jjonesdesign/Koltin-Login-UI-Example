package jesse.jones.kotlinloginexample.activity.auth

import android.content.Intent
import android.os.Bundle
import jesse.jones.kotlinloginexample.R
import jesse.jones.kotlinloginexample.activity.MainActivity
import jesse.jones.kotlinloginexample.activity.auth.events.AuthSuccessfulEvent
import jesse.jones.kotlinloginexample.activity.auth.fragments.AuthWelcomeScreenFragment
import jesse.jones.kotlinloginexample.common.BaseActivity
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

/**
 * @author Jesse Jones
 */
class AuthActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth_main)
        AuthWelcomeScreenFragment().newInstance()?.let { setFragment(it) }
    }

    override fun onStart() {
        super.onStart()
        EventBus.getDefault().register(this)
    }

    override fun onStop() {
        super.onStop()
        EventBus.getDefault().unregister(this)
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onMessageEvent(event: AuthSuccessfulEvent?) {
        //Login/Register was successful, proceed to MainActivity
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}