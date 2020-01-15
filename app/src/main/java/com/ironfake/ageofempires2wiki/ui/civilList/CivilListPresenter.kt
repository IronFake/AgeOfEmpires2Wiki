package com.ironfake.ageofempires2wiki.ui.civilList

import android.content.Context
import android.os.Bundle
import com.ironfake.ageofempires2wiki.base.BaseApp
import com.ironfake.ageofempires2wiki.ui.civil.CivilFragment
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers


class CivilListPresenter : CivilListContract.Presenter {


    private val subscriptions = CompositeDisposable()
    private lateinit var context: Context
    private lateinit var view: CivilListContract.View

    override fun subscribe() {


    }

    override fun unsubscribe() {
        subscriptions.clear()
    }


    override fun attach(view: CivilListContract.View, context: Context) {
        this.view = view
        this.context = context
    }

    override fun inflateCivilList(){
        subscriptions.add(BaseApp.dataBase!!.getCivilDAO().getAllCivil()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe{ list ->
                view.updateAdapter(list)
            })
    }

    override fun setFragment(childPosition: Int) {
        val fragment = CivilFragment()
        val bundle = Bundle()
        bundle.putInt("position", childPosition+1)
        fragment.arguments = bundle
        view.showFragment(fragment)
    }
}