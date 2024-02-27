package com.example.proyekgithubuser.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.proyekgithubuser.UsersItem
import com.example.proyekgithubuser.databinding.FragmentTabBinding

class TabFragment : Fragment() {
    private lateinit var binding: FragmentTabBinding
    private val detailViewModel: DetailViewModel by activityViewModels()
    private var position: Int? = null
    private var username: String? = null

    companion object {
        const val ARG_POSITION = "param1"
        const val ARG_USERNAME = "param2"
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            position = it.getInt(ARG_POSITION)
            username = it.getString(ARG_USERNAME)
        }
        val layoutManager = LinearLayoutManager(requireActivity())
        binding.rvFollow.layoutManager = layoutManager
        val itemDecoration = DividerItemDecoration(requireActivity(), layoutManager.orientation)
        binding.rvFollow.addItemDecoration(itemDecoration)
        binding.rvFollow.setHasFixedSize(true)

        if (position == 1){
            detailViewModel.findUserFollower(username!!)
            detailViewModel.userFollower.observe(viewLifecycleOwner){
                followList -> setFollowData(followList)
            }
            detailViewModel.isLoadingFollow.observe(viewLifecycleOwner, {
                showLoadingTab(it)
            })
        } else {
            detailViewModel.findUserFollowing(username!!)
            detailViewModel.userFollowing.observe(viewLifecycleOwner){
                followList -> setFollowData(followList)
            }
            detailViewModel.isLoadingFollow.observe(viewLifecycleOwner, {
                showLoadingTab(it)
            })
        }

    }

    private fun setFollowData(userFollow: List<UsersItem>) {
        val adapter = ListFollowAdapter(userFollow)
        binding.rvFollow.adapter = adapter
    }

    private fun showLoadingTab(isLoading: Boolean) {
        binding.progressBarTab.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTabBinding.inflate(layoutInflater)

        return binding.root
    }
}