package com.example.clickgame2.data.room

import android.app.Application
import androidx.room.Room

class App: Application() {
    private lateinit var myDataBase: MyDataBase
    override fun onCreate() {
        super.onCreate()
        myDataBase = Room.databaseBuilder(applicationContext,MyDataBase::class.java,"weapone_db").build()
    }

    fun getMyDataBase() : MyDataBase{
        return myDataBase;
    }
}