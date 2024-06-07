package com.example.clickgame2.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.clickgame2.adapter.shop.OnItemClickWeapon
import com.example.clickgame2.adapter.shop.ShopAdapter
import com.example.clickgame2.data.PreferencesManager
import com.example.clickgame2.databinding.ActivityShopBinding
import com.example.clickgame2.entity.Weapon

class ShopActivity : AppCompatActivity(),OnItemClickWeapon{
    lateinit var binding :ActivityShopBinding
    lateinit var preferencesManager: PreferencesManager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        preferencesManager = PreferencesManager(this)
        binding = ActivityShopBinding.inflate(layoutInflater)
        setContentView(binding.root)

        levelsIntent()

        var weapon1 = Weapon(1,"Деревянный меч",10,200,false,false)
        var weapon2 = Weapon(2,"Каменный меч",35, 400,false,false)
        var weapon3 = Weapon(3,"Железный меч",75,650,false,false)
        var weapon4 = Weapon(4,"Золотой меч",105,800,false,false)
        var weapon5 = Weapon(5,"Алмазный меч",250,1500,false,false)

        var list = listOf<Weapon>(weapon1,weapon2,weapon3,weapon4,weapon5)

        binding.recyclerView.adapter = ShopAdapter(list,this)

    }

    override fun click(name:String) {
       val toast = Toast.makeText(this,"Для покупки $name нажмите купить",Toast.LENGTH_SHORT)
        toast.show()
    }

   fun levelsIntent(){
       binding.btnToolbarMenu.setOnClickListener() {
           val intent = Intent(this, MenuActivity::class.java)
           startActivity(intent)
           finish()
       }
   }
}