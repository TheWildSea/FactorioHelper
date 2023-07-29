package com.example.factoriohelper

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.factoriohelper.databinding.ActivityElementDetailsBinding

class ElementDetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityElementDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityElementDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val name = intent?.getStringExtra("name")
        val description = intent?.getStringExtra("description")

        if (name != null && description != null) {
            binding.elementName.text = name
            binding.elementDescription.text = description
        } else {
            // Handle the case where the intent extras are not available or invalid
        }


        title = name
        binding.elementDescription.text = description
    }
}