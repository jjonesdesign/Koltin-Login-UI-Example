package jesse.jones.kotlinloginexample.activity.auth.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import jesse.jones.kotlinloginexample.R
import jesse.jones.kotlinloginexample.activity.auth.events.AuthSuccessfulEvent
import jesse.jones.kotlinloginexample.activity.auth.viewmodels.RegisterFormViewModel
import jesse.jones.kotlinloginexample.common.BaseFragment
import jesse.jones.kotlinloginexample.utils.PreferenceUtility
import kotlinx.android.synthetic.main.fragment_auth_register_screen.*
import org.greenrobot.eventbus.EventBus

/**
 * @author Jesse Jones
 */
class AuthRegisterFragment : BaseFragment() {
    fun newInstance(): AuthRegisterFragment? {
        return AuthRegisterFragment()
    }

    private var viewModel: RegisterFormViewModel = ViewModelProvider.NewInstanceFactory().create(
        RegisterFormViewModel::class.java
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_auth_register_screen, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Set Data Observers
        viewModel.getOnNameError.observe(viewLifecycleOwner, Observer { error ->
            when (error) {
                RegisterFormViewModel.ERROR_NAME_NOT_VALID -> auth_input_name_container.error =
                    getString(R.string.error_name_min_length)
                RegisterFormViewModel.NO_ERROR -> auth_input_name_container.error = null
            }
        })
        viewModel.getOnUserNameError.observe(viewLifecycleOwner, Observer { error ->
            when (error) {
                RegisterFormViewModel.ERROR_USERNAME_LENGTH -> auth_input_username_container.error =
                    getString(R.string.error_username_min_length)
                RegisterFormViewModel.NO_ERROR -> auth_input_username_container.error = null
            }
        })
        viewModel.getOnPasswordError.observe(viewLifecycleOwner, Observer { error ->
            when (error) {
                RegisterFormViewModel.ERROR_PASSWORD_NOT_VALID -> auth_input_password_container.error =
                    getString(R.string.error_password_not_valid)
                RegisterFormViewModel.NO_ERROR -> auth_input_password_container.error = null
            }
        })

        viewModel.getOnRegisterResult.observe(viewLifecycleOwner, Observer { registered ->
            when (registered) {
                true -> {
                    context?.let {
                        PreferenceUtility().setUserName(it, auth_input_username.text.toString())
                        EventBus.getDefault().post(AuthSuccessfulEvent())
                    }
                }
                //false -> If we were using a live DB we would probably explain why register failed
            }
        })

        auth_register_button.setOnClickListener {
            //Do some kind of data validation here
            viewModel.register(
                auth_input_name.text.toString(),
                auth_input_username.text.toString(),
                auth_input_password.text.toString()
            )
        }
        auth_use_existing_account_button.setOnClickListener {
            clearBackstack()
            AuthLoginFragment().newInstance()?.let { addFragment(it) }
        }
    }
}