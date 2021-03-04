package jesse.jones.kotlinloginexample.activity.auth.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.regex.Pattern

/**
 * @author Jesse Jones
 */
class LoginFormViewModel : ViewModel() {
    private val onUsernameError: MutableLiveData<Int> = MutableLiveData()
    private val onPasswordError: MutableLiveData<Int> = MutableLiveData()
    private val onLoginResult: MutableLiveData<Boolean> = MutableLiveData()

    companion object {
        val NO_ERROR = 0;
        val ERROR_USERNAME_LENGTH = 2;
        val ERROR_PASSWORD_NOT_VALID = 3;
    }

    //Public live data access
    public val getOnUserNameError: LiveData<Int> = onUsernameError
    public val getOnPasswordError: LiveData<Int> = onPasswordError
    public val getOnLoginResult: LiveData<Boolean> = onLoginResult


    fun login(username: String, password: String) {
        clearErrors()
        if (!isUsernameValid(username)) {
            return
        }
        if (!isPasswordValid(password)) {
            return
        }
        onLoginResult.value = true
    }

    fun clearErrors() {
        onUsernameError.value = NO_ERROR
        onPasswordError.value = NO_ERROR
    }

    //Validation
    fun isUsernameValid(username: String): Boolean {
        if (username.length < 4) {
            onUsernameError.value = ERROR_USERNAME_LENGTH
            return false
        }
        return true
    }

    fun isPasswordValid(password: String): Boolean {
        if (password.length < 8) {
            onPasswordError.value = ERROR_PASSWORD_NOT_VALID
            return false
        }
        val charRegex: Pattern = Pattern.compile("[$&+,:;=\\\\?@#|/'<>.^*()%!-]")
        if (!charRegex.matcher(password).find()) {
            onPasswordError.value = RegisterFormViewModel.ERROR_PASSWORD_NOT_VALID
            return false
        }
        val numRegex: Pattern = Pattern.compile("[0123456789]")
        val found = numRegex.matcher(password).find()
        if (!found) {
            onPasswordError.value = RegisterFormViewModel.ERROR_PASSWORD_NOT_VALID
            return false
        }
        return true
    }

}
