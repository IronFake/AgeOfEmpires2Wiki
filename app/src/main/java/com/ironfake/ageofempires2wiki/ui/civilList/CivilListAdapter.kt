package com.ironfake.ageofempires2wiki.ui.civilList

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.Resources
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.ironfake.ageofempires2wiki.R
import com.ironfake.ageofempires2wiki.model.aoeApiModels.Civilization

class CivilListAdapter(private var context: Context, var resources: Resources,
                       private var civilList: List<Civilization>) : BaseAdapter() {


    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val layoutInflater = LayoutInflater.from(context)
        val view = layoutInflater.inflate(R.layout.list_view_row, null)

        val imageView = view.findViewById<ImageView>(R.id.civil_logo)
        val civilName = view.findViewById<TextView>(R.id.civil_name)

        var civil: Civilization = civilList[position]

        imageView.setImageResource(resources.getIdentifier(civil.image, "drawable", context.packageName))
        civilName.text = civil.name
        return view
    }

    override fun getItem(position: Int): Civilization {
        return civilList[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return civilList.size
    }
}