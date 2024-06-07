package com.example.clickgame2.data.room


import androidx.room.Dao;
import androidx.room.Query;

import com.example.clickgame2.entity.Weapon;



@Dao
interface WeaponDao {

    @Query("SELECT * FROM weapons")
    suspend fun getWeapons(): List<Weapon>

    @Query("UPDATE weapons SET is_selected = false")
    suspend fun setSelectedAllFalse():Int

    @Query("UPDATE weapons SET is_pay = :isPay WHERE id = :id")
    suspend fun setIsPayById(isPay: Boolean, id: Int)

    @Query("UPDATE weapons SET is_selected = :isSelected WHERE id = :id")
    suspend fun setSelectedById(isSelected: Boolean, id: Int)

    @Query("SELECT * FROM weapons WHERE id = :id")
    suspend fun getWeaponById(id: Int): Weapon?
}
