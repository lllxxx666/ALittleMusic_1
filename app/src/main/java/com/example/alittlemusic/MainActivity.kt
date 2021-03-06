package com.example.alittlemusic

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.os.Process.killProcess
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.SearchView
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.bumptech.glide.Glide
import com.example.activitytest.BaseActivity
import com.example.activitytest.isDarkTheme
import com.example.alittlemusic.baseClass.MyApplication
import com.example.alittlemusic.baseClass.MyApplication.Companion.context
import com.example.alittlemusic.databinding.ActivityMainBinding
import com.example.alittlemusic.ui.search.SearchActivity
import com.example.alittlemusic.util.toast
import com.google.android.material.navigation.NavigationView

class MainActivity : BaseActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    companion object{
        fun actionStart(context: Context){
            val intent = Intent(context, MainActivity::class.java)
//            intent.flags = FILE_INTEGRITY_SERVICE
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //        ActionBar
        supportActionBar?.hide()
        val toolbar= binding.toolbar
        toolbar.title = null
        setSupportActionBar(toolbar)
        supportActionBar?.let {
            it.setDisplayHomeAsUpEnabled(true)
            it.title = null
        }
        if (Build.VERSION.SDK_INT >= 21) {
            val decorView = window.decorView
            decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
//            decorView.dispatchWindowVisibilityChanged()
            window.statusBarColor = Color.TRANSPARENT
        }

        val navView: BottomNavigationView = binding.navView
        val drawerLayout: DrawerLayout = binding.container
//        drawer menu
        val drawerNav = binding.drawerNavView
        drawerNav.bringToFront()
//        val toggle=ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close)
//        drawerLayout.addDrawerListener(toggle)
//        toggle.syncState()

        drawerNav.setNavigationItemSelectedListener(this)

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(setOf(
            R.id.navigation_home, R.id.navigation_toplist, R.id.navigation_mine)
            , drawerLayout)
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)


        binding.fakeSerarchview.setOnClickListener {
            SearchActivity.actionStart(this@MainActivity,"????????????")
        }

        initUserInfo()
    }

    private fun initUserInfo() {
//        val user_name = activity?.findViewById<TextView>(R.id.menu_user_name)
//        user_name?.text = getString(R.string.fake_user_name)
        val img = findViewById<ImageView>(R.id.menu_user_img)
        val url = "https://p4.music.126.net/PKBd8tfF2RSoZaRufP4uoA==/109951163340483924.jpg"
        if (img != null) {
            Glide.with(img).load(url).dontAnimate().into(img)
        }

    }

//    override fun onCreateOptionsMenu(menu: Menu): Boolean {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        menuInflater.inflate(R.menu.options_menu, menu)
//        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
//        (menu.findItem(R.id.menu_search).actionView as SearchView).apply {
//            setSearchableInfo(searchManager.getSearchableInfo(componentName))
//            isIconifiedByDefault = false
//            this.setOnClickListener {
//                SearchActivity.actionStart(MyApplication.context,"????????????")
//            }
//        }
//
//        return true
//    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    override fun onBackPressed() {
        val drawerLayout = binding.container
        if (drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START)
        }else{
            super.onBackPressed()
        }
    }



    override fun onMenuOpened(featureId: Int, menu: Menu): Boolean {
        return super.onMenuOpened(featureId, menu)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
//            R.id.menu_login -> LoginActivity.actionStart(MyApplication.context)
            R.id.menu_logout -> {
                toast("????????????")
                val editor = getSharedPreferences("data",Context.MODE_PRIVATE).edit()
                editor.let {
                    it.putBoolean("isLogin",false)
                    it.apply()
                }
                context.packageManager.getLaunchIntentForPackage(context.packageName)?.apply {
                    // ?????????????????????Activity
                    addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                    context.startActivity(this)
                }
//                ??????????????????
                android.os.Process.killProcess(android.os.Process.myPid())

            }
            R.id.menu_darkMode -> {
                if (isDarkTheme(this)) AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                else AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            }
            R.id.menu_timeClock -> Intent("com.example.activitytest.Timing_closure").let {
                sendBroadcast(it)
            }
        }
        return true
    }


}