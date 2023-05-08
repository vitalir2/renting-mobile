package com.renting.app.android.core.system

import android.content.Context
import android.content.Intent
import androidx.core.net.toUri

fun Context.openPhoneApp(phoneNumber: String) {
    val intent = Intent(Intent.ACTION_DIAL).apply {
        data = "tel:$phoneNumber".toUri()
    }
    startActivity(intent)
}
