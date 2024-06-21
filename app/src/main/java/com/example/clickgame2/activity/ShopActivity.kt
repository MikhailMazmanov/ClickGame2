package com.example.clickgame2.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.room.Room
import com.example.clickgame2.adapter.shop.OnItemClickWeapon
import com.example.clickgame2.adapter.shop.ShopAdapter
import com.example.clickgame2.data.PreferencesManager
import com.example.clickgame2.data.room.App
import com.example.clickgame2.data.room.MyDataBase
import com.example.clickgame2.data.room.WeaponDao
import com.example.clickgame2.databinding.ActivityShopBinding
import com.example.clickgame2.entity.Weapon
import com.example.clickgame2.service.createListWeapon
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.job
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

class ShopActivity : AppCompatActivity(), OnItemClickWeapon {
    lateinit var binding: ActivityShopBinding
    lateinit var preferencesManager: PreferencesManager
    lateinit var dao: WeaponDao
    lateinit var db: MyDataBase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        preferencesManager = PreferencesManager(this)
        binding = ActivityShopBinding.inflate(layoutInflater)

        db = App().getMyDataBase()
        dao = db.weaponDao()

        setContentView(binding.root)

        levelsIntent()


        var list = createListWeapon()
        CoroutineScope(Dispatchers.IO).launch {
            dao.getWeapons()
        }

        binding.recyclerView.adapter = ShopAdapter(list, this)

    }


    fun getWeaponByIdFromList(id: Long, list: List<Weapon>): Weapon {
        return list.filter { it.id == id }.get(0)
    }

    override suspend fun click(id: Long): Boolean {
        var weapon = getWeaponByIdFromList(id, createListWeapon())
        var isPay = false
        val balance = preferencesManager.getInt("balance")
        if (balance >= weapon.price) {
            withContext(Dispatchers.IO) {
                dao.setIsPayById(true, id.toInt())
                dao.setSelectedAllFalse()
                dao.setSelectedById(true, id.toInt())
            }
            withContext(Dispatchers.Main) {
                isPay = true
            }
        }
        return isPay
    }



    fun levelsIntent() {
    binding.btnToolbarMenu.setOnClickListener() {
        val intent = Intent(this, MenuActivity::class.java)
        startActivity(intent)
        finish()
    }
}
}