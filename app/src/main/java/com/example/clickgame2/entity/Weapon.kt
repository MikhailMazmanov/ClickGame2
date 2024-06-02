package com.example.clickgame2.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "weapons")
    //foreignKeys = [ForeignKey(entity=User::class, parentColumns = ["id"], childColumns = ["user_id"], onDelete = ForeignKey.CASCADE )]
//)
class Weapon(
    @PrimaryKey(autoGenerate = true) val id: Long=0,
    @ColumnInfo(name = "name_weapon") val name: String,
    @ColumnInfo(name = "power") var power: Int,
    @ColumnInfo(name = "price") var price: Int,
    @ColumnInfo(name = "is_pay") var isPay: Boolean,
    @ColumnInfo(name ="is_selected")var isSelected:Boolean

    //@Colum nInfo(name = "user_id") var userId: Long
) {



}