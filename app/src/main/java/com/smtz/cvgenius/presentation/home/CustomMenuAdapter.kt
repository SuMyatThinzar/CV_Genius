package com.smtz.cvgenius.presentation.home

import android.content.Context
import android.view.*
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.smtz.cvgenius.R

class CustomMenuAdapter(private val context: Context, private val menu: Menu) : BaseAdapter() {

    override fun getCount(): Int {
        return menu.size()
    }

    override fun getItem(position: Int): MenuItem {
        return menu.getItem(position)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View = convertView ?: LayoutInflater.from(context).inflate(R.layout.menu_item_layout, parent, false)
        val menuItem = getItem(position)

        val iconView = view.findViewById<ImageView>(R.id.menu_item_icon)
        val titleView = view.findViewById<TextView>(R.id.menu_item_title)

        iconView.setImageResource(getIconResourceForMenuItem(position))
        titleView.text = menuItem.title

        return view
    }

    private fun getIconResourceForMenuItem(itemId: Int) : Int{
        return when (itemId) {
            0-> R.drawable.ic_delete
            1-> R.drawable.ic_copy
//            2-> R.drawable.ic_share
            else -> 0
        }
    }
}
