package com.example.factoriohelper

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.factoriohelper.databinding.ActivityInfoBinding

class InfoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityInfoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.logisticsButton.setOnClickListener {
            val intent = Intent(this, ElementListActivity::class.java)
            intent.putExtra("category", "logistics")
            startActivity(intent)
        }

        binding.productionButton.setOnClickListener {
            val intent = Intent(this, ElementListActivity::class.java)
            intent.putExtra("category", "production")
            startActivity(intent)
        }

        binding.intermediateProductsButton.setOnClickListener {
            val intent = Intent(this, ElementListActivity::class.java)
            intent.putExtra("category", "intermediate-products")
            startActivity(intent)
        }

        binding.combatButton.setOnClickListener {
            val intent = Intent(this, ElementListActivity::class.java)
            intent.putExtra("category", "combat")
            startActivity(intent)
        }

        binding.environmentButton.setOnClickListener {
            val intent = Intent(this, ElementListActivity::class.java)
            intent.putExtra("category", "environment")
            startActivity(intent)
        }
    }

}

