package com.example.clickgame2.adapter.shop

interface OnItemClickWeapon {
    suspend fun click(id: Long) :Boolean
}