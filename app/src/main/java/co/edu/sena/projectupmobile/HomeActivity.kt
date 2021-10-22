package co.edu.sena.projectupmobile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import co.edu.sena.projectupmobile.databinding.ActivityHomeBinding
import co.edu.sena.projectupmobile.databinding.ActivityMainBinding

class HomeActivity : AppCompatActivity() {

    lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val recyclerView = binding.recycleView
        val adapter = CustomerAdapterCard()

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter= adapter

    }
}