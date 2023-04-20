package br.com.monteoliva.githubuserslist.ui.adapterr

import java.util.*

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

import br.com.monteoliva.githubuserslist.R
import br.com.monteoliva.githubuserslist.repository.core.extensions.loadImage
import br.com.monteoliva.githubuserslist.repository.core.extensions.validation
import br.com.monteoliva.githubuserslist.repository.model.UserItem

class ItemUserListAdapter : RecyclerView.Adapter<ItemUserListAdapter.ViewHolder>()  {
    private var list = emptyList<UserItem>().toMutableList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_user_list, parent, false)
        return ViewHolder(view)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateList(items: MutableList<UserItem>?) {
        items?.let {
            if (it.isNotEmpty()) {
                list.clear()
                list.addAll(it)
                notifyDataSetChanged()
            }
        }
    }

    override fun getItemCount(): Int = list.size
    override fun getItemId(position: Int): Long = list[position].id?.toLong()!!

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item: UserItem = getItem(position)

        holder.apply {
            ownerName.text = item.login?.validation()
            itemView.apply {
                item.avatarUrl.let { ownerImage.loadImage(context, it.toString()) }
            }
        }
    }

    private fun getItem(position: Int): UserItem = list[position]

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val ownerName: TextView   = itemView.findViewById(R.id.ownerName)
        val ownerImage: ImageView = itemView.findViewById(R.id.ownerImage)
    }
}