package com.project.assignmentdiksha.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.project.assignmentdiksha.base.BaseViewModel
import com.project.assignmentdiksha.repository.EmployeeRepository
import kotlinx.coroutines.launch

class ForgotPasswordViewModel(): BaseViewModel() {
    private val employeeRepository by lazy { EmployeeRepository() }

    var changeEmail= MutableLiveData<String>()
    var changePassword=MutableLiveData<String>()
    fun update(changePassword:String,changeEmail:String)
    {
        viewModelScope.launch {
            employeeRepository.update(changePassword,changeEmail)
        }
    }

}