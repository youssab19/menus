package com.example.fragments.fragments

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.fragments.PrefsHelper
import com.example.fragments.R
import com.example.fragments.data.Menudata

class Recycler(val menu: ArrayList<Menudata>, val menuItemListener: MenuItemListener) :
    RecyclerView.Adapter<Recycler.ViewHolder>() {
    private lateinit var newaarry: ArrayList<Menudata>
    lateinit var image: Array<Int>
    lateinit var name: Array<String>
    var sel: Boolean = false

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val layoutStyle = PrefsHelper.getItemType(parent.context)
        val layoutId = if (layoutStyle == "grid") {
            R.layout.grid
        } else {
            R.layout.list
        }
        val view = inflater.inflate(layoutId, parent, false)
        return ViewHolder(view)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
         val currentItem = menu[position]
        holder.image.setImageResource(currentItem.image)
        holder.name.text = currentItem.name
        holder.itemView.setOnClickListener {
            menuItemListener.onMenuItemClick(currentItem)
        }
        holder.imgSel.setOnClickListener {
            if (currentItem.sel) {
                currentItem.sel = false
                holder.imgSel.colorFilter = null

            } else {
                currentItem.sel = true
                holder.imgSel.setColorFilter(Color.argb(255, 255, 0, 0))
            }
            notifyItemChanged(position)
        }

    }

    override fun getItemCount() = menu.size

    inner class ViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.findViewById(R.id.textView)
        val imgSel: ImageButton = itemView.findViewById(R.id.imageButton2)


        val image = itemView.findViewById<ImageView>(R.id.imageView2)
    }

    interface MenuItemListener {
        fun onMenuItemClick(menudata: Menudata)

    }


}











