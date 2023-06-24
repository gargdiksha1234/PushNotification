package com.project.assignmentdiksha.ui.activity

import android.os.Bundle
import com.project.assignmentdiksha.base.BaseActivity
import com.project.assignmentdiksha.databinding.ActivityMainBinding

class MainActivity : BaseActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
    }
}