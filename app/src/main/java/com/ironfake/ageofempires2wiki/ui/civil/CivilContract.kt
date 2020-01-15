package com.ironfake.ageofempires2wiki.ui.civil

import androidx.fragment.app.Fragment
import com.ironfake.ageofempires2wiki.base.BaseContract
import com.ironfake.ageofempires2wiki.model.aoeApiModels.Civilization
import com.ironfake.ageofempires2wiki.model.aoeApiModels.Technology
import com.ironfake.ageofempires2wiki.model.aoeApiModels.Unit
import io.reactivex.Single

class CivilContract {
    interface View: BaseContract.View {
        fun setCivilization(civil: Civilization)
        fun showUniqueUnit(list: List<Unit>)
        fun showUniqueTech(list: List<Technology>)
        fun showFragment(fragment: Fragment)
    }

    interface Presenter: BaseContract.Presenter<View> {
        fun getData(position: Int): Single<Civilization>
        fun getUniqueUnits(list: List<String>?)
        fun getUniqueTech(list: List<String>?)
        fun setFragment(id: Int)
    }
}