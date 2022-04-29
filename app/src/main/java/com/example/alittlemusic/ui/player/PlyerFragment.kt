package com.example.alittlemusic.ui.player

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.alittlemusic.R

class PlyerFragment : Fragment() {

    companion object {
        fun newInstance() = PlyerFragment()
    }

    private lateinit var viewModel: PlyerViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.plyer_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(PlyerViewModel::class.java)
        // TODO: Use the ViewModel
    }

}