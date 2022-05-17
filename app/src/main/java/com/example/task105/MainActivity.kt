package com.example.task105

import android.content.Context
import android.content.Intent
import android.os.*
import androidx.appcompat.app.AppCompatActivity
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.TextView
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {

    private lateinit var enterCode: TextView
    private lateinit var pinCode: TextView
    private lateinit var clearBtn: Button
    private lateinit var okBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        enterCode = findViewById(R.id.edit_code_tv)
        pinCode = findViewById(R.id.pin_code)
        clearBtn = findViewById(R.id.clear_button)
        okBtn = findViewById(R.id.ok_button)

        numbersOnClick()
        setOnClick()
    }

    private fun numbersOnClick() {
        val buttonIDs: Array<Int> = arrayOf(
            R.id.btn_0,
            R.id.btn_1,
            R.id.btn_2,
            R.id.btn_3,
            R.id.btn_4,
            R.id.btn_5,
            R.id.btn_6,
            R.id.btn_7,
            R.id.btn_8,
            R.id.btn_9
        )
        buttonIDs.forEach {
            val buttonNumber = findViewById<TextView>(it)
            buttonNumber.setOnClickListener {
                clearEditText()
                pinCode.append(buttonNumber.text)
            }
        }
        clearDigitsOnLongClick()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(Const.CODE_KEY, pinCode.text.toString())
        outState.putInt(Const.COLOR_KEY, pinCode.currentTextColor)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        clearEditText()
        pinCode.text = savedInstanceState.getString(Const.CODE_KEY)
        pinCode.setTextColor(savedInstanceState.getInt(Const.COLOR_KEY))
    }

    private fun clearDigitsOnLongClick() {
        clearBtn.setOnLongClickListener {
            pinCode.text = ""
            pinCode.setTextColor(ContextCompat.getColor(this, R.color.grey))
            true
        }
    }

    private fun setOnClick() {
        clearBtn.setOnClickListener {
            clearEditText()
            removeLast()
        }
        okBtn.setOnClickListener {
            checkIsCodeCorrect()
        }
    }

    private fun checkIsCodeCorrect() {
        val code = pinCode.text.toString()
        val correctCode = "1567"
        if (code == correctCode) {
            pinCode.setTextColor(ContextCompat.getColor(this, R.color.blue_white))
            val intent = Intent(this, ResultActivity::class.java)
            startActivity(intent)
        } else {
            pinCode.setTextColor(ContextCompat.getColor(this, R.color.red))
            animateAndVibrate()
        }
    }

    private fun animateAndVibrate() {
        pinCode.startAnimation(AnimationUtils.loadAnimation(this, R.anim.shake_animation))
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            val vibratorManager =
                getSystemService(Context.VIBRATOR_MANAGER_SERVICE) as VibratorManager
            vibratorManager.defaultVibrator.vibrate(
                VibrationEffect.createOneShot(
                    200,
                    VibrationEffect.DEFAULT_AMPLITUDE
                )
            )
        } else {
            @Suppress("DEPRECATION")
            val s = getSystemService(VIBRATOR_SERVICE) as Vibrator
            s.vibrate(200)
        }

    }

    private fun removeLast() {
        pinCode.setTextColor(ContextCompat.getColor(this, R.color.grey))
        if (pinCode.text.isNotEmpty()) {
            pinCode.text = pinCode.text.dropLast(1)
            // pinCode.text = pinCode.text.substring(0, pinCode.text.length - 1)
        }
    }

    private fun clearEditText() {
        if (enterCode.text.contains("Введ")) {
            enterCode.text = ""
        }
    }
}