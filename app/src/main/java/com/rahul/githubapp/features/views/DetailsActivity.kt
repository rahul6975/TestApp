package com.rahul.githubapp.features.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.rahul.githubapp.R
import com.rahul.githubapp.databinding.ActivityDetailsBinding

class DetailsActivity : AppCompatActivity() {
    lateinit var binding: ActivityDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_details)

        val id = intent.getIntExtra("id", 0)
        val title = intent.getStringExtra("title")
        val body = intent.getStringExtra("body")

        binding.tvId.text = id.toString()
        binding.tvBody.text = body
        binding.tvTitle.text = title
    }
}