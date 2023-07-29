package com.example.factoriohelper

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.Toast
import com.example.factoriohelper.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.infoButton.setOnClickListener {
            Toast.makeText(this, "Info button clicked", Toast.LENGTH_SHORT).show()
            navigateToInfoActivity()
        }

        binding.calcButton.setOnClickListener {
            Toast.makeText(this, "Calc button clicked", Toast.LENGTH_SHORT).show()
            navigateToCalculatorActivity()
        }
        binding.newsButton.setOnClickListener {
            Toast.makeText(this, "News button clicked", Toast.LENGTH_SHORT).show()
            navigateToNewsActivity()
        }
    }

    private fun navigateToInfoActivity() {
        val intent = Intent(this, InfoActivity::class.java)
        startActivity(intent)
    }

    private fun navigateToCalculatorActivity() {
        val intent = Intent(this, CalculatorActivity::class.java)
        startActivity(intent)
    }
    private fun navigateToNewsActivity() {
        val intent = Intent(this, NewsActivity::class.java)
        startActivity(intent)
    }
}

