package com.example.clickgame2.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.clickgame2.data.PreferencesManager
import com.example.clickgame2.data.room.App
import com.example.clickgame2.data.room.MyDataBase
import com.example.clickgame2.data.room.WeaponDao
import com.example.clickgame2.databinding.ActivityMenuBinding
import com.example.clickgame2.service.createListWeapon

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class MenuActivity : AppCompatActivity() {
    lateinit var binding: ActivityMenuBinding
    lateinit var preferencesManager: PreferencesManager
    private var startSound = true

    lateinit var dao: WeaponDao
    lateinit var db: MyDataBase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuBinding.inflate(layoutInflater)
        preferencesManager = PreferencesManager(this)
        setContentView(binding.root)

        db = App().getMyDataBase()
        dao = db.weaponDao()
        if (startSound == true) {
            var idAudio = resources.getIdentifier("open", "raw", packageName)
            playAudio(idAudio)
            startSound = false
        }

        start()
        shop()
        init()
        statistyc()
        instructionsIntent()

//        val weapon = preferencesManager.getString("weapon")
//
//        if(weapon == "no"){
//            preferencesManager.putString(attack )
//        }
//


        // Устанавливаем задержку в три секунды перед скрытием текста
        Handler().postDelayed({
            // Анимация изменения прозрачности текста
            binding.textViewStartText.animate().apply {
                // Уменьшаем прозрачность от 1 до 0 за 3 секунды
                alpha(0f).duration = 1500
                // Слушатель завершения анимации
                withEndAction {
                    // Устанавливаем тексту пустую строку
                    binding.textViewStartText.text = ""
                }
            }
        }, 3000)
    }

    fun playAudio(audioResourseId: Int) {
        var mediaPlayer = MediaPlayer.create(this, audioResourseId)
        mediaPlayer?.setOnCompletionListener {
            mediaPlayer?.release()
            mediaPlayer = null
        }
        mediaPlayer?.start()
    }

    fun init() {

        CoroutineScope(Dispatchers.IO).launch {
            val weapons = dao.getWeapons()
            if (weapons.size == 0){
               // dao.insertWeapons(createListWeapon())
            }
        }


        val weaponId = preferencesManager.getInt("weapon_id")


        binding.gold.text = preferencesManager.getInt("gold").toString()
        val def = preferencesManager.getInt("def")
        if (def == 0) {
            preferencesManager.putInt("def", 100)
        }


    }


    @SuppressLint("SuspiciousIndentation")
    fun start() {
        binding.btnStart.setOnClickListener() {
            val intent = Intent(this, LevelsActivity::class.java)
            startActivity(intent)
        }
    }

    fun shop() {
        binding.btnShop.setOnClickListener() {
            val intent = Intent(this, ShopActivity::class.java)
            startActivity(intent)
        }
    }

    fun statistyc() {
        binding.btnStatistyc.setOnClickListener() {
            val intent = Intent(this, StatistycActivity::class.java)
            startActivity(intent)
        }
    }


    fun instructionsIntent() {
        binding.btnHowToPlay.setOnClickListener() {
            val intent = Intent(this, Instructions::class.java)
            startActivity(intent)
        }
    }
}

