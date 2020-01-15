package com.ironfake.ageofempires2wiki.ui.building

import androidx.fragment.app.Fragment
import com.ironfake.ageofempires2wiki.base.BaseContract
import com.ironfake.ageofempires2wiki.model.aoeApiModels.Building
import com.ironfake.ageofempires2wiki.model.aoeApiModels.Technology
import com.ironfake.ageofempires2wiki.model.aoeApiModels.Unit
import io.reactivex.Single

class BuildingContact {

    interface View : BaseContract.View{
        fun showUnits(list: List<Unit>)
        fun showTechnologies(list: List<Technology>)
        fun showFragment(fragment: Fragment)
    }

    interface Presenter : BaseContract.Presenter<View>{
        fun getData(name: String): Single<List<Building>>
        fun getUnits(building: String?)
        fun getTechnologies(building: String?)
        fun setFragment(id: Int)
    }
}