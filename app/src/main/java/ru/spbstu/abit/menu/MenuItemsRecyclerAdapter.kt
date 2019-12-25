package ru.spbstu.abit.menu

import android.graphics.drawable.GradientDrawable
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.list_item_menu.view.*
import ru.spbstu.abit.R

import ru.spbstu.abit.util.getColorId

class MenuItemsRecyclerAdapter<T : Parcelable> (
    private val menuListItems: List<MenuListItem<T>>,
    private val listener: OnMenuAdapterInteractionListener?
) : RecyclerView.Adapter<MenuItemsRecyclerAdapter<T>.ViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.list_item_menu,
            parent,
            false
        )

        return ViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        val item = menuListItems[position]

        with(holder) {
            if (position == menuListItems.size - 1) {
                separator.visibility = View.GONE
            }

            this.item = item
            title.text = item.title
            icon.setColorFilter(getColorId(icon.context, item.iconColorId))
            icon.setImageResource(item.iconId)

            val drawable = GradientDrawable()
            drawable.shape = GradientDrawable.OVAL
            drawable.setColor(getColorId(icon.context, item.iconBackgroundColorId))
            iconHolder.background = drawable

            view.setOnClickListener { listener?.onMenuItemSelected(this.item) }
        }
    }

    override fun getItemCount(): Int {
        return menuListItems.size
    }

    inner class ViewHolder (val view: View) : RecyclerView.ViewHolder(view) {
        val separator: View = view.menu_item_separator
        val title: TextView = view.menu_item_title_text
        val iconHolder: ConstraintLayout = view.menu_item_icon_holder
        val icon: ImageView = view.menu_item_icon_image

        lateinit var item: MenuListItem<*>
    }

    interface OnMenuAdapterInteractionListener {
        fun onMenuItemSelected(menuListItem: MenuListItem<*>)
    }
}