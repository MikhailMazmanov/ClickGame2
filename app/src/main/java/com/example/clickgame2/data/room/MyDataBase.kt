package com.example.clickgame2.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.clickgame2.entity.Weapon

@Database(entities = [Weapon::class], version = 1)
abstract class MyDataBase : RoomDatabase() {
    abstract  fun weaponDao() :WeaponDao
}