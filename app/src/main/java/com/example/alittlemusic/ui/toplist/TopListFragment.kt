package com.example.alittlemusic.ui.toplist

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.alittlemusic.databinding.FragmentToplistBinding
import com.example.alittlemusic.data.Adapter.TopListAdapter
import com.example.alittlemusic.util.getViewModelFactory

class TopListFragment : Fragment() {

    private var _binding: FragmentToplistBinding? = null

    private val viewModel by viewModels<TopListViewModel> { getViewModelFactory() }
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private lateinit var adapter : TopListAdapter

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        val viewModel =
                ViewModelProvider(this).get(TopListViewModel::class.java)

        _binding = FragmentToplistBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val layoutManager = LinearLayoutManager(activity)
        binding.OfficialRankings.layoutManager = layoutManager
        adapter = TopListAdapter(this,viewModel.topList)
        binding.OfficialRankings.adapter = adapter

        viewModel.topListLiveData.observe(viewLifecycleOwner, Observer { result ->
            val list = result.getOrNull()
            if (list != null){
                viewModel.topList.clear()
                viewModel.topList.addAll(list)
                adapter.notifyDataSetChanged()
                showTopList()
                Log.d("test","我得到了排行榜"+list.toString())
            }

        })


//        val textView: TextView = binding.textDashboard
//        dashboardViewModel.text.observe(viewLifecycleOwner) {
//            textView.text = it
//        }
        return root
    }

    private fun showTopList() {
        binding.OfficialRankings.visibility = View.VISIBLE
        binding.toplistLoading.visibility = View.GONE
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}