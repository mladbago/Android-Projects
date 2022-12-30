package com.example.authenticationapp
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.authenticationapp.databinding.ActivitySignInBinding
import com.google.firebase.auth.FirebaseAuth
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignInActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignInBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.textView.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }
        binding.button.setOnClickListener {
            val username: String = binding.emailEt.text.toString().trim()
            val password: String = binding.passET.text.toString().trim()
            val response = ServiceBuilder.buildService(APIInterface::class.java)
            val requestModel = RequestModel(username, password)
            response.sendReqLogin(requestModel).enqueue(
                object : Callback<ResponseModel> {
                    override fun onResponse(
                        call: Call<ResponseModel>,
                        response: Response<ResponseModel>
                    ) {
                        Toast.makeText(this@SignInActivity,response.message().toString(),Toast.LENGTH_LONG).show()
                    }

                    override fun onFailure(call: Call<ResponseModel>, t: Throwable) {
                        Toast.makeText(this@SignInActivity,t.toString(),Toast.LENGTH_LONG).show()
                    }

                }
            )
        }
    }

}