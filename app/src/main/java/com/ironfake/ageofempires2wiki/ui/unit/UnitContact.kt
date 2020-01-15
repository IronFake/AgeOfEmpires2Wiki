package com.ironfake.ageofempires2wiki.ui.unit

import androidx.fragment.app.Fragment
import com.ironfake.ageofempires2wiki.base.BaseContract
import com.ironfake.ageofempires2wiki.model.aoeApiModels.Building
import com.ironfake.ageofempires2wiki.model.aoeApiModels.Unit
import io.reactivex.Single

class UnitContact {

    interface View: BaseContract.View{
        fun showCreateIn(building: Building)
        fun showFragment(fragment: Fragment)
    }

    interface Presenter: BaseContract.Presenter<View> {
        fun getData(position: Int): Single<Unit>
        fun getBuilding(building: String?)
        fun setFragment(name: String)
    }
}