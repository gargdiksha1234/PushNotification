package com.project.assignmentdiksha.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "employee")
data class Employee (
    @PrimaryKey(autoGenerate =true)
    val id: Long ?= null,

    val Name: String ,
    val email:String,
    val phone: String,
    val password:String,
    val uri:String
)