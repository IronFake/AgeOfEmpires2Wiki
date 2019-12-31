package com.ironfake.ageofempires2wiki.base

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity


/**
 * Activity all Activity classes of rosso must extend. It provides required methods and presenter
 * instantiation and calls.
 * @param P the type of the presenter the Activity is based on
 */
abstract class BaseActivity<P: BasePresenter<BaseView>> : BaseView, AppCompatActivity(){

    protected lateinit var presenter: P

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = instantPresenter()
    }

    /**
     * Instantiates the presenter the Activity is based on.
     */
    abstract fun instantPresenter(): P

    override fun getContext(): Context {
        return this
    }
}