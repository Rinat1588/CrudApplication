package com.example.crudapplication

import adapter.UserRecycleAdapter
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import datasource.ServiceBuilder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import model.Post

class UserActivity : AppCompatActivity() {
    private val apiService = ServiceBuilder.getService()
    private val apiPostService= ServiceBuilder.getPostService()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)
        val name = findViewById<TextView>(R.id.textViewName)
        val email = findViewById<TextView>(R.id.textViewEmail)
        val username = findViewById<TextView>(R.id.textViewUsername)
        val website = findViewById<TextView>(R.id.textViewWebsite)
        val phone = findViewById<TextView>(R.id.textViewPhone)
        val id = intent.getIntExtra(MainActivity.EXTRA_ID,0)
        lifecycleScope.launch(Dispatchers.IO) {
            val user = apiService.getUserById(id)
            withContext(Dispatchers.Main) {
                name.text = user?.name
                username.text = user?.username
                email.text = user?.email
                website.text = user?.website
                phone.text = user?.phone
            }
        }
        val list = mutableListOf<Post>()
        val adapter = UserRecycleAdapter(list)
        val postRecycler = findViewById<RecyclerView>(R.id.postRecyclerView)
        postRecycler.layoutManager = LinearLayoutManager(this)
        postRecycler.adapter = adapter
        lifecycleScope.launch(Dispatchers.IO) {
            val posts = apiPostService.getPostsByUserId(id)
            list.addAll(posts)
            withContext(Dispatchers.Main){
                adapter.notifyDataSetChanged()
            }
        }

        val buttonAdd = findViewById<Button>(R.id.addPostButton)
        buttonAdd.setOnClickListener{
            val intent = Intent(this,AddPostActivity::class.java)
            intent.putExtra("id",id)
            startActivity(intent)
        }
    }
}