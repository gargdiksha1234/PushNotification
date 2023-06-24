
package com.project.assignmentdiksha.base

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.baseproject.utils.NetworkUtils
import com.example.baseproject.utils.ProjectConstants
import com.project.assignmentdiksha.databinding.ActivityBaseBinding

abstract class BaseActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBaseBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityBaseBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if(NetworkUtils.isInternetAvailable(this))
            Toast.makeText(this, ProjectConstants.PERMISSION_GRANTED, Toast.LENGTH_LONG).show()
        else
            Toast.makeText(this, ProjectConstants.PERMISSION_NOT_GRANTED, Toast.LENGTH_LONG).show()

    }
}