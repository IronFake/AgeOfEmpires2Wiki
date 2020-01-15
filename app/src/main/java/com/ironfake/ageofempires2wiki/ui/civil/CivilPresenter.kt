package com.ironfake.ageofempires2wiki.ui.civil

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import com.ironfake.ageofempires2wiki.base.BaseApp
import com.ironfake.ageofempires2wiki.model.aoeApiModels.Civilization
import com.ironfake.ageofempires2wiki.model.aoeApiModels.Technology
import com.ironfake.ageofempires2wiki.model.aoeApiModels.Unit
import com.ironfake.ageofempires2wiki.ui.unit.UnitFragment
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.net.URI

class CivilPresenter : CivilContract.Presenter {

    private val subscriptions = CompositeDisposable()

    private lateinit var context: Context
    private lateinit var view: CivilContract.View


    private var uniqueUnit: ArrayList<Unit> = ArrayList()
    private var uniqueTech: ArrayList<Technology> = ArrayList()


    @SuppressLint("CheckResult")
    override fun getData(position: Int): Single<Civilization> {
        return BaseApp.dataBase!!.getCivilDAO().getCivil(position)
    }

    override fun getUniqueUnits(list: List<String>?) {
        getList(list)?.let {
            for(unit in it){
                BaseApp.dataBase!!.getUnitDAO().getUnit(unit)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe { t: Unit? ->
                        uniqueUnit.add(t!!)
                        view.showUniqueUnit(uniqueUnit)
                    }
            }
        }
    }

    override fun getUniqueTech(list: List<String>?) {
        getList(list)?.let {
            for(tech in it){
                BaseApp.dataBase!!.getTechDAO().getTech(tech)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe { t: Technology? ->
                        uniqueTech.add(t!!)
                        view.showUniqueTech(uniqueTech)
                    }
            }
        }
    }

    private fun getList(list: List<String>?): List<String> {
        list.let {
            var filterList = ArrayList<String>()
            for (unit in list!!) {
                val uriPath = URI(unit).path
                val unitName = uriPath.substring(uriPath.lastIndexOf('/') + 1)
                    .replace("_", " ")
                val output =
                    unitName.split(" ")
                        .joinToString(" ") { word -> word[0].toUpperCase() + word.substring(1) }
                filterList.add(output)
            }
            return filterList
        }
        return emptyList()
    }

    override fun setFragment(id: Int) {
        val fragment = UnitFragment()
        val bundle = Bundle()
        bundle.putInt("position", id)
        fragment.arguments = bundle
        view.showFragment(fragment)
    }

    override fun subscribe() {

    }

    override fun unsubscribe() {

    }

    override fun attach(view: CivilContract.View, context: Context) {
        this.view = view
        this.context = context
    }
}