package com.project.assignmentdiksha.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.project.assignmentdiksha.base.BaseFragment
import com.project.assignmentdiksha.databinding.FragmentWelcomeBinding


class WelcomeFragment : BaseFragment() {
 private lateinit var binding: FragmentWelcomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentWelcomeBinding.inflate(inflater)
        return binding.root
    }


}