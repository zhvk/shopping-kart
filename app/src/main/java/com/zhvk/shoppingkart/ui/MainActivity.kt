package com.zhvk.shoppingkart.ui

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import com.zhvk.shoppingkart.R
import com.microsoft.appcenter.AppCenter;
import com.microsoft.appcenter.analytics.Analytics;
import com.microsoft.appcenter.crashes.Crashes;

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // TODO: appSecret is PUBLIC at the moment which you shouldn't do! This is done just for
        //  presentation purposes.
        AppCenter.start(
            application,
            "\"1b385950-78f9-4a11-a1ef-6e7744b21946\"",
            Analytics::class.java,
            Crashes::class.java
        )

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
        setupActionBarWithNavController(navController)
        supportActionBar?.hide()

        // TODO: Should be removed, this code is here just for testing purposes
        getPreferences(Context.MODE_PRIVATE).edit().clear().apply()

        Analytics.trackEvent("App started")
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}