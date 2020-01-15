package com.ironfake.ageofempires2wiki.ui.main

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.view.MenuItem
import com.ironfake.ageofempires2wiki.R
import com.ironfake.ageofempires2wiki.api.ApiServiceInterface
import com.ironfake.ageofempires2wiki.base.BaseApp
import com.ironfake.ageofempires2wiki.model.aoeApiModels.*
import com.ironfake.ageofempires2wiki.model.aoeApiModels.Unit
import com.ironfake.ageofempires2wiki.ui.buildingList.BuildingListFragment

import com.ironfake.ageofempires2wiki.ui.civilList.CivilListFragment
import com.ironfake.ageofempires2wiki.ui.newsList.NewsFragment
import com.ironfake.ageofempires2wiki.ui.unitsList.UnitListFragment
import com.ironfake.ageofempires2wiki.ui.web.WebViewActivity
import com.ironfake.ageofempires2wiki.utils.AOE_BASE_URL
import com.ironfake.ageofempires2wiki.utils.TAG
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers


import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.net.URI

class MainPresenter : MainContract.Presenter {

    private val subscriptions = CompositeDisposable()
    private val api: ApiServiceInterface = ApiServiceInterface.create(AOE_BASE_URL)

    private lateinit var view: MainContract.View
    private lateinit var context: Context

    override fun subscribe() {
        Log.e(TAG, "check")

        subscriptions.add( api
            .getAllCivil()
            .flatMapObservable{ t: CivilizationList ->  createCivilObservable(t)}
            .flatMapIterable { t: List<Civilization> -> t }
            .subscribeOn(Schedulers.io())
            .subscribe(
                {civilization  ->
                    civilization.image = "civ_" + civilization.name.toLowerCase()
                    BaseApp.dataBase?.let {
                        it.getCivilDAO().addCivil(civilization)
                    }
                }, { error -> Log.e(TAG, error.message)}))

        subscriptions.add( api
            .getAllUnits()
            .flatMapObservable{ t: UnitList ->  createUnitObservable(t)}
            .flatMapIterable { t: List<Unit> -> t }
            .subscribeOn(Schedulers.io())
            .subscribe(
                {unit  ->
                    unit.image = "unit_" + unit.name.toLowerCase().replace("(", "")
                        .replace(")", "")
                        .replace(" ", "_")
                    unit.createdIn = preparingString(unit.createdIn)
                    BaseApp.dataBase?.let {
                        it.getUnitDAO().addUnit(unit)
                    }
                }, { error -> Log.e(TAG, error.message)}))

        subscriptions.add( api
            .getAllTech()
            .flatMapObservable { t: TechnologyList ->  createTechObservable(t)}
            .flatMapIterable { t: List<Technology> -> t }
            .subscribeOn(Schedulers.io())
            .subscribe(
                {tech  ->
                    tech.image = "tech_" + tech.name.toLowerCase().replace(" ", "_")
                    tech.developsIn = preparingString(tech.developsIn)
                    BaseApp.dataBase?.let {
                        it.getTechDAO().addTech(tech)
                    }
                }, { error -> Log.e(TAG, error.message)}))

        subscriptions.add( api
            .getAllBuildings()
            .flatMapObservable { t: BuildingList ->  createBuildingObservable(t)}
            .flatMapIterable { t: List<Building> -> t }
            .subscribeOn(Schedulers.io())
            .subscribe(
                {build  ->
                    build.image = "build_" + build.name.toLowerCase().replace(" ", "_")
                    BaseApp.dataBase?.let {
                        it.getBuildingDAO().addBuilding(build)
                    }
                }, { error -> Log.e(TAG, error.message)}))
    }

    override fun createCivilObservable(listCivilisation: CivilizationList): Observable<List<Civilization>> {
        return Observable.just(listCivilisation.civilizations)
    }

    override fun createUnitObservable(listUnit: UnitList): Observable<List<Unit>> {
        return Observable.just(listUnit.units)
    }

    override fun createBuildingObservable(listBuild: BuildingList): Observable<List<Building>> {
        return Observable.just(listBuild.structures)
    }

    override fun createTechObservable(listTech: TechnologyList): Observable<List<Technology>> {
        return Observable.just(listTech.technologies)
    }

    override fun unsubscribe() {
        subscriptions.clear()
    }

    override fun attach(view: MainContract.View, context: Context) {
        this.view = view
        this.context = context
        checkDB()
        view.showFragment(NewsFragment())
    }

    override fun setFragment(item: MenuItem) {
        when(item.itemId){
            R.id.civil -> view.showFragment(CivilListFragment())
            R.id.units -> view.showFragment(UnitListFragment())
            R.id.news -> view.showFragment(NewsFragment())
            R.id.buildings -> view.showFragment(BuildingListFragment())
            R.id.techTree -> {
                val i = Intent(context, WebViewActivity::class.java)
                i.data = Uri.parse("https://aoe2techtree.net")
                i.action = Intent.ACTION_VIEW
                context.startActivity(i)
            }
        }
    }

    @SuppressLint("CheckResult")
    override fun checkDB(){
        BaseApp.dataBase!!.getUnitDAO().getCount()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { t: Int? ->
                if (t!! <= 0) subscribe()
            }
    }


    override fun preparingString(str: String?): String {
        val uriPath = URI(str).path
        val unitName = uriPath.substring(uriPath.lastIndexOf('/') + 1).replace("_", " ")
        val output = unitName.split(" ")
            .joinToString(" ") { word -> word[0].toUpperCase() + word.substring(1) }
        return output
    }
}

