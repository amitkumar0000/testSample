package com.ecom.ui.startwar.utils

import android.content.Context
import com.ecom.ui.startwar.datasource.models.Player
import org.json.JSONArray
import java.lang.IllegalStateException


object Utils {
    val playerList = arrayListOf<Player>()


    fun findPlayer(id: Int): Player {
        playerList.forEach {
            if(id == it.id)
                return it
        }

        throw IllegalStateException("Player id does'nt exit")
    }

    fun loadFromJson(context: Context, filename: String): JSONArray {
        var json: String? = null

        try {
            val inputStream = context.assets.open(filename)
            val size: Int = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()
            json = String(buffer, Charsets.UTF_8)
        } catch (e: Exception) {
            println("Exception occur: ${e.message}")
            e.printStackTrace()
        }

        val jsonObject = JSONArray(json)

        return jsonObject
    }
}