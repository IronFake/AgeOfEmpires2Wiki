package com.ironfake.ageofempires2wiki.ui.buildingList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ListView
import androidx.fragment.app.Fragment
import com.ironfake.ageofempires2wiki.R
import com.ironfake.ageofempires2wiki.inkection.component.DaggerFragmentComponent
import javax.inject.Inject


class BuildingListFragment : Fragment(), BuildingListContract.View {

    @Inject
    lateinit var presenter: BuildingListContract.Presenter

    private lateinit var listView: ListView
    private lateinit var listAdapter : BuildingListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectDependency()
        presenter.attach(this, context!!)
    }

    private fun injectDependency() {
        val buildComponent = DaggerFragmentComponent.builder()
            .build()
        buildComponent.inject(this)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.unsubscribe()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_building_list, container, false)
        listView = view.findViewById(R.id.building_list_view)

        presenter.inflateBuildingList()

        listView.setOnItemClickListener { _: AdapterView<*>, _:View, position:Int, _:Long ->
            presenter.setFragment(position)
        }

        // Inflate the layout for this fragment
        return view

    }

    override fun updateAdapter(list: List<String>) {
        listAdapter = BuildingListAdapter(context!!, resources, list)
        listView.adapter = listAdapter
        listAdapter.notifyDataSetChanged()
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
