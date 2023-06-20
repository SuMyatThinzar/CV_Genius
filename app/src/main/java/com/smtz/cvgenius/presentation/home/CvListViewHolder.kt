package com.smtz.cvgenius.presentation.home

import android.annotation.SuppressLint
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import com.smtz.cvgenius.R
import com.smtz.cvgenius.databinding.ViewHolderCvListBinding
import com.smtz.cvgenius.domain.model.CvVO

@SuppressLint("RestrictedApi")
class CvListViewHolder(private var binding: ViewHolderCvListBinding, private val delegate: CvDelegate) : RecyclerView.ViewHolder(binding.root) {

    private var mDataVO : CvVO ? = null

    init {
//        itemView.setOnClickListener {
//            mDataVO?.let {
//                delegate.onTapCv(it.cvId)
//            }
//        }
        binding.btnEditCv.setOnClickListener {
            mDataVO?.let {
                delegate.onTapCv(it)
            }
        }
        binding.btnViewCv.setOnClickListener {
            mDataVO?.let {
                delegate.onTapViewCv(it)
            }
        }
        binding.btnOptions.setOnClickListener { view ->
            // 1. create option_menu.xml with actionViewClass -> val popupMenu
            // 2. popup_menu.xml with ListView (esp for menu's width, height, divider, etc just like rv) -> listView
            // 3. menu_item_layout.xml (for stylizing one menu's view just like view holder) -> inside Adapter
            // 4. CustomMenuAdapter.kt (for changing dynamically for every menu's text and image) -> customMenuAdapter

            val popupWindow = PopupWindow(itemView.context)
            val contentView = LayoutInflater.from(itemView.context).inflate(R.layout.popup_menu, null)
            val listView = contentView.findViewById<ListView>(R.id.menu_list_view)

            val popupMenu = PopupMenu(itemView.context, view)
            popupMenu.inflate(R.menu.option_menu)

            val menu = popupMenu.menu

            // Set up the custom adapter
            val customMenuAdapter = CustomMenuAdapter(itemView.context, menu)
            listView.adapter = customMenuAdapter

            // Set item click listener
            listView.setOnItemClickListener { parent, view, position, id ->
                val menuItem = menu.getItem(position)

                mDataVO?.let {
                    when (menuItem.itemId) {
                        R.id.action_delete -> {
                            delegate.onTapDeleteCv(it.cvId)
                        }
                        R.id.action_duplicate -> {
                            delegate.onTapDuplicate(it)
                        }
//                        R.id.action_share -> {
//                            delegate.onTapShare(it.templateId)
//                        }
                    }
                }
                popupWindow.dismiss()
            }

            // Set the content view and dimensions of the popup window
            popupWindow.contentView = contentView
            popupWindow.width = 500
            popupWindow.height = ViewGroup.LayoutParams.WRAP_CONTENT
            popupWindow.isFocusable = true

            // Show the popup window
            popupWindow.showAsDropDown(view)
        }
    }

    fun bindData(data: CvVO) {
        mDataVO = data

        val name = "${data.personalDetails?.firstName} ${data.personalDetails?.lastName}"
        binding.tvUserName.text = name
        binding.tvEmail.text = data.personalDetails?.email

        if (data.profileImage != null){
            val bitmap = BitmapFactory.decodeByteArray(data.profileImage, 0, data.profileImage!!.size)
            binding.ivProfile.setImageBitmap(bitmap)
        } else {
            binding.ivProfile.setImageResource(R.drawable.ic_select_photo)
        }
    }
}