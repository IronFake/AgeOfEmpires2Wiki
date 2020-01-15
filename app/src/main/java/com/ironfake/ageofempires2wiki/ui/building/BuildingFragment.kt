package com.ironfake.ageofempires2wiki.ui.building

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.ironfake.ageofempires2wiki.R
import com.ironfake.ageofempires2wiki.inkection.component.DaggerFragmentComponent
import com.ironfake.ageofempires2wiki.model.aoeApiModels.Technology
import com.ironfake.ageofempires2wiki.model.aoeApiModels.Unit
import com.ironfake.ageofempires2wiki.utils.TAG
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import kotlin.properties.Delegates


class BuildingFragment : Fragment(), BuildingContact.View {

    @Inject
    lateinit var presenter: BuildingContact.Presenter

    var position by Delegates.notNull<String>()

    lateinit var unitsLinearLayout: LinearLayout
    lateinit var technologiesLinearLayout: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectDependency()
        presenter.attach(this, context!!)
        val bundle = this.arguments
        if (bundle != null) {
            position = bundle.getString("position", "null")

        }
    }

    private fun injectDependency() {
        val buildingComponent = DaggerFragmentComponent.builder()
            .build()
        buildingComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_building, container, false)
        val buildName: TextView = view.findViewById(R.id.build_name_one)
        val buildLogo: ImageView = view.findViewById(R.id.build_logo)
        val expansion: TextView = view.findViewById(R.id.expansion)
        val age: TextView = view.findViewById(R.id.age)
        val costWood: TextView = view.findViewById(R.id.costWood)
        val costMeat: TextView = view.findViewById(R.id.costMeat)
        val costStone: TextView = view.findViewById(R.id.costStone)
        val costGold: TextView = view.findViewById(R.id.costGold)
        val darkBuildTime: TextView = view.findViewById(R.id.darkBuildTime)
        val feudalBuildTime: TextView = view.findViewById(R.id.feudalBuildTime)
        val castleBuildTime: TextView = view.findViewById(R.id.castleBuildTime)
        val imperialBuildTime: TextView = view.findViewById(R.id.imperialBuildTime)
        val darkHitPoints: TextView = view.findViewById(R.id.darkHitPoints)
        val feudalHitPoints: TextView = view.findViewById(R.id.feudalHitPoints)
        val castleHitPoints: TextView = view.findViewById(R.id.castleHitPoints)
        val imperialHitPoints: TextView = view.findViewById(R.id.imperialHitPoints)
        val darkLineOfSight: TextView = view.findViewById(R.id.darkLineOfSight)
        val feudalLineOfSight: TextView = view.findViewById(R.id.feudalLineOfSight)
        val castleLineOfSight: TextView = view.findViewById(R.id.castleLineOfSight)
        val imperialLineOfSight: TextView = view.findViewById(R.id.imperialLineOfSight)
        val darkArmor: TextView = view.findViewById(R.id.darkArmor)
        val feudalArmor: TextView = view.findViewById(R.id.feudalArmor)
        val castleArmor: TextView = view.findViewById(R.id.castleArmor)
        val imperialArmor: TextView = view.findViewById(R.id.imperialArmor)

        unitsLinearLayout = view.findViewById(R.id.units)
        technologiesLinearLayout = view.findViewById(R.id.technologies)

        presenter.getData(position)?.let {
            presenter.getData(position)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe( {
                    presenter.getUnits(it[0].name)
                    presenter.getTechnologies(it[0].name)
                    buildName.text = it[0].name
                    buildLogo.setImageResource(resources.getIdentifier(it[0].image, "drawable", context!!.packageName))
                    expansion.text = it[0].expansion
                    age.text = it[0].age
                    costWood.text = it[0].cost?.wood ?: "-"
                    costMeat.text = it[0].cost?.food ?: "-"
                    costStone.text = it[0].cost?.stone ?: "-"
                    costGold.text = it[0].cost?.gold ?: "-"
                    val helper = 4 - it.size
                    if (it.size >= 2){
                        castleBuildTime.text = it[2-helper].buildTime ?: "-"
                        castleHitPoints.text = it[2-helper].hitPoints ?: "-"
                        castleLineOfSight.text = it[2-helper].lineOfSight ?: "-"
                        castleArmor.text = it[2-helper].armor ?: "-"
                    }
                    if (it.size >= 3){
                        feudalBuildTime.text = it[1-helper].buildTime ?: "-"
                        feudalHitPoints.text = it[1-helper].hitPoints ?: "-"
                        feudalLineOfSight.text = it[1-helper].lineOfSight ?: "-"
                        feudalArmor.text = it[1-helper].armor ?: "-"
                    }
                    if (it.size >= 4){
                        darkBuildTime.text = it[0].buildTime ?: "-"
                        darkHitPoints.text = it[0].hitPoints ?: "-"
                        darkLineOfSight.text = it[0].lineOfSight ?: "-"
                        darkArmor.text = it[0].armor ?: "-"
                    }
                    if (it.size >= 0){
                        imperialBuildTime.text = it[3-helper].buildTime ?: "-"
                        imperialHitPoints.text = it[3-helper].hitPoints ?: "-"
                        imperialLineOfSight.text = it[3-helper].lineOfSight ?: "-"
                        imperialArmor.text = it[3-helper].armor ?: "-"
                    }


                }, {
                    error -> Log.e(TAG, error.printStackTrace().toString())
                })
        }

        // Inflate the layout for this fragment
        return view
    }

    override fun showUnits(list: List<Unit>) {
        unitsLinearLayout.removeAllViews()
        Log.e(TAG, list.toString())
        for (unit in list){
            val view = LayoutInflater.from(context!!).inflate(R.layout.unit_list_row, null)
            view.setOnClickListener {
                presenter.setFragment(unit.id)
            }
            val imageView = view.findViewById<ImageView>(R.id.logo)
            val civilName = view.findViewById<TextView>(R.id.name)
            val costWood  = view.findViewById<TextView>(R.id.costWood)
            val costMeat  = view.findViewById<TextView>(R.id.costMeat)
            val costStone  = view.findViewById<TextView>(R.id.costStone)
            val costGold  = view.findViewById<TextView>(R.id.costGold)

            imageView.setImageResource(resources.getIdentifier(unit.image, "drawable", context!!.packageName))
            civilName.text = unit.name
            costWood.text = unit.cost?.wood ?: "-"
            costMeat.text = unit.cost?.food ?: "-"
            costStone.text = unit.cost?.stone ?: "-"
            costGold.text = unit.cost?.gold ?: "-"
            unitsLinearLayout.addView(view)
        }
    }

    override fun showTechnologies(list: List<Technology>) {
        technologiesLinearLayout.removeAllViews()
        for (tech in list){
            val view = LayoutInflater.from(context!!).inflate(R.layout.tech_list_row, null)
            val imageView = view.findViewById<ImageView>(R.id.logo)
            val civilName = view.findViewById<TextView>(R.id.name)
            val costWood  = view.findViewById<TextView>(R.id.costWood)
            val costMeat  = view.findViewById<TextView>(R.id.costMeat)
            val costStone  = view.findViewById<TextView>(R.id.costStone)
            val costGold  = view.findViewById<TextView>(R.id.costGold)
            val description = view.findViewById<TextView>(R.id.description)

            imageView.setImageResource(resources.getIdentifier(tech.image, "drawable", context!!.packageName))
            civilName.text = tech.name
            costWood.text = tech.cost?.wood ?: "-"
            costMeat.text = tech.cost?.food ?: "-"
            costStone.text = tech.cost?.stone ?: "-"
            costGold.text = tech.cost?.gold ?: "-"
            description.text = tech.description
            technologiesLinearLayout.addView(view)
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
