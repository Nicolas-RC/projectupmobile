package co.edu.sena.projectupmobile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import co.edu.sena.projectupmobile.databinding.ActivityMainBinding
import com.huawei.hms.common.ApiException
import com.huawei.hms.support.account.AccountAuthManager
import com.huawei.hms.support.account.request.AccountAuthParams
import com.huawei.hms.support.account.request.AccountAuthParamsHelper
import com.huawei.hms.support.account.service.AccountAuthService

class MainActivity : AppCompatActivity() {

    // Variables
    companion object {
        const val REQUEST_CODE = 8888
        const val TAG = "MainActivityInfo"
    }

    // Binding
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLogin.setOnClickListener {
            // Se envía una autorización de request
            val authParams : AccountAuthParams =
                AccountAuthParamsHelper(AccountAuthParams.DEFAULT_AUTH_REQUEST_PARAM)
                    .setAuthorizationCode()
                    .createParams()

            // Se inicializa le objeto account para la autenticación
            val service : AccountAuthService =
                AccountAuthManager.getService(this, authParams)

            // Desplegar la autorización
            startActivityForResult(service.signInIntent, REQUEST_CODE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 8888) {
            val authAccountTask = AccountAuthManager.parseAuthResultFromIntent(data)
            if (authAccountTask.isSuccessful) {
                // El inicio de sesión es correcto, y el código de autorización ha sido obtenido.
                val authAccount = authAccountTask.result
                Log.i(TAG, "Codigo de autorizacion:" + authAccount.authorizationCode)
            } else {
                // Error al iniciar sesión
                Log.e(TAG, "Fallo al iniciar sesion:" + (authAccountTask.exception as ApiException).statusCode)
            }
        }
    }
}