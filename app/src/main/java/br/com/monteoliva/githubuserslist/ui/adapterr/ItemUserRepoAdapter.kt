package br.com.monteoliva.githubuserslist.ui.adapterr

import java.util.*

import java.math.RoundingMode
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

import br.com.monteoliva.githubuserslist.R
import br.com.monteoliva.githubuserslist.repository.core.extensions.validation
import br.com.monteoliva.githubuserslist.repository.model.repositories.UserRepositoriesItem
import br.com.monteoliva.githubuserslist.ui.components.BoxData

class ItemUserRepoAdapter : RecyclerView.Adapter<ItemUserRepoAdapter.ViewHolder>()  {
    private var list = emptyList<UserRepositoriesItem>().toMutableList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_repo_list, parent, false)
        return ViewHolder(view)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateList(items: MutableList<UserRepositoriesItem>?) {
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
        val item: UserRepositoriesItem = getItem(position)
        var stars = 0.00
        var forks = 0.00

        item.stargazersCount?.let { stars = it.toDouble() / 1000 }
        item.forksCount?.let      { forks = it.toDouble() / 1000 }

        holder.apply {
            repoName.text  = item.name?.validation()
            starsNumber.setValue(roundOffDecimal(stars) + "k")
            forksNumber.setValue(roundOffDecimal(forks) + "k")
        }
    }

    private fun getItem(position: Int): UserRepositoriesItem = list[position]

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val repoName: TextView   = itemView.findViewById(R.id.repoName)
        val starsNumber: BoxData = itemView.findViewById(R.id.starsNumber)
        val forksNumber: BoxData = itemView.findViewById(R.id.forksNumber)
    }

    private fun roundOffDecimal(number: Double): String {
        DecimalFormat("##.#", DecimalFormatSymbols(Locale.US)).apply {
            roundingMode = RoundingMode.FLOOR
            return format(number)
        }
    }
}