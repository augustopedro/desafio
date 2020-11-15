package com.auzusto.repopopular.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.auzusto.repopopular.R
import com.auzusto.repopopular.model.Items
import com.auzusto.repopopular.viewmodel.ItemsViewModel
import kotlinx.android.synthetic.main.fragment_repo_list.*
import kotlinx.android.synthetic.main.repo_item_view.*

class RepoListFragment : Fragment(R.layout.fragment_repo_list), onRepoClickListener {

    val adapter = ItemsAdapter(this)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        configRepoList()
        sendList()
    }

    private fun configRepoList() {
        recyclerView.adapter = adapter
        adapter.notifyDataSetChanged()
    }

    private fun sendList() {
        val itemViewModel = ViewModelProviders.of(this)
            .get(ItemsViewModel::class.java)
        itemViewModel.itemsPagedList.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
        })
    }

    override fun onRepoClickListener(items: Items) {
        val login = textRepoName.text.toString()
        val name = textLogin.text.toString()
        val action = RepoListFragmentDirections.actionRepoListFragmentToPullListFragment(name, login)
        findNavController().navigate(action)
    }

}