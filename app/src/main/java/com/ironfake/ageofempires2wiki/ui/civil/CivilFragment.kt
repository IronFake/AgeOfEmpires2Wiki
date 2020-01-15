package com.ironfake.ageofempires2wiki.ui.civil

import android.R.attr.defaultValue
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.ironfake.ageofempires2wiki.R
import com.ironfake.ageofempires2wiki.inkection.component.DaggerFragmentComponent
import com.ironfake.ageofempires2wiki.model.aoeApiModels.Civilization
import com.ironfake.ageofempires2wiki.model.aoeApiModels.Technology
import com.ironfake.ageofempires2wiki.model.aoeApiModels.Unit
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import kotlin.properties.Delegates


class CivilFragment : Fragment(), CivilContract.View{

    @Inject
    lateinit var presenter: CivilContract.Presenter

    var position by Delegates.notNull<Int>()

    private lateinit var uniqueUnitsLinearLayout: LinearLayout
    private lateinit var uniqueTechLinearLayout: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectDependency()
        val bundle = this.arguments
        if (bundle != null) {
             position = bundle.getInt("position", defaultValue)

        }
        presenter.attach(this,context!!)
    }

    private fun injectDependency() {
        val civilComponent = DaggerFragmentComponent.builder()
            .build()
        civilComponent.inject(this)
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_civil, container, false)
        val imageLogo: ImageView = view.findViewById(R.id.civil_logo)
        val civilName: TextView = view.findViewById(R.id.civil_name_one)
        val expansion :TextView = view.findViewById(R.id.expansion)
        val armyType :TextView = view.findViewById(R.id.armyType)

        uniqueUnitsLinearLayout = view.findViewById(R.id.uniqueUnitList)
        uniqueTechLinearLayout = view.findViewById(R.id.uniqueTechList)
        val teamBonusList: TextView = view.findViewById(R.id.teamBonus)
        val civilBonusList: TextView = view.findViewById(R.id.civil_bonus)
        civilBonusList.isScrollContainer = false

        presenter.getData(position)?.let {
            presenter.getData(position)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe{it ->
                    civilName.text = it.name
                    imageLogo.setImageResource(resources.getIdentifier(it.image, "drawable", context!!.packageName))
                    expansion.text = it.expansion
                    armyType.text = it.armyType
                    presenter.getUniqueUnits(it.uniqueUnit)
                    presenter.getUniqueTech(it.uniqueTech)
                    teamBonusList.text = it.teamBonus
                    var value = 1
                    for (bonus in it.civilizationBonus!!){
                        civilBonusList.append("$value. $bonus + \n")
                        value += 1
                    }
                }
        }


        // Inflate the layout for this fragment
        return view
    }

    override fun setCivilization(civilization: Civilization) {
            //civil = civilization
    }

    override fun showUniqueUnit(list: List<Unit>) {
        uniqueUnitsLinearLayout.removeAllViews()
        for (unit in list){
            val view = LayoutInflater.from(context!!).inflate(R.layout.list_view_row, null)
            view.setOnClickListener {
                presenter.setFragment(unit.id)
            }
            val imageView = view.findViewById<ImageView>(R.id.civil_logo)
            val civilName = view.findViewById<TextView>(R.id.civil_name)

            imageView.setImageResource(resources.getIdentifier(unit.image, "drawable", context!!.packageName))
            civilName.text = unit.name
            uniqueUnitsLinearLayout.addView(view)
        }

    }

    override fun showUniqueTech(list: List<Technology>) {
        uniqueTechLinearLayout.removeAllViews()
        for (tech in list){
            val view = LayoutInflater.from(context!!).inflate(R.layout.list_view_row, null)
            val imageView = view.findViewById<ImageView>(R.id.civil_logo)
            val civilName = view.findViewById<TextView>(R.id.civil_name)

            imageView.setImageResource(resources.getIdentifier(tech.image, "drawable", context!!.packageName))
            civilName.text = tech.name
            uniqueTechLinearLayout.addView(view)
        }
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
