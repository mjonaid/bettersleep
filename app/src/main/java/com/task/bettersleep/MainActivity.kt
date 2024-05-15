package com.task.bettersleep

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.task.bettersleep.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private lateinit var viewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java] // Initialize ViewModel
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.main_fragment_container_view) as NavHostFragment
        navController = navHostFragment.navController
        binding.apply {
            cvMediaPlayer.visibility=View.GONE
            bottomNavigation.setOnItemSelectedListener { menuItem ->
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
                        navigateToDestination(R.id.sleep) // Navigate to Sleep fragment
                        true
                    }
                    R.id.menu_journal -> {
                        navigateToDestination(R.id.journal) // Navigate to Journal fragment
                        true
                    }
                    R.id.menu_profile -> {
                        navigateToDestination(R.id.profile)
                        true
                    }
                    else -> false
                }
            }
            viewModel.isMediaPlayerPlaying.observe(this@MainActivity) { isPlaying ->
                cvMediaPlayer.visibility = if (isPlaying) View.VISIBLE else View.GONE
            }
//            upwardClick.setIconResource(R.drawable.baseline_pause_24)
            upwardClick.setOnClickListener{
                if (MediaPlayerManager.isPlaying()) {
                    upwardClick.setIconResource(R.drawable.baseline_pause_24)
                    MediaPlayerManager.stop()
                } else {
                    MediaPlayerManager.restart()
                    upwardClick.setIconResource(R.drawable.videolibtunel)
                }
            }

        }
    }
    private fun navigateToDestination(destinationId: Int) {
        navController.navigate(destinationId)
    }
}
