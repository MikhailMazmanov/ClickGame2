package com.example.clickgame2.data.room


import androidx.room.Dao;
import androidx.room.Query;

import com.example.clickgame2.entity.Weapon;

import java.util.List;

@Dao
interface WeaponeDao {
    @Query("SELECT * FROM weapons")
    suspend fun getWeapons(): List<Weapon>

    @Query("UPDATE weapons SET is_selected = false")
    suspend fun setSelectedAllFalse()

    @Query("UPDATE weapons SET is_pay = :isPay where id =:id ")
    suspend fun setIsPayById(isPay:Boolean,id:Int)

    @Query("UPDATE weapons SET is_selected =:isSelected where id =:id")
    suspend fun setSelectedById(isSelected:Boolean,id:Int)

}
