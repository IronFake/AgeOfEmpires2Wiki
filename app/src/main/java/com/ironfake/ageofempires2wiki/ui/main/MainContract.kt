package com.ironfake.ageofempires2wiki.ui.main

import android.view.MenuItem
import androidx.fragment.app.Fragment
import com.ironfake.ageofempires2wiki.base.BaseContract
import com.ironfake.ageofempires2wiki.model.aoeApiModels.*
import com.ironfake.ageofempires2wiki.model.aoeApiModels.Unit
import io.reactivex.Observable

class MainContract {

    interface View: BaseContract.View {
        fun showFragment(fragment: Fragment)
    }

    interface Presenter: BaseContract.Presenter<View> {
        fun setFragment(item: MenuItem)
        fun checkDB()

        fun createCivilObservable(listCivilisation: CivilizationList): Observable<List<Civilization>>
        fun createUnitObservable(listUnit: UnitList): Observable<List<Unit>>
        fun createBuildingObservable(listBuild: BuildingList): Observable<List<Building>>
        fun createTechObservable(listTech: TechnologyList): Observable<List<Technology>>
        fun preparingString(str: String?): String
    }
}