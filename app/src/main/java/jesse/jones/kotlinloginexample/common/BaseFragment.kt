package jesse.jones.kotlinloginexample.common

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import jesse.jones.kotlinloginexample.R

/**
 * BaseFragment is used to maintain fragment additions and setting in a more friendly and consistent manner within fragments
 * @author Jesse Jones
 */
open class BaseFragment : Fragment() {
    /**
     * Set the current Fragment and display it, replaces all other fragments in backstack
     *
     * @param `fragment` - Fragment class
     */
    public fun setFragment(fragment: Fragment) {
        val fragmentManager = requireActivity().supportFragmentManager
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
        val fragmentManager = requireActivity().supportFragmentManager
        val transaction = fragmentManager.beginTransaction()
        transaction.replace(R.id.root_container, fragment)
        transaction.addToBackStack(fragment.javaClass.simpleName)
        transaction.commit()
    }

    /**
     * Clears the entire fragment backstack leaving only the first fragment
     *
     */
    public fun clearBackstack() {
        val manager = requireActivity().supportFragmentManager
        val first: FragmentManager.BackStackEntry = manager.getBackStackEntryAt(0)
        manager.popBackStack(first.id, FragmentManager.POP_BACK_STACK_INCLUSIVE)
    }
}