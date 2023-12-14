package com.example.flightitinerary.utils

import android.os.Build
import androidx.annotation.RequiresApi
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone

class Utils {
    companion object {
        @RequiresApi(Build.VERSION_CODES.O)
        fun getTimestampFromStringDate(dateString: String): Long {
            try {
                val format = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault())
                val date = format.parse(dateString + "T00:00:00")
                return date?.time ?: 0
            } catch (e: Exception) {
                e.printStackTrace()
            }
            return 0
        }

        fun secondsToDate(secondes: Long): String {
            val timeZone = TimeZone.getDefault().id
            try {
                // Convertir les secondes en millisecondes
                val millisecondes = secondes * 1000

                // Créer un objet Date avec les millisecondes
                val date = Date(millisecondes)

                // Créer un objet SimpleDateFormat avec le fuseau horaire spécifié
                val simpleDateFormat = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
                simpleDateFormat.timeZone = TimeZone.getTimeZone(timeZone)

                // Formater la date en fonction du fuseau horaire
                return simpleDateFormat.format(date)
            } catch (e: Exception) {
                e.printStackTrace()
                return ""
            }
        }
    }
}