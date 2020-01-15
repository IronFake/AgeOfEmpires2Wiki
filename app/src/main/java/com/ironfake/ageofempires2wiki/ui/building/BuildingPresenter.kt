package com.ironfake.ageofempires2wiki.ui.building

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import com.ironfake.ageofempires2wiki.base.BaseApp
import com.ironfake.ageofempires2wiki.model.aoeApiModels.Building
import com.ironfake.ageofempires2wiki.model.aoeApiModels.Technology
import com.ironfake.ageofempires2wiki.model.aoeApiModels.Unit
import com.ironfake.ageofempires2wiki.ui.unit.UnitFragment
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class BuildingPresenter: BuildingContact.Presenter {

    private lateinit var context: Context
    private lateinit var view: BuildingContact.View

    override fun subscribe() {

    }

    override fun unsubscribe() {

    }

    override fun attach(view: BuildingContact.View, context: Context) {
        this.context = context
        this.view = view
    }

    @SuppressLint("CheckResult")
    override fun getData(name: String): Single<List<Building>> {
        return BaseApp.dataBase!!.getBuildingDAO().getBuildings(name)
    }

    @SuppressLint("CheckResult")
    override fun getUnits(building: String?) {
        BaseApp.dataBase!!.getUnitDAO().getUnitsByBuilding(building!!)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { units: List<Unit>? ->
                view.showUnits(units!!)
            }
    }

    @SuppressLint("CheckResult")
    override fun getTechnologies(building: String?) {
        BaseApp.dataBase!!.getTechDAO().getTechnologies(building!!)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { technologies: List<Technology>? ->
                view.showTechnologies(technologies!!)
            }
    }

    override fun setFragment(id: Int) {
        val fragment = UnitFragment()
        val bundle = Bundle()
        bundle.putInt("position", id)
        fragment.arguments = bundle
        view.showFragment(fragment)
    }
}