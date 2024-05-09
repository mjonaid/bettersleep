package com.task.bettersleep

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.task.bettersleep.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
//findNavController().navigate(R.id.action_home2_to_musicHome)
        // Initialize NavController
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.main_fragment_container_view) as NavHostFragment
        navController = navHostFragment.navController
        binding.bottomNavigation.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.menu_home -> {
                    navigateToDestination(R.id.home_nav_graph)
                    true
                }
                R.id.menu_sounds -> {
                    navigateToDestination(R.id.sounds) // Navigate to Sounds fragment
                    true
                }
                R.id.menu_sleep -> {
                    navigateToDestination(R.id.home_nav_graph) // Navigate to Sleep fragment
                    true
                }
                R.id.menu_journal -> {
                    navigateToDestination(R.id.journal) // Navigate to Journal fragment
                    true
                }
                R.id.menu_profile -> {
//                    navigateToDestination(R.id.profile) // Navigate to Profile fragment
                    true
                }
                else -> false
            }
        }
    }
    private fun navigateToDestination(destinationId: Int) {
        navController.navigate(destinationId)
    }
}
