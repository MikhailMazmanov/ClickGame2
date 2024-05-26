package com.example.clickgame2.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
class User(
    @PrimaryKey(autoGenerate = true) var id:Long,
    @ColumnInfo(name = "armor") var armor:Int,
    @ColumnInfo(name = "balance") var balance:Int

) {

}