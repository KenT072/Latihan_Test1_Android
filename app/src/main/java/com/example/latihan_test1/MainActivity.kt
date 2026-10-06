package com.example.latihan_test1

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private val roleLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            if (result.resultCode == RESULT_OK) {
                val role = result.data?.getStringExtra("role")
                if (role != null) {
                    val txtRole = findViewById<TextView>(R.id.txtRole)
                    txtRole.text = "♢   Role\n      $role"
                }
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val txtEmail = findViewById<TextView>(R.id.txtEmail)
        val txtPhone = findViewById<TextView>(R.id.txtPhone)
        val txtRole = findViewById<TextView>(R.id.txtRole)

        txtEmail.setOnClickListener {
            val intent = Intent(Intent.ACTION_SENDTO)
            intent.data = Uri.parse("mailto:sarah@school.edu")

            startActivity(intent)
        }

        txtPhone.setOnClickListener {
            val intent = Intent(Intent.ACTION_DIAL)
            intent.data = Uri.parse("tel:+15559876547")

            startActivity(intent)
        }

        txtRole.setOnClickListener {
            val intent = Intent(
                this@MainActivity,
                RoleActivity::class.java
            )

            roleLauncher.launch(intent)
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}