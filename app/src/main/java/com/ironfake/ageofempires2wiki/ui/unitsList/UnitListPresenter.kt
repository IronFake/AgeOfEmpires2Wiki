package com.ironfake.ageofempires2wiki.ui.unitsList

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import com.ironfake.ageofempires2wiki.R
import com.ironfake.ageofempires2wiki.base.BaseApp
import com.ironfake.ageofempires2wiki.model.aoeApiModels.Unit
import com.ironfake.ageofempires2wiki.ui.unit.UnitFragment
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class UnitListPresenter : UnitListContract.Presenter{

    private val subscriptions = CompositeDisposable()


    private lateinit var context: Context
    private lateinit var view: UnitListContract.View

    var listGroup: ArrayList<Int> = ArrayList()
    var listItem: HashMap<Int, List<Unit>> = HashMap()

    override fun subscribe() {

    }

    override fun unsubscribe() {
        subscriptions.clear()
    }

    override fun attach(view: UnitListContract.View, context: Context) {
        this.view = view
        this.context = context
    }

    override fun getGroupList(): List<Int> {
        listGroup.clear()
        listGroup.add(R.drawable.age_dark)
        listGroup.add(R.drawable.age_feudal)
        listGroup.add(R.drawable.age_castle)
        listGroup.add(R.drawable.age_imperial)
        return listGroup
    }

    @SuppressLint("CheckResult")
    override fun getItemList(): HashMap<Int, List<Unit>> {
        subscriptions.add(BaseApp.dataBase!!.getUnitDAO().getAllDarkUnits()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { darkUnitList ->
                listItem[R.drawable.age_dark] = darkUnitList
                view.expandGroup(0)
            })
        subscriptions.add(BaseApp.dataBase!!.getUnitDAO().getAllFeudalUnits()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { feudalUnitList ->
                listItem[R.drawable.age_feudal] = feudalUnitList
                view.expandGroup(1)
            })
        subscriptions.add(BaseApp.dataBase!!.getUnitDAO().getAllCastleUnits()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { castleUnitList ->
                listItem[R.drawable.age_castle] = castleUnitList
                view.expandGroup(2)
            })
        subscriptions.add(BaseApp.dataBase!!.getUnitDAO().getAllImperialUnits()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { imperialUnitList ->
                listItem[R.drawable.age_imperial] = imperialUnitList
                view.expandGroup(3)
            })
        return listItem
    }

    override fun setFragment(groupPosition: Int, childPosition: Int) {
        val fragment = UnitFragment()
        val bundle = Bundle()
        bundle.putInt("position", listItem[listGroup[groupPosition]]!![childPosition].id)
        fragment.arguments = bundle
        view.showFragment(fragment)
    }
}