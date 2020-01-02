package com.ironfake.ageofempires2wiki.ui.Units

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.ironfake.ageofempires2wiki.R
import com.ironfake.ageofempires2wiki.inkection.component.DaggerFragmentComponent
import javax.inject.Inject

class UnitsFragment : Fragment(), UnitsContrast.View {

    @Inject
    lateinit var presenter: UnitsContrast.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectDependency()
    }

    private fun injectDependency() {
        val unitsComponent = DaggerFragmentComponent.builder()
            .build()

        unitsComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_units, container, false)
    }
}
