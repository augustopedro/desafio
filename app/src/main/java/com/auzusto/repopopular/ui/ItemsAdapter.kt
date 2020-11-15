package com.auzusto.repopopular.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.auzusto.repopopular.R
import com.auzusto.repopopular.model.Items
import com.auzusto.repopopular.model.Owner
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.repo_item_view.view.*
import org.jetbrains.annotations.NotNull


class ItemsAdapter(private val onRepoClickListener: onRepoClickListener)
    : PagedListAdapter<Items, ItemsAdapter.ViewHolder>(ITEMS_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.repo_item_view, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val items = getItem(position)
        items?.let {
            holder.bind(it)
            holder.itemView.setOnClickListener {
                getItem(position)?.let { it ->
                    onRepoClickListener.onRepoClickListener(it) }
            }

        }

    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        private val repoName = view.textRepoName
        private val repoDescrip = view.textDescription
        private val forksCount = view.textForksCount
        private val starsCount = view.textStarsCount
        private val login = view.textLogin
        private val avatar = view.imageAvatar

        fun bind(items: Items) {
            repoName.text = items.name
            repoDescrip.text = items.description
            forksCount.text = items.forksCount.toString()
            starsCount.text = items.stargazersCount.toString()
            login.text = items.owner.login

            Glide.with(avatar.context)
                .load(items.owner.avatarUrl)
                .placeholder(R.drawable.ic_launcher_background)
                .into(avatar)
        }
    }

    companion object {

        private val ITEMS_COMPARATOR = object: DiffUtil.ItemCallback<Items>() {
            override fun areItemsTheSame(oldItem: Items, newItem: Items): Boolean =
                oldItem.id == newItem.id

            @SuppressLint("DiffUtilEquals")
            override fun areContentsTheSame(oldItem: Items, newItem: Items): Boolean =
                newItem == oldItem

        }
    }

}