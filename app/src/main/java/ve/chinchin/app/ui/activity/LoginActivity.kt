package ve.chinchin.app.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import ve.chinchin.app.R
import ve.chinchin.app.databinding.ActivityLoginBinding
import ve.chinchin.app.util.SharedPreference

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var sharedPreference: SharedPreference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        sharedPreference = SharedPreference(this)
        binding.buttonLogin.setOnClickListener {
            validateCredentials()
        }
    }

    private fun validatefieldsLogin(): Boolean{
        binding.textInputUser.isErrorEnabled = true
        binding.textInputPass.isErrorEnabled = true
        binding.textViewErrorLogin.visibility = View.INVISIBLE
        var aux = true
        if(binding.editUserLogin.text!!.trim().isEmpty()){
            binding.textInputUser.error = getString(R.string.usuario_error)
            aux = false
        }
        if(binding.editPassLogin.text!!.trim().isEmpty()){
            binding.textInputPass.error = getString(R.string.pass_error)
            aux = false
        }
        return aux
    }

    private fun validateCredentials(){
        if(validatefieldsLogin()){
            if((binding.editUserLogin.text!!.trim().toString() != "admin") ||
                (binding.editPassLogin.text!!.trim().toString() != "admin")){
                binding.textViewErrorLogin.visibility = View.VISIBLE
            }else{
                sharedPreference.save("username",binding.editUserLogin.text!!.trim().toString())
                sharedPreference.save("token","dummy token")

                val intent = Intent(applicationContext, HomeActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
            }
        }
    }
}
