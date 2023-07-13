package com.listocalixto.android.rembrandt

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.listocalixto.android.rembrandt.databinding.ActivityMainBinding
import com.listocalixto.android.rembrandt.presentation.utility.contentView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by contentView(R.layout.activity_main)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.run {}
    }
}
