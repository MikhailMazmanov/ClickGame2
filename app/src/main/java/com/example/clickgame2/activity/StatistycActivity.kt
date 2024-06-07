package com.example.clickgame2.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.clickgame2.data.PreferencesManager
import com.example.clickgame2.entity.User
import com.example.clickgame2.databinding.ActivityStatistycBinding
import com.example.clickgame2.entity.Weapon
import com.example.clickgame2.service.setWeaponImage

class StatistycActivity : AppCompatActivity() {
    lateinit var binding: ActivityStatistycBinding
    //lateinit var user: User
    lateinit var preferencesManager: PreferencesManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStatistycBinding.inflate(layoutInflater)
        setContentView(binding.root)
        preferencesManager = PreferencesManager(this)
        init()
        statistycIntent()
        //var weapon = createWeapone(preferencesManager)
    }


   private fun init(){

        val weapon = createWeapone();
        //val def = preferencesManager.getInt("def")
       // var user = User(def,weapon)

       // binding.attackTxt.text = user.attack.toString()
       // binding.armorTxt.text = user.armor.toString()
       // binding.weaponType.text = user.weapon.name

        val image = binding.image
        setWeaponImage(weapon,image)

    }

    private fun statistycIntent() {
        binding.btnToolbarMenu.setOnClickListener(){
            val intent = Intent(this, MenuActivity::class.java)
            startActivity(intent)
        }

    }


    private fun createWeapone(): Weapon{
        return Weapon(1,"",12,12,true,true)

    }
}


