package com.example.crudapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.google.gson.JsonObject
import datasource.ServiceBuilder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import model.Post

class AddPostActivity : AppCompatActivity() {
    private val apiService = ServiceBuilder.getPostService()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_post)

        val list = mutableListOf<Post>()
        val button = findViewById<Button>(R.id.addButton)
        button.setOnClickListener{
            createPost()
        }
    }

    private fun createPost() {
        val titlePost = findViewById<EditText>(R.id.editTextTitle)
        val bodyPost = findViewById<EditText>(R.id.editTextBody)
        val id: Int = intent.getIntExtra("id",0)
        lifecycleScope.launch(Dispatchers.IO) {
            val body = JsonObject().apply {
                addProperty("userId", id.toString())
                addProperty("textBody", bodyPost.text.toString())
                addProperty("title", titlePost.text.toString())
            }
            val result = apiService.createPost(body)
            if (result.isSuccessful){
                Log.e("0000", "статья создана ${result.body()}")
            }
            else {
                Log.e("0000", "статья не создана ${result.body()}")
            }
        }
    }
}