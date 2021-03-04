package jesse.jones.kotlinloginexample.activity.auth.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import jesse.jones.kotlinloginexample.R
import jesse.jones.kotlinloginexample.common.BaseFragment
import kotlinx.android.synthetic.main.fragment_auth_welcome_screen.*

/**
 * @author Jesse Jones
 */
class AuthWelcomeScreenFragment : BaseFragment() {

    fun newInstance(): AuthWelcomeScreenFragment? {
        return AuthWelcomeScreenFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_auth_welcome_screen, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        auth_login_button.setOnClickListener {
            AuthLoginFragment().newInstance()?.let { addFragment(it) }
        }
        auth_register_button.setOnClickListener {
            AuthRegisterFragment().newInstance()?.let { addFragment(it) }
        }
    }
}