package com.project.assignmentdiksha.ui.fragment

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.project.assignmentdiksha.R
import com.project.assignmentdiksha.base.BaseFragment
import com.project.assignmentdiksha.databinding.FragmentLoginBinding
import com.project.assignmentdiksha.viewModel.LoginViewModel


class LoginFragment : BaseFragment() {
    private lateinit var binding: FragmentLoginBinding
    private lateinit var loginViewModel:LoginViewModel
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
        binding=FragmentLoginBinding.inflate(inflater)
       return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setListener()
        initViewModel()
        initObserver()
    }

    private fun initObserver() {
        loginViewModel.navigate.observe(this, Observer {
            if(it)
            {  findNavController().navigate(R.id.welcomeFragment, null)}

        })
    }

    private fun initViewModel() {
        loginViewModel= ViewModelProvider(this)[LoginViewModel::class.java]
        binding.loginViewModel=loginViewModel    }

    private fun setListener() {
        binding.tvSignUp.setOnClickListener{
            findNavController().navigate(R.id.signUpFragment, null)

        }
        binding.btForgotPassword.setOnClickListener{
            findNavController().navigate(R.id.forgotPasswordFragment, null)

        }
    }


}