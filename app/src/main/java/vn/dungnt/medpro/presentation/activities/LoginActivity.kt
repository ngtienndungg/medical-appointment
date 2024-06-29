package vn.dungnt.medpro.presentation.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import vn.dungnt.medpro.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}