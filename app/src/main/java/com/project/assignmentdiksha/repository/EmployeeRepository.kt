package com.project.assignmentdiksha.repository

import android.util.Log
import com.project.assignmentdiksha.MainApplication
import com.project.assignmentdiksha.db.Employee
import com.project.assignmentdiksha.db.EmployeeDatabase


class EmployeeRepository () {

    private val employeeDatabase by lazy { EmployeeDatabase }

    fun addPlayer(employee: Employee)
    {
        employeeDatabase.getDatabase(MainApplication.instance).employeeDao().insertEmployee(employee)
    }
    fun loginPasswordChecking(email:String) : String?
    {

        return employeeDatabase.getDatabase(MainApplication.instance).employeeDao().loginPasswordChecking(email)

    }
    fun update(password:String,email: String)
    {

        employeeDatabase.getDatabase(MainApplication.instance).employeeDao().update( password, email)
    }
}