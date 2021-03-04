package jesse.jones.kotlinloginexample.activity.auth.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.regex.Pattern

/**
 * @author Jesse Jones
 */
class RegisterFormViewModel : ViewModel() {
    private var onNameError: MutableLiveData<Int> = MutableLiveData()
    private val onUsernameError: MutableLiveData<Int> = MutableLiveData()
    private val onPasswordError: MutableLiveData<Int> = MutableLiveData()
    private val onRegisterResult: MutableLiveData<Boolean> = MutableLiveData()

    companion object {
        val NO_ERROR = 0;
        val ERROR_NAME_NOT_VALID = 1;
        val ERROR_USERNAME_LENGTH = 2;
        val ERROR_PASSWORD_NOT_VALID = 3;
    }

    //Public live data access
    public val getOnNameError: LiveData<Int> = onNameError
    public val getOnUserNameError: LiveData<Int> = onUsernameError
    public val getOnPasswordError: LiveData<Int> = onPasswordError
    public val getOnRegisterResult: LiveData<Boolean> = onRegisterResult


    fun register(name: String, username: String, password: String) {
        clearErrors()
        if (!isNameValid(name)) {
            return
        }
        if (!isUsernameValid(username)) {
            return
        }
        if (!isPasswordValid(password)) {
            return
        }
        onRegisterResult.value = true
    }

    fun clearErrors() {
        onNameError.value = NO_ERROR
        onUsernameError.value = NO_ERROR
        onPasswordError.value = NO_ERROR
    }

    //Validation
    fun isNameValid(name: String): Boolean {
        if (name.isEmpty()) {
            onNameError.value = ERROR_NAME_NOT_VALID
            return false
        }
        return true
    }

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
            onPasswordError.value = ERROR_PASSWORD_NOT_VALID
            return false
        }
        val numRegex: Pattern = Pattern.compile("[0123456789]")
        val found = numRegex.matcher(password).find()
        if (!found) {
            onPasswordError.value = ERROR_PASSWORD_NOT_VALID
            return false
        }
        return true
    }


}