package com.andro.indie.school.common.extension

import android.app.Activity
import android.content.Context
import android.widget.ImageView
import coil.api.load
import com.andro.indie.school.R
import com.andro.indie.school.common.custom.IntentHelper
import java.text.SimpleDateFormat
import java.util.Locale

/**
 * Created by herisulistiyanto on 2019-11-05.
 * KjokenKoddinger
 */

fun String?.convertDate(inputFormat: String = "yyyy-MM-dd",
                        outputFormat: String = "dd MMMM yyyy",
                        localeId: Locale = Locale.getDefault()): String {
    if (!this.isNullOrEmpty()) {
        val dateFormat = SimpleDateFormat(inputFormat, localeId)
        val requiredFormat = SimpleDateFormat(outputFormat, localeId)
        try {
            val date = dateFormat.parse(this)
            date?.let {
                return requiredFormat.format(it)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
    return ""
}

fun ImageView.loadImageWithUrl(partUrl: String?, isLarge: Boolean = false) {
    val baseUrl = when {
        isLarge -> "https://image.tmdb.org/t/p/w500/"
        else -> "https://image.tmdb.org/t/p/w300/"
    }
    val fullUrl = baseUrl + partUrl.orEmpty()
    this.load(fullUrl) {
        crossfade(false)
        placeholder(R.drawable.img_placeholder)
        error(R.drawable.img_placeholder)
    }
}

inline fun <reified T: Activity> Context.launchActivity(vararg params: Pair<String, Any?>) {
    IntentHelper.internalStartActivity(this, T::class.java, params)
}
