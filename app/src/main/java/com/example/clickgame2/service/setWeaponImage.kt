package com.example.clickgame2.service

import android.media.MediaPlayer
import android.widget.ImageView
import com.example.clickgame2.R
import com.example.clickgame2.data.PreferencesManager
import com.example.clickgame2.entity.Weapon

fun setWeaponImage(weapon: Weapon,image:ImageView) {
    when (weapon.name) {
        "Каменный меч" -> image.setImageResource(R.drawable.stone)
        "Железный меч" -> image.setImageResource(R.drawable.iron)
        "Золотой меч" -> image.setImageResource(R.drawable.gold)
        "Алмазный меч" -> image.setImageResource(R.drawable.diamond)
    }


}

fun createWeapone(preferencesManager: PreferencesManager): Weapon{
    val weaponeName = preferencesManager.getString("weaponName")
    val weaponePrice = preferencesManager.getInt("weaponPrice")
    val weaponeId = preferencesManager.getInt("weapoeId")
    val weaponePower = preferencesManager.getInt("weaponPower")
    return Weapon(weaponeId.toLong(),weaponeName,weaponePower,weaponePrice)

}


