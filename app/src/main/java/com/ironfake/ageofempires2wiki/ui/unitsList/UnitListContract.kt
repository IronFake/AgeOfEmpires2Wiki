package com.ironfake.ageofempires2wiki.ui.unitsList

import androidx.fragment.app.Fragment
import com.ironfake.ageofempires2wiki.base.BaseContract
import com.ironfake.ageofempires2wiki.model.aoeApiModels.Unit

class UnitListContract {

    interface View: BaseContract.View{
        fun showFragment(fragment: Fragment)
        fun expandGroup(id: Int)
    }

    interface Presenter: BaseContract.Presenter<View> {
        fun getGroupList() : List<Int>
        fun getItemList() : HashMap<Int, List<Unit>>
        fun setFragment(groupPosition: Int, childPosition: Int)

    }
}