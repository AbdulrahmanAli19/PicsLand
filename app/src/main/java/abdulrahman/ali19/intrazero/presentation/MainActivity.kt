package abdulrahman.ali19.intrazero.presentation

import abdulrahman.ali19.intrazero.R
import abdulrahman.ali19.intrazero.databinding.ActivityMainBinding
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val navController: NavController = findNavController(R.id.nav_host_fragment_activity_main)


        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home
            )
        )

        setupActionBarWithNavController(navController, appBarConfiguration)
    }


}
