package com.example.clickgame2.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity(tableName = "weapons")
class Weapon( @PrimaryKey(autoGenerate = true) val id:Long,
               @ColumnInfo (name = "name_weapon") val name:String ,
              @ColumnInfo(name ="power") var power :Int ,
              @ColumnInfo(name ="price")var price:Int) {


}