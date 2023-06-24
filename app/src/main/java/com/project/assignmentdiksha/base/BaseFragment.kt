package com.project.assignmentdiksha.base

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.project.assignmentdiksha.databinding.FragmentBaseBinding

abstract class BaseFragment : Fragment() {
    private lateinit var binding: FragmentBaseBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentBaseBinding.inflate(inflater)
        return binding.root
    }


}