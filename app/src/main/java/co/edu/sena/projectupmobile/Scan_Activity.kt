package co.edu.sena.projectupmobile

import android.Manifest
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import co.edu.sena.projectupmobile.databinding.ActivityScanBinding

class Scan_Activity : AppCompatActivity() {

    lateinit var binding: ActivityScanBinding

    private val PERMISSION_STORAGE = arrayOf<String>(
        Manifest.permission.CAMERA,
        Manifest.permission.READ_EXTERNAL_STORAGE
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityScanBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.bottonScan.setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(PERMISSION_STORAGE, 1)
            }
        }


    }
}