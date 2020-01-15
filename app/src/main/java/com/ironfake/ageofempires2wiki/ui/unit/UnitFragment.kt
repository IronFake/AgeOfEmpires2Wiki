package com.ironfake.ageofempires2wiki.ui.unit

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
import com.ironfake.ageofempires2wiki.model.aoeApiModels.Building
import com.ironfake.ageofempires2wiki.utils.TAG
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import kotlin.properties.Delegates

class UnitFragment : Fragment(), UnitContact.View {

    @Inject
    lateinit var presenter: UnitContact.Presenter

    private lateinit var createdInLinearLayout: LinearLayout

    var position by Delegates.notNull<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectDependency()
        presenter.attach(this, context!!)
        val bundle = this.arguments
        if (bundle != null) {
            position = bundle.getInt("position", android.R.attr.defaultValue)

        }
    }

    private fun injectDependency() {
        val unitComponent = DaggerFragmentComponent.builder()
            .build()
        unitComponent.inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_unit, container, false)
        val unitName: TextView = view.findViewById(R.id.unit_name_one)
        val unitLogo: ImageView = view.findViewById(R.id.unit_logo)
        val expansion: TextView = view.findViewById(R.id.expansion)
        val age: TextView = view.findViewById(R.id.age)
        val description: TextView = view.findViewById(R.id.description)
        createdInLinearLayout =  view.findViewById(R.id.createdIn)
        val buildTime: TextView = view.findViewById(R.id.buildTime)
        val reloadTime: TextView = view.findViewById(R.id.reloadTime)
        val attackDelay: TextView = view.findViewById(R.id.attackDelay)
        val movementRate: TextView = view.findViewById(R.id.movementRate)
        val lineOfSight: TextView = view.findViewById(R.id.lineOfSight)
        val hitPoints: TextView = view.findViewById(R.id.hitPoints)
        val range: TextView = view.findViewById(R.id.range)
        val attack: TextView = view.findViewById(R.id.attack)
        val armor: TextView = view.findViewById(R.id.armor)
        val accuracy: TextView = view.findViewById(R.id.accuracy)
        val costWood: TextView = view.findViewById(R.id.costWood)
        val costMeat: TextView = view.findViewById(R.id.costMeat)
        val costStone: TextView = view.findViewById(R.id.costStone)
        val costGold: TextView = view.findViewById(R.id.costGold)

        presenter.getData(position)?.let {
            presenter.getData(position)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe( {it ->
                    presenter.getBuilding(it.createdIn)
                    unitName.text = it.name
                    unitLogo.setImageResource(resources.getIdentifier(it.image, "drawable", context!!.packageName))
                    expansion.text = it.expansion
                    age.text = it.age
                    description.text = it.description
                    buildTime.text = it.buildTime ?: "-"
                    reloadTime.text = it.reloadTime ?: "-"
                    attackDelay.text = it.attackDelay ?: "-"
                    movementRate.text = it.movementRate ?: "-"
                    lineOfSight.text = it.lineOfSight ?: "-"
                    hitPoints.text = it.hitPoints ?: "-"
                    range.text = it.range ?: "-"
                    attack.text = it.attack ?: "-"
                    armor.text = it.armor ?: "-"
                    accuracy.text = it.accuracy ?: "-"
                    costWood.text = it.cost?.wood ?: "-"
                    costMeat.text = it.cost?.food ?: "-"
                    costStone.text = it.cost?.stone ?: "-"
                    costGold.text = it.cost?.gold ?: "-"
                }, {
                    error -> Log.e(TAG, error.printStackTrace().toString())
                })
        }
        // Inflate the layout for this fragment
        return view
    }

    override fun showCreateIn(building: Building) {
        createdInLinearLayout.removeAllViews()
        val view = LayoutInflater.from(context!!).inflate(R.layout.list_view_row, null)
        view.setOnClickListener {
            presenter.setFragment(building.name)
        }
        val imageView = view.findViewById<ImageView>(R.id.civil_logo)
        val civilName = view.findViewById<TextView>(R.id.civil_name)

        imageView.setImageResource(resources.getIdentifier(building.image, "drawable", context!!.packageName))
        civilName.text = building.name
        createdInLinearLayout.addView(view)
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
