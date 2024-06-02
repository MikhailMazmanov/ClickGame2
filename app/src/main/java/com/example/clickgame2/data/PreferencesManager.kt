package com.example.clickgame2.data

import android.content.Context
import android.content.SharedPreferences
import android.content.SharedPreferences.Editor

class PreferencesManager(context: Context) {
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("db", Context.MODE_PRIVATE)


    fun putInt(key: String?, value: Int?) {
        val editor: Editor = sharedPreferences.edit()
        editor.putInt(key , value ?: 0)
        editor.apply()

    }
       fun getInt(key: String?):Int{
           val value = sharedPreferences.getInt(key, 1)
           return value
       }



      fun getString(key: String?):String{
         val value:String? = sharedPreferences.getString(key , "no")
          return value!!
      }


      fun putString(key: String? , value: String?){
         val editor: Editor = sharedPreferences.edit()
         editor.putString(key , value ?:"no")
          editor.apply()
      }

}