package com.ironfake.ageofempires2wiki.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.ironfake.ageofempires2wiki.R
import com.ironfake.ageofempires2wiki.inkection.component.DaggerActivityComponent
import com.ironfake.ageofempires2wiki.inkection.module.ActivityModule
import com.ironfake.ageofempires2wiki.ui.Units.UnitsFragment
import com.ironfake.ageofempires2wiki.ui.civil.CivilFragment
import com.ironfake.ageofempires2wiki.ui.news.NewsFragment
import javax.inject.Inject

/**
 * Activity displaying the list of posts
 */
class MainActivity : AppCompatActivity(), MainContrast.View {

    @Inject
    lateinit var presenter: MainContrast.Presenter

    var civilFragment = CivilFragment()
    var unitsFragment =  UnitsFragment()
    var newsFragment =  NewsFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setBottomNavigation()

        injectDependency()
        presenter.attach(this)
    }

    private fun injectDependency() {
        val activityComponent = DaggerActivityComponent.builder()
            .activityModule(ActivityModule(this))
            .build()
        activityComponent.inject(this)
    }

    private fun setBottomNavigation() {
        val bottomNavigation : BottomNavigationView = findViewById(R.id.btm_nvg)
        bottomNavigation.setOnNavigationItemSelectedListener {item ->
            when (item.itemId){
                R.id.civil -> presenter.addFragment(civilFragment)
                R.id.units -> presenter.addFragment(unitsFragment)
                R.id.news -> presenter.addFragment(newsFragment)
            }

            true
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.unsubscribe()
    }

    override fun setFragment(fragment: Fragment) {

        //showing the presenter on screen
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.frame_layout,fragment)
            .commit()
    }
}