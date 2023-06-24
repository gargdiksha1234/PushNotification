package com.project.assignmentdiksha.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Employee::class], version = 2)
abstract class EmployeeDatabase : RoomDatabase(){
    abstract fun employeeDao():EmployeeDao
    companion object {
        @Volatile
        private var INSTANCE : EmployeeDatabase ?= null

        fun getDatabase(context: Context) : EmployeeDatabase {
            if (INSTANCE == null) {
                synchronized(this) {}
                INSTANCE = Room.databaseBuilder(
                    context,
                    EmployeeDatabase::class.java, "userDB"
                )
                    .build()
            }
            return INSTANCE!!
        }
    }
}