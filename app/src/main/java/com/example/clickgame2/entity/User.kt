package com.example.clickgame2.entity

//@Entity(tableName = "users")
//class User(
//    @PrimaryKey(autoGenerate = true) var id:Long=0,
//    @ColumnInfo(name = "armor") var armor:Int,
//    @ColumnInfo(name = "balance") var balance:Int
//
//) {
//
//}


class
User(
    var id: Long = 0,
    var armor: Int,
    var balance: Int,
    var weapone: Weapon
) {

}