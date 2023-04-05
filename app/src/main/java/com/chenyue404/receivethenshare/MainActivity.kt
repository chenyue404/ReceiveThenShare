package com.chenyue404.receivethenshare

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log

class MainActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        StringBuilder().apply {
            append("Action: ${intent.action}\n")
            append("URI: ${intent.toUri(Intent.URI_INTENT_SCHEME)}\n")
            log(toString())
        }

        val extraStr = intent.getStringExtra("text") ?: kotlin.run {
            log("没收到内容")
            finish()
            return
        }
        log("收到：$extraStr")

        val sendIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, extraStr)
            type = "text/plain"
        }

        val shareIntent = Intent.createChooser(sendIntent, null)
            .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(shareIntent)
        finish()
    }

    private fun log(str: String) {
        Log.d("TextReceiver", str)
    }
}