package com.example.clickgame2.activity

import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.animation.Animation
import android.view.animation.TranslateAnimation
import com.example.clickgame2.data.PreferencesManager
import com.example.clickgame2.R
import com.example.clickgame2.entity.User
import com.example.clickgame2.databinding.ActivityGameBinding
import com.example.clickgame2.service.createWeapone


class GameActivity : AppCompatActivity() {

    lateinit var binding: ActivityGameBinding
    var mediaPlayer: MediaPlayer? = null
    var counter = 0
    var ingreso = 0
    var attack = 0
    lateinit var preferencesManager: PreferencesManager
    lateinit var user: User
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGameBinding.inflate(layoutInflater)
        setContentView(binding.root)
        preferencesManager = PreferencesManager(this)

        val weapon = createWeapone()

        user = User(1,100,preferencesManager.getInt("balance"), weapon)

        var level: Int = intent.getIntExtra("level", 2)
        when (level) {
            1 -> {
                counter = 30
                ingreso = 100
                attack = 5
            }


            2 -> {
                binding.RL.setBackgroundResource(R.drawable.fon1)
                counter = 45
                ingreso = 400
                attack = 8
            }

            3 -> {
                binding.RL.setBackgroundResource(R.drawable.fon2)
                counter = 105
                ingreso = 1050
                attack = 13
            }

            4 -> {
                binding.RL.setBackgroundResource(R.drawable.fon4)
                counter = 200
                ingreso = 2500
                attack = 16
            }

            5 -> {
                binding.RL.setBackgroundResource(R.drawable.fon5)
                counter = 500
                ingreso = 5500
                attack = 23
            }
        }
        binding.mobLives.text = counter.toString()

        var idImageMob = resources.getIdentifier("mob$level", "drawable", packageName)
        binding.imageView1.setImageResource(idImageMob)

        mobOnClick()


    }

    fun mobAttack(nameAudio:String , attack:Int){
        Handler().postDelayed(
            {
                var idAudio = resources.getIdentifier(nameAudio, "raw", packageName)
                playAudio(idAudio)
                if ((user.armor - attack) <0){
                    user.armor = 0
                }else{
                    user.armor -= attack
                }

                binding.bron.text = user.armor.toString()
            }, 500
        )

    }


    fun mobOnClick() {
        binding.imageView1.setOnClickListener {
            if (counter > 0) {
                counter-= user.weapone.power
                binding.mobLives.text = counter.toString()
                var idAudio = resources.getIdentifier("hit", "raw", packageName)
                playAudio(idAudio)

                var critticalAtack = (1..100).random() in 1..20
                if (critticalAtack == true) {
                    var critAttack =    (attack / 2) * (5..10).random()
                    mobAttack("crit",critAttack)

                } else {
                    mobAttack("def",attack)
                }

                if (user.armor <= 0) {
                    binding.loseText.visibility = View.VISIBLE
                    var idAudio = resources.getIdentifier("over", "raw", packageName)
                    playAudio(idAudio)
                    Handler().postDelayed({
                        val intent = Intent(this, MenuActivity::class.java)
                        startActivity(intent)
                    },1000)

                }

            } else {
                binding.winText.visibility = View.VISIBLE
                animateAndHideImage()
            }
        }
    }

    private fun playAudio(audioResourseId: Int) {
        mediaPlayer = MediaPlayer.create(this, audioResourseId)
        mediaPlayer?.setOnCompletionListener {
            mediaPlayer?.release()
            mediaPlayer = null
        }
        mediaPlayer?.start()
    }

    private fun animateAndHideImage() {
        val animation = TranslateAnimation(0f, 1000f, 0f, 0f)
        animation.duration = 5000
        animation.fillAfter = true


        // слушатель завершения анимации
        var animListener = object : Animation.AnimationListener {
            override fun onAnimationStart(p0: Animation?) {
                var idAudio = resources.getIdentifier("mob_death", "raw", packageName)
                playAudio(idAudio)

            }

            override fun onAnimationEnd(p0: Animation?) {
                Handler().postDelayed(
                    {
                        var gold = preferencesManager.getInt("gold")
                        preferencesManager.putInt("gold", gold + ingreso)
                        intentLevel()
                        finish()


                    }, 100
                )
            }


            override fun onAnimationRepeat(p0: Animation?) {

            }
        }

        animation.setAnimationListener(animListener)
        binding.imageView1.startAnimation(animation)
    }

    private fun intentLevel() {
        val intent = Intent(this, LevelsActivity::class.java)
        startActivity(intent)
    }


}


//    private fun updateScore(imageView: ImageView ) {private fun moveImages(imageView1: ImageView) {
//    for (i in 1..10) {
//        val animation = TranslateAnimation(
//            TranslateAnimation.ABSOLUTE, 0f,
//            TranslateAnimation.ABSOLUTE, (0..400).random().toFloat(),
//            TranslateAnimation.ABSOLUTE, 0f,
//            TranslateAnimation.ABSOLUTE, (0..600).random().toFloat()
//        )
//
//        animation.duration = 2000
//        animation.fillAfter = false
//        imageView1.startAnimation(animation)
//
//        imageView1.setOnClickListener { view ->
//
//        }
//    }
//        imageView.isVisible = false
//        // Здесь вы можете обновить UI для отображения текущего счета
//        // Например, использовать TextView для отображения баллов.
//    }
//}


