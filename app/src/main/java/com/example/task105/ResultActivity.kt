package com.example.task105

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.TextView
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts

class ResultActivity : AppCompatActivity() {

    private lateinit var share: TextView
    private lateinit var sendMail: TextView
    private lateinit var call: TextView
    private lateinit var launchCameraTV: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        findViews()
        launchImplIntents()
    }

    private fun findViews() {
        share = findViewById(R.id.share_tv)
        sendMail = findViewById(R.id.send_by_mail_tv)
        call = findViewById(R.id.call_tv)
        launchCameraTV = findViewById(R.id.launch_cam_tv)
    }

    private fun launchImplIntents() {
        share.setOnClickListener {
            val sendIntent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, "some topics about java")
                type = "text/plain"
            }
            val shareIntent = Intent.createChooser(sendIntent, null)
            startActivity(shareIntent)
        }

        sendMail.setOnClickListener {
            val sendIntent = Intent().apply {
                action = Intent.ACTION_SENDTO
                data = Uri.parse("mailto:") // only email apps should handle this
                putExtra(Intent.EXTRA_TEXT, "send some message")
            }
            val shareIntent = Intent.createChooser(sendIntent, null)
            startActivity(shareIntent)
        }

        call.setOnClickListener {
            val intent = Intent().apply {
                action = Intent.ACTION_DIAL
                data = Uri.parse("tel:${87}")
            }
            val callIntent = Intent.createChooser(intent, null)
            startActivity(callIntent)
        }

        val resultActivityResult: ActivityResultLauncher<Intent> =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                // do some manipulation with result/response
            }

        launchCameraTV.setOnClickListener {
            val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            try {
                resultActivityResult.launch(takePictureIntent)
            } catch (e: ActivityNotFoundException) {
                // handle exception
            }
        }

    }
}