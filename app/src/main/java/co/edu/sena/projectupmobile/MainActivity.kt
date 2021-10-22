package co.edu.sena.projectupmobile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import co.edu.sena.projectupmobile.databinding.ActivityMainBinding
import com.huawei.cloud.services.drive.DriveScopes
import com.huawei.hms.common.ApiException
import com.huawei.hms.support.account.AccountAuthManager
import com.huawei.hms.support.account.request.AccountAuthParams
import com.huawei.hms.support.account.request.AccountAuthParamsHelper
import com.huawei.hms.support.account.service.AccountAuthService
import com.huawei.hms.support.api.entity.auth.Scope
import com.huawei.hms.support.hwid.HuaweiIdAuthManager
import com.huawei.hms.support.hwid.request.HuaweiIdAuthParams
import com.huawei.hms.support.hwid.request.HuaweiIdAuthParamsHelper
import com.huawei.hms.support.hwid.result.AuthHuaweiId

class MainActivity : AppCompatActivity(), View.OnClickListener {

    // Variables

    private val REQUEST_CODE = 8888
    private val TAG = "MainActivityInfo"
    val scopes = listOf(
        Scope("email"),
        Scope(DriveScopes.SCOPE_DRIVE),
        Scope(DriveScopes.SCOPE_DRIVE_FILE),
        Scope(DriveScopes.SCOPE_DRIVE_METADATA),
        Scope(DriveScopes.SCOPE_DRIVE_METADATA_READONLY),
        Scope(DriveScopes.SCOPE_DRIVE_READONLY),
        Scope(DriveScopes.SCOPE_DRIVE_APPDATA)
    )

    // Binding
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLogin.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        // Se envía una autorización de request
        val authParams = HuaweiIdAuthParamsHelper(HuaweiIdAuthParams.DEFAULT_AUTH_REQUEST_PARAM)
            .setIdToken()
            .setScopeList(scopes)
            .createParams()

        // Se inicializa le objeto account para la autenticación
        val service = HuaweiIdAuthManager.getService(this, authParams)
        startActivityForResult(service.signInIntent, REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 8888) {
            val authHuaweiIdTask = HuaweiIdAuthManager.parseAuthResultFromIntent(data)
            if (authHuaweiIdTask.isSuccessful) {
                // El inicio de sesión es correcto, y el código de autorización ha sido obtenido.
                val authAccount = authHuaweiIdTask.result
                binding.textView.apply {
                    append(" ${authAccount.displayName}")
                    if (authAccount.email.isNotEmpty()) {
                        append(" ${authAccount.email}")
                    }
                    sendHome(authAccount)
                }
                Log.i(TAG, "Codigo de autorizacion:" + authAccount.authorizationCode + authAccount.idToken + "Name:" + authAccount.displayName + "Email" + authAccount.email )
            } else {
                // Error al iniciar sesión
                binding.textView.append("Fallo al iniciar sesion:" + (authHuaweiIdTask.exception as ApiException).statusCode)
                Log.e(TAG, "Fallo al iniciar sesion:" + (authHuaweiIdTask.exception as ApiException).statusCode)
            }
        }
    }

    fun sendHome(authAccount: AuthHuaweiId){
        val intent = Intent(this, NavegationActivity::class.java)
        intent.putExtra("account", authAccount)
        startActivity(intent)
    }

}