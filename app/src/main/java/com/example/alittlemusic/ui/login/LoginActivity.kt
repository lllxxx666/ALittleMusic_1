package com.example.alittlemusic.ui.login

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.*
import androidx.core.content.ContextCompat
import com.example.alittlemusic.MainActivity
import com.example.alittlemusic.databinding.ActivityLoginBinding

import com.example.alittlemusic.R
import com.example.alittlemusic.util.toast

class LoginActivity : AppCompatActivity() {

    private val loginViewModel by lazy {  ViewModelProvider(this ).get(LoginViewModel::class.java) }
    private lateinit var binding: ActivityLoginBinding

    private var login_method = 1    // 手机号登录为1，邮箱登录为2，其他待扩展
    companion object{
        fun actionStart(context: Context){
            val intent = Intent(context, LoginActivity::class.java)
            intent.flags = FLAG_ACTIVITY_NEW_TASK
            context.startActivity(intent)
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        binding.viewmodel = loginViewModel

        val phone = binding.phone
        val email = binding.email
        val password = binding.password
        val login = binding.login
        val loading = binding.loading

//        选择登录方式
        val login_text :RadioGroup? = binding.loginText
        login_text?.setOnCheckedChangeListener(RadioGroup.OnCheckedChangeListener{ radioGroup, checkedId ->
            when(checkedId){
                R.id.phone_login_text -> phoneLogin()
                R.id.email_login_text -> emailLogin()
            }
        } )



        loginViewModel.loginFormState.observe(this@LoginActivity, Observer {
            val loginState = it ?: return@Observer

            // disable login button unless both username / password is valid
            login.isEnabled = loginState.isDataValid
            when(login_method){
                1 -> {
                    if (loginState.phoneError != null) {
                        phone?.error = getString(loginState.phoneError)
                    }
                }
                2 -> {
                    if (loginState.emailError != null) {
                        email?.error = getString(loginState.emailError)
                    }
                }
            }

            if (loginState.passwordError != null) {
                password.error = getString(loginState.passwordError)
            }
        })

//        loginViewModel.loginResult.observe(this@LoginActivity, Observer {
//            val loginResult = it ?: return@Observer
//
//            loading.visibility = View.GONE
//            if (loginResult.error != null) {
//                showLoginFailed(loginResult.error)
//            }
//            if (loginResult.success != null) {
//                updateUiWithUser(loginResult.success)
//            }
//            setResult(Activity.RESULT_OK)
//
//            //Complete and destroy login activity once successful
////            finish()
//        })

        phone?.afterTextChanged {
            loginViewModel.loginDataChanged(
                phone.text.toString(),
                password.text.toString()
            )
        }
        email?.afterTextChanged {
            loginViewModel.loginDataChanged(
                email.text.toString(),
                password.text.toString()
            )
        }

        password.apply {
            afterTextChanged {
                loginViewModel.loginDataChanged(
                    phone?.text.toString(),
                    password.text.toString()
                )
            }

            setOnEditorActionListener { _, actionId, _ ->
                when (actionId) {
                    EditorInfo.IME_ACTION_DONE ->
                        loginViewModel.login(
                            phone?.text.toString(),
                            password.text.toString()
                        )
                }
                false
            }

            login.setOnClickListener {
                loading.visibility = View.VISIBLE
                when(login_method){
                    1 -> loginViewModel.login(phone?.text.toString(), password.text.toString())
                    2 -> loginViewModel.emailogin(email?.text.toString(), password.text.toString())
                }

            }
        }

        loginViewModel.loginLiveData.observe(this, Observer { result ->
            val data = result.getOrNull()

//            Log.d("test","让我看看有么有走到这")
            if (data != null){
                when(data.code){
                    400 -> toast("登录失败，请检测账户/密码是否正确")
                    502 -> toast("网关错误，请尝试清理缓存后再试")
                    504 -> toast("前方网络拥堵，请稍后再试")
                    200 ->updateUiWithUser(LoggedInUserView(data.profile.nickname))
                    else -> toast("登录失败")
                }
            }else  {
                binding.loading.visibility = View.GONE
                toast("登录失败，请检测账户/密码是否正确")
            }
        })
    }

    private fun phoneLogin(){
        login_method = 1
        binding.email?.visibility = View.GONE
        binding.phone?.visibility = View.VISIBLE
    }
    private fun emailLogin(){
        login_method = 2
        binding.email?.visibility = View.VISIBLE
        binding.phone?.visibility = View.GONE
    }
    private fun updateUiWithUser(model: LoggedInUserView) {
        getSharedPreferences("data", Context.MODE_PRIVATE).edit().let {
            it.putBoolean("isLogin",true)
            it.apply()
        }
        val welcome = getString(R.string.welcome)
        val displayName = model.displayName
        // initiate successful logged in experience
        Toast.makeText(
            applicationContext,
            "$welcome $displayName",
            Toast.LENGTH_LONG
        ).show()
        android.os.Handler().postDelayed(Runnable {
            MainActivity.actionStart(this)
        }, 1800)
    }

    private fun showLoginFailed(@StringRes errorString: Int) {
        Toast.makeText(applicationContext, errorString, Toast.LENGTH_SHORT).show()
    }

}

/**
 * Extension function to simplify setting an afterTextChanged action to EditText components.
 */
fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(editable: Editable?) {
            afterTextChanged.invoke(editable.toString())
        }

        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
    })
}

