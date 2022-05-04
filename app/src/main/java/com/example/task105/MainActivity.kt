package com.example.task105

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts


class MainActivity : AppCompatActivity() {

    private lateinit var enterCode: TextView
    private lateinit var pinCode: TextView
    private lateinit var clearTV: TextView
    private lateinit var okTV: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        enterCode = findViewById(R.id.edit_code_tv)
        pinCode = findViewById(R.id.pin_code)
        clearTV = findViewById(R.id.text_view_clear)
        okTV = findViewById(R.id.text_view_ok)

        textViewsOnClick()
        setOnClick()
    }

    private fun textViewsOnClick() {
        val textViewsID: Array<Int> = arrayOf(
            R.id.text_view_0,
            R.id.text_view_1,
            R.id.text_view_2,
            R.id.text_view_3,
            R.id.text_view_4,
            R.id.text_view_5,
            R.id.text_view_6,
            R.id.text_view_7,
            R.id.text_view_8,
            R.id.text_view_9
        )
        textViewsID.forEach {
            val textView = findViewById<TextView>(it)
            textView.setOnClickListener {
                clearEditText()
                pinCode.append(textView.text)
            }
        }
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

    private fun setOnClick() {
        clearTV.setOnClickListener {
            clearEditText()
            removeLast()
        }
        okTV.setOnClickListener {
            checkIsCodeCorrect()
        }
    }

    /*private val resultActivityResult: ActivityResultLauncher<Intent> =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {

            if (it.resultCode == Activity.RESULT_OK) {
                val stringExtra: String? = it.data?.getStringExtra(Const.RESULT_KEY)
                // let(block) executes only when an object is not null
                stringExtra?.let { result ->
                    Toast.makeText(this, result, Toast.LENGTH_LONG).show()
                }
            }

        }*/

    private fun checkIsCodeCorrect() {
        val code = pinCode.text.toString()
        val correctCode = "1567"
        if (code == correctCode) {
            pinCode.setTextColor(resources.getColor(R.color.blue_white))
            val intent = Intent(this, ResultActivity::class.java)
            startActivity(intent)
            //resultActivityResult.launch(intent)
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

    private fun clearEditText() {
        if (enterCode.text.contains("Введ")) {
            enterCode.text = ""
        }
    }
}