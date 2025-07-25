package com.example.gymlog.ui

        import android.os.Bundle
        import androidx.appcompat.app.AppCompatActivity
        import androidx.navigation.NavController
        import androidx.navigation.fragment.NavHostFragment
        import androidx.navigation.ui.AppBarConfiguration
        import androidx.navigation.ui.setupActionBarWithNavController
        import com.example.gymlog.R
        import dagger.hilt.android.AndroidEntryPoint

        @AndroidEntryPoint
        class MainActivity : AppCompatActivity() {
            private lateinit var navController: NavController

            override fun onCreate(savedInstanceState: Bundle?) {
                super.onCreate(savedInstanceState)
                setContentView(R.layout.activity_main)

                val navHostFragment = supportFragmentManager
                    .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
                navController = navHostFragment.navController

                val appBarConfiguration = AppBarConfiguration(navController.graph)
                setupActionBarWithNavController(navController, appBarConfiguration)
            }

            override fun onSupportNavigateUp(): Boolean {
                return navController.navigateUp() || super.onSupportNavigateUp()
            }
        }