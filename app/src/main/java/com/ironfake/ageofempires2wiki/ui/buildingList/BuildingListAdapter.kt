package com.ironfake.ageofempires2wiki.ui.buildingList

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

class BuildingListAdapter(private var context: Context, var resources: Resources,
                          private var buildingNames: List<String>) : BaseAdapter() {

    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val layoutInflater = LayoutInflater.from(context)
        val view = layoutInflater.inflate(R.layout.list_view_row, null)
        val imageView = view.findViewById<ImageView>(R.id.civil_logo)
        val civilName = view.findViewById<TextView>(R.id.civil_name)

        var name: String = buildingNames[position]
        imageView.setImageResource(resources.getIdentifier("build_" +
                name.toLowerCase().replace(" ", "_"), "drawable", context.packageName))
        civilName.text = name
        return view
    }

    override fun getItem(position: Int): String {
        return buildingNames[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return buildingNames.size
    }
}