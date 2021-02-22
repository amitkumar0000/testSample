package com.test.ui.startwar.utils

import Players
import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.test.ui.startwar.models.Player
import okio.buffer
import okio.source
import org.json.JSONArray
import org.json.JSONObject
import java.lang.IllegalStateException
import java.lang.reflect.Type


object Utils {
    val playerList = arrayListOf<Player>()


    fun toFileContents(filename: String): String {
        return Utils::class.java.classLoader.getResourceAsStream(filename.removePrefix("/"))!!.use { inputStream ->
            inputStream.source().buffer().readUtf8()
        }

    }

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