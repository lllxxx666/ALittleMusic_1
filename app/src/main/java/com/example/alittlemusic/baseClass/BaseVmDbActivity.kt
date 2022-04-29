package com.example.alittlemusic.baseClass

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider

/**
 *
 * @author liuxin
 * Created on 2022/3/28
 * @description：---根据MVVM设计的Activity
 */
abstract class BaseVmDbActivity<VM : BaseViewModel, DB : ViewDataBinding> : AppCompatActivity() {
    lateinit var viewModel: VM
    lateinit var dataBinding: DB
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewDataBinding() //初始化 DataBinding
        initViewModel()//初始化 ViewModel
        initView(savedInstanceState)//初始化控件
        initData()//初始化数据
        ActivityCollector.addActivity(this)
    }
    abstract fun getLayoutId(): Int //获取布局文件
    abstract fun viewModelClass(): Class<VM>//获取 ViewModel 类
    abstract fun initView(savedInstanceState: Bundle?)
    abstract fun initData()

    private fun initViewDataBinding() {
        dataBinding = DataBindingUtil.setContentView(this, getLayoutId())
        dataBinding.lifecycleOwner = this
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this).get(viewModelClass())
    }

    override fun onDestroy() {
        super.onDestroy()
        ActivityCollector.removeActivity(this)
    }
}