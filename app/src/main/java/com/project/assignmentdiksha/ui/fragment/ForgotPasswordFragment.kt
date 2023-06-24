package com.project.assignmentdiksha.ui.fragment

import android.os.Build
import android.os.Bundle
import android.view.*
import androidx.lifecycle.ViewModelProvider
import com.project.assignmentdiksha.base.BaseFragment
import com.project.assignmentdiksha.databinding.FragmentForgotPasswordBinding
import com.project.assignmentdiksha.viewModel.ForgotPasswordViewModel


class ForgotPasswordFragment : BaseFragment() {
    private lateinit var binding: FragmentForgotPasswordBinding
    private lateinit var changePasswordViewModel:ForgotPasswordViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        @Suppress("DEPRECATION")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            activity?.window?.insetsController?.hide(WindowInsets.Type.statusBars())
        } else {
            activity?.window?.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }



    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentForgotPasswordBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewModel()
    }

    private fun initViewModel() {
        changePasswordViewModel= ViewModelProvider(this)[ForgotPasswordViewModel::class.java]
        binding.changeViewModel=changePasswordViewModel
        binding.lifecycleOwner=this
    }


}