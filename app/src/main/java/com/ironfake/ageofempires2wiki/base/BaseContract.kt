package com.ironfake.ageofempires2wiki.base

import android.content.Context

class BaseContract {

    interface Presenter<in T> {
        fun subscribe()
        fun unsubscribe()
        fun attach(view: T, context: Context)
    }

    interface View {

    }

}