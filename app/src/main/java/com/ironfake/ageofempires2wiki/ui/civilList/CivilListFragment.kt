package com.ironfake.ageofempires2wiki.ui.civilList

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ListView
import androidx.fragment.app.Fragment
import com.ironfake.ageofempires2wiki.R
import com.ironfake.ageofempires2wiki.inkection.component.DaggerFragmentComponent
import com.ironfake.ageofempires2wiki.model.aoeApiModels.Civilization
import javax.inject.Inject


class CivilListFragment : Fragment(), CivilListContract.View {

    @Inject
    lateinit var presenter: CivilListContract.Presenter

    private lateinit var listView: ListView
    private lateinit var listAdapter : CivilListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectDependency()
        presenter.attach(this,  context!!)
    }

    private fun injectDependency() {
        val civilComponent = DaggerFragmentComponent.builder()
            .build()
        civilComponent.inject(this)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.unsubscribe()
    }

    @SuppressLint("CheckResult")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_civil_list, container, false)
        listView = view.findViewById(R.id.civil_list_view)

        presenter.inflateCivilList()

        listView.setOnItemClickListener { _:AdapterView<*>, _:View, position:Int, _:Long ->
            presenter.setFragment(position)
        }
        return view
    }

    override fun updateAdapter(list: List<Civilization>) {
        listAdapter = CivilListAdapter(context!!, resources, list)
        listView.adapter = listAdapter
        listAdapter.notifyDataSetChanged()
    }

    override fun setAdapter(list: List<Civilization>) {

    }

    override fun showFragment(fragment: Fragment) {
        fragmentManager?.let {
            it
                .beginTransaction()
                .addToBackStack(null)
                .replace(R.id.frame_layout,fragment)
                .commit()
        }
    }
}
