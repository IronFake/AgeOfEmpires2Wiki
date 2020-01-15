package com.ironfake.ageofempires2wiki.ui.buildingList

import androidx.fragment.app.Fragment
import com.ironfake.ageofempires2wiki.base.BaseContract

class BuildingListContract {

    interface View: BaseContract.View{
        fun updateAdapter(list: List<String>)
        fun showFragment(fragment: Fragment)
    }

    interface Presenter: BaseContract.Presenter<View> {
        fun inflateBuildingList()
        fun setFragment(position: Int)
    }
}