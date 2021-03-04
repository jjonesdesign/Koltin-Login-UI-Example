package jesse.jones.kotlinloginexample.utils

import android.content.Context

/**
 * Utility tool for writing and reading application preference settings
 * @author Jesse Jones
 */
class PreferenceUtility {

    private val APP_NAME = "Koltin_Login_Example"
    private val USER_NAME = "user_name"

    fun getUserName(context : Context): String? {
        val prefs = context.getSharedPreferences(APP_NAME,Context.MODE_PRIVATE)
        return prefs.getString(USER_NAME,"")
    }

    fun setUserName(context: Context, username : String){
        val sharedPref = context.getSharedPreferences(APP_NAME,Context.MODE_PRIVATE) ?: return
        with (sharedPref.edit()) {
            putString(USER_NAME, username)
            apply()
        }
    }
}