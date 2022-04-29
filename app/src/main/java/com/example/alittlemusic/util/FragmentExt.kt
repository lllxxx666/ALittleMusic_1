package com.example.alittlemusic.util

import androidx.fragment.app.Fragment
import com.example.alittlemusic.data.logic.network.Repository.Repository

/**
 *
 * @author liuxin
 * Created on 2022/4/6
 * @description：---
 */
fun Fragment.getViewModelFactory(): ViewModelFactory {
    val repository = Repository
    return ViewModelFactory(repository, this)
}