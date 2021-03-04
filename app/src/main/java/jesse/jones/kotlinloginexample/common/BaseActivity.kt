package jesse.jones.kotlinloginexample.common

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import jesse.jones.kotlinloginexample.R

/**
 * BaseActivity is used to maintain fragment additions and setting in a more friendly and consistent manner within activities
 * @author Jesse Jones
 */
open class BaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)
    }

    /**
     * Set the current Fragment and display it, replaces all other fragments in backstack
     *
     * @param `fragment` - Fragment class
     */
    public fun setFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val transaction = fragmentManager.beginTransaction()
        transaction.replace(R.id.root_container, fragment)
        transaction.commit()
    }

    /**
     * Add the current Fragment to the current backstack, and distplay it
     *
     * @param `fragment` - Fragment class
     */
    public fun addFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val transaction = fragmentManager.beginTransaction()
        transaction.replace(R.id.root_container, fragment)
        transaction.addToBackStack(fragment.javaClass.simpleName)
        transaction.commit()
    }
}