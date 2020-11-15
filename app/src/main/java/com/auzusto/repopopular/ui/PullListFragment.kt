package com.auzusto.repopopular.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.auzusto.repopopular.R
import com.auzusto.repopopular.model.PullsUrl
import com.auzusto.repopopular.service.ApiService
import com.auzusto.repopopular.service.ApiServiceBuilder
import com.auzusto.repopopular.viewmodel.PullListViewModel
import kotlinx.android.synthetic.main.fragment_pull_list.*
import org.koin.android.viewmodel.ext.android.viewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PullListFragment : Fragment(R.layout.fragment_pull_list), onPullClickListener {

    private val adapter = PullsAdapter(this)
    private val args by navArgs<PullListFragmentArgs>()
    private val viewModel : PullListViewModel by viewModel()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewModel()
        getPullRequests()
    }
    private fun observeViewModel() {
        viewModel.pullRequestsObservable.observe(viewLifecycleOwner, Observer {
            showData(it)
        })
    }

    private fun getPullRequests() {
        viewModel.getPulls(args.login, args.name)
    }

    private fun showData(body: List<PullsUrl>) {
        recyclerPullList.adapter = adapter
        adapter.notifyDataSetChanged()
        adapter.submitList(body)
    }

    override fun onPullClickListener(pullsUrl: PullsUrl) {
        super.onPullClickListener(pullsUrl)
    }

}