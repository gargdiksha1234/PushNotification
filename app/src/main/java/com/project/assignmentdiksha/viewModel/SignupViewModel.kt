package com.project.assignmentdiksha.viewModel

import android.util.Log
import android.util.Patterns
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.project.assignmentdiksha.base.BaseViewModel
import com.project.assignmentdiksha.db.Employee
import com.project.assignmentdiksha.repository.EmployeeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SignupViewModel:BaseViewModel() {
    private val employeeRepository by lazy { EmployeeRepository() }

    var name= MutableLiveData<String>()
    var email= MutableLiveData<String>()
    var phone= MutableLiveData<String>()
    var password= MutableLiveData<String>()
    var path=MutableLiveData<String>()
    var nameError = MutableLiveData<String>()
    var emailError = MutableLiveData<String>()
    var phoneError = MutableLiveData<String>()
    var passError = MutableLiveData<String>()
    var navigate =MutableLiveData<Boolean>(false)

    fun validation(){
        if(name.value.toString().length<3)
            nameError.value="Enter a valid Name"
        else {nameError.value="" }

        if (!Patterns.EMAIL_ADDRESS.matcher(email.value.toString()).matches())
            emailError.value="Invalid Email Address"
        else {emailError.value=""
            nnnn()}

        if (phone.value.toString().length != 10)
            phoneError.value="Enter a valid number"
        else {phoneError.value="" }

        if(password.value.toString().length <6)
            passError.value="Password must me more than 6 char"
        else {passError.value="" }
    }

    fun nnnn()
    {
        if (nameError.value?.isEmpty() == true && emailError.value?.isEmpty() == true && phoneError.value?.isEmpty() == true && passError.value?.isEmpty() == true)
        {
            Log.d("api","api hit")
            adddata()
        }
    }
    fun addPlayer(employee: Employee)
    {
        navigate.value=true
        viewModelScope.launch(Dispatchers.IO) {
           employeeRepository.addPlayer(employee)
        }

    }
    fun adddata(){
        addPlayer(
            Employee(
                null,
                name.value.toString(),
                email.value.toString(),
                phone.value.toString(),
                password.value.toString(),
                path.value.toString()
            ))

    }
}