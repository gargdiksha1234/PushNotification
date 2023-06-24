package com.project.assignmentdiksha.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.project.assignmentdiksha.base.BaseViewModel
import com.project.assignmentdiksha.repository.EmployeeRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class LoginViewModel:BaseViewModel() {
    private val employeeRepository by lazy { EmployeeRepository() }

    var loginEmail= MutableLiveData<String>()
    var loginPassword= MutableLiveData<String>()
    var navigate= MutableLiveData<Boolean>(false)

    var tag:String=""
    fun setEmail():String
    {
        val emails=loginEmail.value
        return emails?:""
    }

    fun loginPasswordChecking(loginEmail:String)
    {
        viewModelScope.launch(Dispatchers.IO) {
            tag=employeeRepository.loginPasswordChecking(loginEmail).toString().trim()
        }
        CoroutineScope(Dispatchers.Main).launch {
            delay(5000)
            navigate.value = loginPassword.value.toString() == tag
        }
    }
}