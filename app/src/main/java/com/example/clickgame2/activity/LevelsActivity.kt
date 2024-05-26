package com.example.clickgame2.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.clickgame2.adapter.level.LevelAdapter
import com.example.clickgame2.adapter.level.OnItemClick
import com.example.clickgame2.data.PreferencesManager
import com.example.clickgame2.databinding.ActivityLevelsBinding


class LevelsActivity : AppCompatActivity(), OnItemClick {
    lateinit var binding: ActivityLevelsBinding
    lateinit var preferencesManager: PreferencesManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLevelsBinding.inflate(layoutInflater)
        preferencesManager = PreferencesManager(this)
        setContentView(binding.root)
        menuIntent()
        toolbarTxt()

        val levels = ArrayList<String>()
        levels.add("Уровень 1")
        levels.add("Уровень 2")
        levels.add("Уровень 3")
        levels.add("Уровень 4")
        levels.add("Уровень 5")

        binding.recyclerView.adapter = LevelAdapter(levels, this)

    }

    override fun click(level: String) {
        Toast.makeText(this, "Выбран уровень: $level", Toast.LENGTH_SHORT).show()

        val intent = Intent(this, GameActivity::class.java)
        intent.putExtra("level", level[8].toString().toInt())
        startActivity(intent)
    }

    fun menuIntent() {
        binding.btnToolbarMenu.setOnClickListener() {
            val intent = Intent(this, MenuActivity::class.java)
            startActivity(intent)
        }

    }
    fun toolbarTxt(){
        binding.toolbarTxt.text = preferencesManager.getInt("gold").toString() + " gold"
    }

}