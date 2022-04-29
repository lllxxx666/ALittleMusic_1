package com.example.alittlemusic.util

import android.os.Bundle
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner
import com.example.alittlemusic.data.logic.network.Repository.Repository

/**
 *
 * @author liuxin
 * Created on 2022/4/6
 * @description：---
 */
@Suppress("UNCHECKED_CAST")
class ViewModelFactory constructor(
    private val repository: Repository,
    owner: SavedStateRegistryOwner,
    defaultArgs: Bundle? = null
) :AbstractSavedStateViewModelFactory(owner, defaultArgs){
    override fun <T : ViewModel?> create(
        key: String,
        modelClass: Class<T>,
        handle: SavedStateHandle
    )= with( modelClass ){
//        when {
//            isAssignableFrom(MineViewModel::class.java) ->
//                MineViewModel(repository)
//            else ->
//                throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
//        }
    } as T
}