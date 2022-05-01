package com.example.task105

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast

private const val CODE_KEY = "key for pin code"
private const val COLOR_KEY = "key for color"

class MainActivity : AppCompatActivity() {

    private lateinit var enterCode: TextView
    private lateinit var pinCode: TextView
    private lateinit var text1: TextView
    private lateinit var text2: TextView
    private lateinit var text3: TextView
    private lateinit var text4: TextView
    private lateinit var text5: TextView
    private lateinit var text6: TextView
    private lateinit var text7: TextView
    private lateinit var text8: TextView
    private lateinit var text9: TextView
    private lateinit var text0: TextView
    private lateinit var clearTV: TextView
    private lateinit var okTV: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViews()
        setOnClick()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.i("MainActivity", pinCode.text.toString())
        outState.putString(CODE_KEY, pinCode.text.toString())
        outState.putInt(COLOR_KEY, pinCode.currentTextColor)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        clearEditText()
        Log.i("MainActivity", "onRestore: ${savedInstanceState.getString(CODE_KEY)}")
        pinCode.text = savedInstanceState.getString(CODE_KEY)
        pinCode.setTextColor(savedInstanceState.getInt(COLOR_KEY))
    }

    private fun setOnClick() {
        text1.setOnClickListener {
            clearEditText()
            pinCode.append('1'.toString())
        }
        text2.setOnClickListener {
            clearEditText()
            pinCode.append('2'.toString())
        }
        text3.setOnClickListener {
            clearEditText()
            pinCode.append('3'.toString())
        }
        text4.setOnClickListener {
            clearEditText()
            pinCode.append('4'.toString())
        }
        text5.setOnClickListener {
            clearEditText()
            pinCode.append('5'.toString())
        }
        text6.setOnClickListener {
            clearEditText()
            pinCode.append('6'.toString())
        }
        text7.setOnClickListener {
            clearEditText()
            pinCode.append('7'.toString())
        }
        text8.setOnClickListener {
            clearEditText()
            pinCode.append('8'.toString())
        }
        text9.setOnClickListener {
            clearEditText()
            pinCode.append('9'.toString())
        }
        text0.setOnClickListener {
            clearEditText()
            pinCode.append('0'.toString())
        }
        clearTV.setOnClickListener {
            clearEditText()
            removeLast()
        }
        okTV.setOnClickListener {
            checkIsCodeCorrect()
        }
    }

    private fun checkIsCodeCorrect() {
        val code = pinCode.text.toString()
        val correctCode = "1567"
        if (code == correctCode) {
            pinCode.setTextColor(resources.getColor(R.color.blue_white))
            val intent = Intent(this, CorrectCodeActivity::class.java)
            startActivity(intent)
        } else {
            pinCode.setTextColor(resources.getColor(R.color.red))
        }
    }

    private fun removeLast() {
        pinCode.setTextColor(resources.getColor(R.color.grey))
        if (pinCode.text.isNotEmpty()) {
            pinCode.text = pinCode.text.substring(0, pinCode.text.length - 1)
        }
    }

    private fun findViews() {
        enterCode = findViewById(R.id.edit_code_tv)
        pinCode = findViewById(R.id.pin_code)
        text1 = findViewById(R.id.text_view_1)
        text2 = findViewById(R.id.text_view_2)
        text3 = findViewById(R.id.text_view_3)
        text4 = findViewById(R.id.text_view_4)
        text5 = findViewById(R.id.text_view_5)
        text6 = findViewById(R.id.text_view_6)
        text7 = findViewById(R.id.text_view_7)
        text8 = findViewById(R.id.text_view_8)
        text9 = findViewById(R.id.text_view_9)
        text0 = findViewById(R.id.text_view_0)
        clearTV = findViewById(R.id.text_view_clear)
        okTV = findViewById(R.id.text_view_ok)
    }

    private fun clearEditText() {
        if (enterCode.text.contains("Введ")) {
            enterCode.text = ""
        }
    }
}