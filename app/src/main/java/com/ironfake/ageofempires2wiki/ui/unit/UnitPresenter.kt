package com.ironfake.ageofempires2wiki.ui.unit

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import com.ironfake.ageofempires2wiki.base.BaseApp
import com.ironfake.ageofempires2wiki.model.aoeApiModels.Building
import com.ironfake.ageofempires2wiki.model.aoeApiModels.Unit
import com.ironfake.ageofempires2wiki.ui.building.BuildingFragment
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class UnitPresenter : UnitContact.Presenter{

    private lateinit var context: Context
    private lateinit var view: UnitContact.View

    override fun subscribe() {

    }

    override fun unsubscribe() {

    }

    override fun attach(view: UnitContact.View, context: Context) {
        this.view = view
        this.context = context
    }

    @SuppressLint("CheckResult")
    override fun getData(position: Int): Single<Unit> {
        return BaseApp.dataBase!!.getUnitDAO().getUnit(position)
    }

    @SuppressLint("CheckResult")
    override fun getBuilding(building: String?) {
        BaseApp.dataBase!!.getBuildingDAO().getBuilding(building!!)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe ({ build: Building? ->
                view.showCreateIn(build!!)
            }, {
                it.printStackTrace()
            })
    }

    override fun setFragment(name: String) {
        val fragment = BuildingFragment()
        val bundle = Bundle()
        bundle.putString("position", name)
        fragment.arguments = bundle
        view.showFragment(fragment)
    }
}