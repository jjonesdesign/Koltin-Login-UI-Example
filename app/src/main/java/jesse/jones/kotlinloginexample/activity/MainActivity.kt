package jesse.jones.kotlinloginexample.activity

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import jesse.jones.kotlinloginexample.R
import jesse.jones.kotlinloginexample.utils.PreferenceUtility
import kotlinx.android.synthetic.main.activity_main_screen.*

/**
 * @author Jesse Jones
 */
class MainActivity : AppCompatActivity() {

    //private var viewModel: SignInForm = ViewModelProvider.NewInstanceFactory().create(SignInForm::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_screen)

        setSupportActionBar(findViewById(R.id.toolbar))

        //No reason to implement MVVM with minimal view requirements
        main_user_name_display.text = "Hello, " + PreferenceUtility().getUserName(this) + "!"
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}