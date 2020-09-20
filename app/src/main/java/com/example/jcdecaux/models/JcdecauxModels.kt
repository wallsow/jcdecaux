package com.example.jcdecaux.models

import android.content.Context
import java.io.IOException
import java.lang.Exception

data class  Station(val number: Int, val name: String, val address: String, val latitude: Double, val longitude: Double)

fun getJsonDataFromAsset(context: Context, fileName: String): String? {
    val jsonString: String?
    try {
        jsonString = context.assets.open(fileName).bufferedReader().use {
            it.readText()
        }
    } catch (e: IOException) {
        e.printStackTrace()
        return null
    }
    return jsonString
}