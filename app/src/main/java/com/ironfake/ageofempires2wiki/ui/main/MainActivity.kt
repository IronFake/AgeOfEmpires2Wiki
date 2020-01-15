package com.ironfake.ageofempires2wiki.ui.main

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.ironfake.ageofempires2wiki.R
import com.ironfake.ageofempires2wiki.inkection.component.DaggerActivityComponent
import com.ironfake.ageofempires2wiki.inkection.module.ActivityModule
import javax.inject.Inject


/**
 * Activity displaying the list of posts
 */
class MainActivity : AppCompatActivity(), MainContract.View {

    private val BACK_STACK_ROOT_TAG = "root_fragment"

    @Inject
    lateinit var presenter: MainContract.Presenter

    private lateinit var bottomNavigation: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        injectDependency()
        presenter.attach(this, this)

        setBottomNavigation()
    }

    private fun injectDependency() {
        val activityComponent = DaggerActivityComponent.builder()
            .activityModule(ActivityModule(this))
            .build()
        activityComponent.inject(this)
    }

    private fun setBottomNavigation() {
        bottomNavigation = findViewById(R.id.btm_nvg)
        bottomNavigation.setOnNavigationItemSelectedListener {item ->
            presenter.setFragment(item)
            true
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.unsubscribe()
    }

    override fun showFragment(fragment: Fragment) {

        val fragmentManager = supportFragmentManager
        Log.e(com.ironfake.ageofempires2wiki.utils.TAG, fragmentManager.backStackEntryCount.toString())
        fragmentManager.popBackStack(BACK_STACK_ROOT_TAG, FragmentManager.POP_BACK_STACK_INCLUSIVE)


        //showing the presenter on screen
        supportFragmentManager
            .beginTransaction()
            .addToBackStack(BACK_STACK_ROOT_TAG)
            .replace(R.id.frame_layout,fragment)
            .commit()
    }

    override fun onBackPressed() {
        val fragments = supportFragmentManager
        val homeFrag = fragments.findFragmentByTag("0")

        if (fragments.backStackEntryCount > 1) { // We have fragments on the backstack that are poppable
            fragments.popBackStackImmediate()
        } else { // We are already showing the home screen, so the next stop is out of the app.
            supportFinishAfterTransition()
        }
    }
}