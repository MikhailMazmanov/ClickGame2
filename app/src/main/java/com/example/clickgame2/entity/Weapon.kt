package com.example.clickgame2.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "weapons")
class Weapon(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    @ColumnInfo(name = "name_weapon") val name: String,
    @ColumnInfo(name = "power") var power: Int,
    @ColumnInfo(name = "price") var price: Int,
    @ColumnInfo(name = "is_pay") var isPay: Boolean,
    @ColumnInfo(name = "is_selected") var isSelected: Boolean
)
{




}