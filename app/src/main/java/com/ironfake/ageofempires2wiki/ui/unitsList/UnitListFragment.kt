package com.ironfake.ageofempires2wiki.ui.unitsList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ExpandableListView
import androidx.fragment.app.Fragment
import com.ironfake.ageofempires2wiki.R
import com.ironfake.ageofempires2wiki.inkection.component.DaggerFragmentComponent
import javax.inject.Inject

class UnitListFragment : Fragment(), UnitListContract.View {

    @Inject
    lateinit var presenter: UnitListContract.Presenter
    lateinit var expandableListView: ExpandableListView
    lateinit var unitListAdapter: UnitListAdapter

        override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectDependency()
        presenter.attach(this, context!!)

    }

    private fun injectDependency() {
        val unitsComponent = DaggerFragmentComponent.builder()
            .build()
        unitsComponent.inject(this)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.unsubscribe()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_unit_list, container, false)
        expandableListView = view.findViewById(R.id.unitExpListView)
        unitListAdapter = UnitListAdapter(context!!, resources, presenter.getGroupList(),
            presenter.getItemList(), expandableListView)
        expandableListView.setAdapter(unitListAdapter)
        expandableListView.setOnChildClickListener{ _: ExpandableListView, _: View, groupPosition: Int, childPosition: Int, _: Long ->
            presenter.setFragment(groupPosition, childPosition)
            false}

        // Inflate the layout for this fragment
        return view
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

    override fun expandGroup(id: Int) {
        expandableListView.expandGroup(id)
    }
}
