package co.edu.sena.projectupmobile

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import co.edu.sena.projectupmobile.databinding.ActivityNavegationBinding
import co.edu.sena.projectupmobile.databinding.FragmentHomeBinding
import co.edu.sena.projectupmobile.databinding.NavHeaderNavegationBinding
import com.google.android.material.navigation.NavigationView
import com.google.android.material.snackbar.Snackbar
import com.huawei.hms.support.hwid.result.AuthHuaweiId

class NavegationActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    lateinit var binding: ActivityNavegationBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityNavegationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val huaweiAccount = intent.extras?.getParcelable<AuthHuaweiId>("account")



        setSupportActionBar(binding.appBarNavegation.toolbar)

        binding.appBarNavegation.fab.setOnClickListener { view ->
            val intent = Intent(this, crearEvento_Activity::class.java)
            startActivity(intent)
        }
        val drawerLayout: DrawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        // val headerNav: NavHeaderNavegationBinding
        val navController = findNavController(R.id.nav_host_fragment_content_navegation)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.home, R.id.nav_gallery, R.id.nav_slideshow
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.navegation, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_navegation)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}