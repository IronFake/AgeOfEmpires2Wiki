package com.ironfake.ageofempires2wiki.ui.main

import androidx.fragment.app.Fragment
import com.ironfake.ageofempires2wiki.base.BaseContract

class MainContrast {

    interface View: BaseContract.View {
        fun setFragment(fragment: Fragment)
    }

    interface Presenter: BaseContract.Presenter<MainContrast.View> {
        fun addFragment(fragment: Fragment)
    }
}