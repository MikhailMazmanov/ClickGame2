package com.example.clickgame2.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Lifecycle
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Query
import com.example.clickgame2.data.PreferencesManager
import com.example.clickgame2.data.room.App
import com.example.clickgame2.data.room.MyDataBase
import com.example.clickgame2.data.room.WeaponDao
import com.example.clickgame2.entity.User
import com.example.clickgame2.databinding.ActivityStatistycBinding
import com.example.clickgame2.entity.Weapon
import com.example.clickgame2.service.setWeaponImage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class StatistycActivity : AppCompatActivity() {
    lateinit var binding: ActivityStatistycBinding
    //lateinit var user: User
    lateinit var dao:WeaponDao;
    lateinit var db: MyDataBase
    lateinit var preferencesManager: PreferencesManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStatistycBinding.inflate(layoutInflater)
        setContentView(binding.root)
        preferencesManager = PreferencesManager(this)
        db = App().getMyDataBase()
        dao = db.weaponDao()

        init()
        statistycIntent()
        //var weapon = createWeapone(preferencesManager)
    }


   private fun init(){

        val weapon = getWeaponeFromDB()
        val def = preferencesManager.getInt("def")


        binding.attackTxt.text = weapon!!.power.toString()
        binding.armorTxt.text = def.toString()
        binding.weaponType.text = weapon.name

        val image = binding.image
        setWeaponImage(weapon,image)

    }

    private fun statistycIntent() {
        binding.btnToolbarMenu.setOnClickListener(){
            val intent = Intent(this, MenuActivity::class.java)
            startActivity(intent)
        }

    }


    private fun getWeaponeFromDB(): Weapon?{
        var weapon:Weapon? = null
        CoroutineScope(Dispatchers.IO).launch {
           var list =  dao.getWeapons()
            for(i in list ){
              if (i.isSelected == true){
                  weapon = i
              }
            }

        }
        return weapon
    }
}


