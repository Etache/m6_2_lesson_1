package com.example.m6_2_lesson_1.ui

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.m6_2_lesson_1.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnAdd.setOnClickListener {
            val title = binding.etTitle.text.toString().trim()
            if (title.isNotEmpty()) {
                val result = Intent()
                result.putExtra("title", title)
                setResult(Activity.RESULT_OK, result)
                finish()
            }
        }
    }
}