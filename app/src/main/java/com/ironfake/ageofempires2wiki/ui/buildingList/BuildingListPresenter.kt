package com.ironfake.ageofempires2wiki.ui.buildingList

import android.content.Context
import android.os.Bundle
import android.util.Log
import com.ironfake.ageofempires2wiki.base.BaseApp
import com.ironfake.ageofempires2wiki.ui.building.BuildingFragment
import com.ironfake.ageofempires2wiki.utils.TAG
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class BuildingListPresenter : BuildingListContract.Presenter{

    private val subscriptions = CompositeDisposable()

    private lateinit var context: Context
    private lateinit var view: BuildingListContract.View

    var buildingList: List<String> = ArrayList()

    override fun subscribe() {
    }

    override fun unsubscribe() {
        subscriptions.clear()
    }

    override fun attach(view: BuildingListContract.View, context: Context) {
        this.view = view
        this.context = context
    }


    override fun inflateBuildingList() {
        subscriptions.add(BaseApp.dataBase!!.getBuildingDAO().getUniqueBuildingName()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {buildList  ->
                    buildingList = buildList
                    view.updateAdapter(buildList)
                },
                { error -> Log.e(TAG, error.message)}))
    }

    override fun setFragment(position: Int) {
        val fragment = BuildingFragment()
        val bundle = Bundle()
        bundle.putString("position", buildingList[position])
        fragment.arguments = bundle
        view.showFragment(fragment)
    }
}