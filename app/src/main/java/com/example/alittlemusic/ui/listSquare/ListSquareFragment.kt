package com.example.alittlemusic.ui.listSquare

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.alittlemusic.R

class ListSquareFragment : Fragment() {

    companion object {
        fun newInstance() = ListSquareFragment()
    }

    private lateinit var viewModel: ListSquareViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.list_square_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ListSquareViewModel::class.java)
        // TODO: Use the ViewModel
    }

}