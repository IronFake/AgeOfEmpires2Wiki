package com.ironfake.ageofempires2wiki.ui.civilList

import androidx.fragment.app.Fragment
import com.ironfake.ageofempires2wiki.base.BaseContract
import com.ironfake.ageofempires2wiki.model.aoeApiModels.Civilization

class CivilListContract {
    interface View: BaseContract.View {
        fun updateAdapter(list: List<Civilization>)
        fun setAdapter(list: List<Civilization>)
        fun showFragment(fragment: Fragment)


    }

    interface Presenter: BaseContract.Presenter<View> {
        fun inflateCivilList()
        fun setFragment(childPosition: Int)
    }
}