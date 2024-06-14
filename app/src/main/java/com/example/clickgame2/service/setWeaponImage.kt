package com.example.clickgame2.service

import android.widget.ImageView
import com.example.clickgame2.R
import com.example.clickgame2.data.PreferencesManager
import com.example.clickgame2.entity.Weapon

fun setWeaponImage(weapon: Weapon, image:ImageView) {
    when (weapon.name) {
        "Каменный меч" -> image.setImageResource(R.drawable.stone)
        "Железный меч" -> image.setImageResource(R.drawable.iron)
        "Золотой меч" -> image.setImageResource(R.drawable.gold)
        "Алмазный меч" -> image.setImageResource(R.drawable.diamond)
    }


}

fun createListWeapon():List<Weapon>{

    var weapon1 = Weapon(1,"Деревянный меч",10,200,true,true)
    var weapon2 = Weapon(2,"Каменный меч",35, 400,false,false)
    var weapon3 = Weapon(3,"Железный меч",75,650,false,false)
    var weapon4 = Weapon(4,"Золотой меч",105,800,false,false)
    var weapon5 = Weapon(5,"Алмазный меч",250,1500,false,false)
    return  listOf(weapon1,weapon2,weapon3,weapon4,weapon5)
}


