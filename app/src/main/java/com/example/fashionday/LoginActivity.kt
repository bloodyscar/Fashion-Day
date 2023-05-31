package com.example.fashionday

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.widget.TextView

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        setupHyperlink()
    }

    private fun setupHyperlink() {
        val linkTextView = findViewById<TextView>(R.id.tvRegister)
        linkTextView.movementMethod = LinkMovementMethod.getInstance();
    }
}