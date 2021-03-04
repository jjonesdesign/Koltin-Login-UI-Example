package jesse.jones.kotlinloginexample.activity.auth.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import jesse.jones.kotlinloginexample.R
import jesse.jones.kotlinloginexample.activity.auth.events.AuthSuccessfulEvent
import jesse.jones.kotlinloginexample.activity.auth.viewmodels.LoginFormViewModel
import jesse.jones.kotlinloginexample.common.BaseFragment
import jesse.jones.kotlinloginexample.utils.PreferenceUtility
import kotlinx.android.synthetic.main.fragment_auth_login_screen.*
import org.greenrobot.eventbus.EventBus

/**
 * @author Jesse Jones
 */
class AuthLoginFragment : BaseFragment() {
    fun newInstance(): AuthLoginFragment? {
        return AuthLoginFragment()
    }

    private var viewModel: LoginFormViewModel =
        ViewModelProvider.NewInstanceFactory().create(LoginFormViewModel::class.java)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_auth_login_screen, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Set Data Observers
        viewModel.getOnUserNameError.observe(viewLifecycleOwner, Observer { error ->
            when (error) {
                LoginFormViewModel.ERROR_USERNAME_LENGTH -> auth_input_username_container.error =
                    getString(R.string.error_username_min_length)
                LoginFormViewModel.NO_ERROR -> auth_input_username_container.error = null
            }
        })
        viewModel.getOnPasswordError.observe(viewLifecycleOwner, Observer { error ->
            when (error) {
                LoginFormViewModel.ERROR_PASSWORD_NOT_VALID -> auth_input_password_container.error =
                    getString(R.string.error_password_not_valid)
                LoginFormViewModel.NO_ERROR -> auth_input_password_container.error = null
            }
        })

        viewModel.getOnLoginResult.observe(viewLifecycleOwner, Observer { loggedin ->
            when (loggedin) {
                true -> {
                    context?.let {
                        PreferenceUtility().setUserName(it, auth_input_username.text.toString())
                        EventBus.getDefault().post(AuthSuccessfulEvent())
                    }
                }
                //false -> If we were using a live DB we would probably explain why login failed
            }
        })

        auth_login_button.setOnClickListener {
            //Do some kind of data validation here
            viewModel.login(
                auth_input_username.text.toString(),
                auth_input_password.text.toString()
            )
        }

        auth_register_account_button.setOnClickListener {
            clearBackstack()
            AuthRegisterFragment().newInstance()?.let { addFragment(it) }
        }
    }
}