package com.example.crudapplication

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import datasource.ServiceBuilder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UserActivity : AppCompatActivity() {
    private val apiService = ServiceBuilder.getService()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)
        val name = findViewById<TextView>(R.id.textViewName)
        val email = findViewById<TextView>(R.id.textViewEmail)
        val username = findViewById<TextView>(R.id.textViewUsername)
        val website = findViewById<TextView>(R.id.textViewWebsite)
        val phone = findViewById<TextView>(R.id.textViewPhone)
        lifecycleScope.launch(Dispatchers.IO) {
            val id = intent.getIntExtra(MainActivity.EXTRA_ID,0)
            val user = apiService.getUserById(id)
            withContext(Dispatchers.Main) {
                name.text = user?.name
                username.text = user?.username
                email.text = user?.email
                website.text = user?.website
                phone.text = user?.phone
            }
        }
    }
}