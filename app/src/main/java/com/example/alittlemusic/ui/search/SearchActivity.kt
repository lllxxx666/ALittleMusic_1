package com.example.alittlemusic.ui.search

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.View
import android.widget.ProgressBar
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.example.alittlemusic.R
import com.example.alittlemusic.data.Adapter.TabPagerAdapter
import com.example.alittlemusic.databinding.ActivitySearchableBinding
import com.google.android.material.tabs.TabLayoutMediator

class SearchActivity : AppCompatActivity() {
    companion object {
        fun actionStart(context: Context, query: String) {
            val intent = Intent(context, SearchActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            intent.putExtra("query", query)
            context.startActivity(intent)
        }
    }

    private lateinit var binding: ActivitySearchableBinding
    private val viewModel by lazy { ViewModelProvider(this).get(SearchViewModel::class.java) }

    private lateinit var adapter: TabPagerAdapter

    private val tabs = arrayOf(
        "综合",
        "歌曲",
        "歌单"
    )

    override fun onMenuOpened(featureId: Int, menu: Menu): Boolean {
        return super.onMenuOpened(featureId, menu)
        Log.d("test","菜单打开")
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        if (intent != null) handleIntent(intent)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchableBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Log.d("test","SearchActivity：onCreate")
        handleIntent(intent)

        setSupportActionBar(binding.searchToolbar)
        supportActionBar?.let {
            it.setDisplayHomeAsUpEnabled(true)
            it.title =null
        }
        binding.searchToolbar.setNavigationOnClickListener {
            finish()
        }

//        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
//        binding.searchView.apply {
//            setSearchableInfo(searchManager.getSearchableInfo(componentName))
////            this.isSubmitButtonEnabled = true
//            isIconified = false
//        }

//        TabLayout + ViewPager2
        adapter = TabPagerAdapter(this)
        initTab()


    }

    private fun initTab() {
        val fragments = ArrayList<Fragment>()

        tabs.indices.map { i ->
            fragments.add(SearchFragment.newInstance(i))
        }

        adapter.setNewFragments(fragments)
        binding.searchContext.adapter = adapter

        TabLayoutMediator(binding.searchResultType, binding.searchContext) { tab, position ->
            when (position) {
                0 -> {
                    tab.text = tabs[0]
                }
                1 -> {
                    tab.text = tabs[1]
                }
                2 -> {
                    tab.text = tabs[2]
                }
            }
        }.attach()

        binding.searchContext.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {

        })
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.options_menu, menu)
        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        (menu.findItem(R.id.menu_search).actionView as SearchView).apply {
            setSearchableInfo(searchManager.getSearchableInfo(componentName))
//            放大镜在搜索框外；右侧无×，输入文字后有×；自动获得焦点
            isIconified = false
            isIconifiedByDefault = false
            onActionViewExpanded()
            setIconifiedByDefault(false)
            setSubmitButtonEnabled(true)

        }
        return true
    }


    override fun onDestroy() {
        super.onDestroy()
        handleIntent(intent)
        Log.d("test","SearchActivity：onDestroy")
    }

    private fun handleIntent(intent: Intent) {

        if (Intent.ACTION_SEARCH == intent.action) {
            val query = intent.getStringExtra(SearchManager.QUERY)
            //use the query to search your data somehow
            Log.d("test","执行搜索："+query)
            viewModel.search(query!!)
            binding.searchBefore.visibility = View.GONE         //这个在整个生命周期只出现一次，消失一次
            binding.searchLoading.visibility = View.VISIBLE     //在每次调用搜索时，都要显示，在结果出现时消失
            binding.searchAfter.visibility = View.VISIBLE       //在每次调用搜索时，都会重新显示
        }
    }
}