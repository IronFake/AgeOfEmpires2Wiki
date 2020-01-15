package com.ironfake.ageofempires2wiki.ui.unitsList

import android.content.Context
import android.content.res.Resources
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import android.widget.ExpandableListView
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.ironfake.ageofempires2wiki.R
import com.ironfake.ageofempires2wiki.model.aoeApiModels.Unit

class UnitListAdapter(val context: Context, private val resources: Resources,
                      private val listGroup : List<Int>, private val listItem : HashMap<Int, List<Unit>>,
                      private val expandableListView: ExpandableListView) : BaseExpandableListAdapter() {


    override fun getGroup(groupPosition: Int): Int {
        return listGroup[groupPosition]
    }

    override fun isChildSelectable(groupPosition: Int, childPosition: Int): Boolean {
        return true
    }

    override fun hasStableIds(): Boolean {
        return false
    }

    override fun getGroupView(groupPosition: Int, isExpanded: Boolean, convertView: View?, parent: ViewGroup?): View {
        val layoutInflater = LayoutInflater.from(context)
        val view = layoutInflater.inflate(R.layout.list_group, null)

        // Expand and collapse listView after click
        val groupName = view.findViewById<TextView>(R.id.list_parent_text_view)
        groupName.setOnClickListener {
            if (expandableListView.isGroupExpanded(groupPosition)){
                expandableListView.collapseGroup(groupPosition)
            }
            else
                expandableListView.expandGroup(groupPosition)
        }

        val imageView = view.findViewById<ImageView>(R.id.list_parent_image_view)
        var groupImage: Int =  getGroup(groupPosition)

        imageView.setImageDrawable(ContextCompat.getDrawable(context, groupImage))
        when(groupImage){
            R.drawable.age_dark -> groupName.text = "Dark age"
            R.drawable.age_feudal -> groupName.text = "Feudal age"
            R.drawable.age_castle -> groupName.text = "Castle age"
            R.drawable.age_imperial -> groupName.text = "Imperial age"
        }
            //expandableListView.expandGroup(groupPosition)
        return view
    }

    override fun getChildrenCount(groupPosition: Int): Int {
        return listItem[listGroup[groupPosition]]!!.size
    }

    override fun getChild(groupPosition: Int, childPosition: Int): Unit {
        return listItem[listGroup[groupPosition]]!![childPosition]
    }

    override fun getGroupId(groupPosition: Int): Long {
        return groupPosition.toLong()
    }

    override fun getChildView(groupPosition: Int, childPosition: Int, isLastChild: Boolean, convertView: View?, parent: ViewGroup?): View {
        val layoutInflater = LayoutInflater.from(context)
        val view = layoutInflater.inflate(R.layout.list_view_row, null)

        val imageView = view.findViewById<ImageView>(R.id.civil_logo)
        val unitName = view.findViewById<TextView>(R.id.civil_name)

        var unit : Unit = getChild(groupPosition, childPosition)
        imageView.setImageResource(resources.getIdentifier(unit.image, "drawable", context.packageName))
        unitName.text = unit.name

        return view
    }

    override fun getChildId(groupPosition: Int, childPosition: Int): Long {
       return childPosition.toLong()
    }

    override fun getGroupCount(): Int {
        return listGroup.size
    }
}