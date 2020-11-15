package com.auzusto.repopopular.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.auzusto.repopopular.R
import com.auzusto.repopopular.model.PullsUrl
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.pull_item_view.view.*

class PullsAdapter(private val onPullClickListener: onPullClickListener)
    : ListAdapter<PullsUrl, PullsAdapter.ViewHolder>(PULLS_COMPARATOR) {

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {

        private val pullName = view.textPullName
        private val pullDescription = view.textPullDescription
        private val login = view.textPullLogin
        private val avatar = view.imagePullAvatar

        fun bind(pullsUrl: PullsUrl){
            pullName.text = pullsUrl.title
            pullDescription.text = pullsUrl.body
            login.text = pullsUrl.user.login
            Glide.with(avatar.context)
                .load(pullsUrl.user.avatarUrl)
                .placeholder(R.drawable.ic_launcher_background)
                .into(avatar)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.pull_item_view, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val pullsUrl = getItem(position)
        pullsUrl?.let {
            holder.bind(it)
            onPullClickListener.onPullClickListener(it)
        }
    }

    companion object {
        private val PULLS_COMPARATOR = object: DiffUtil.ItemCallback<PullsUrl>() {
            override fun areItemsTheSame(oldItem: PullsUrl, newItem: PullsUrl): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: PullsUrl, newItem: PullsUrl): Boolean =
                newItem == oldItem
        }
    }

}