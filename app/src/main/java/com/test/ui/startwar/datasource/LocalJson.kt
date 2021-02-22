package com.test.ui.startwar.datasource

import Players
import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import com.test.ui.startwar.models.MatchDetails
import com.test.ui.startwar.models.MatchedDetails
import com.test.ui.startwar.models.Player
import com.test.ui.startwar.utils.Utils
import io.reactivex.Single
import org.json.JSONArray
import java.lang.reflect.Type


class LocalJson(val context: Context) {

    fun fetchPointTableDetails(): Single<List<Player>> {
        val jsonObject: JSONArray = getStarWarPlayer(context, "StarWarsPlayers.json")

        val list = ArrayList<Player>()
        for (i in 0 until jsonObject.length()) {
            val jsonObject = jsonObject.getJSONObject(i)

            val name = jsonObject["name"] as String
            val id = jsonObject["id"] as Int
            val icon = jsonObject["icon"] as String

            list.add(Player(name = name, id = id, icon = icon))
        }
        return Single.just(list)
    }

    private fun getStarWarPlayer(context: Context, fileName: String): JSONArray {
        val json = Utils.loadFromJson(context, fileName)
        return json
    }


    fun fetchMatchTableDetails(playerList: List<Player>): Single<List<Player>> {
        val jsonObject: JSONArray = Utils.loadFromJson(context, "StarWarsMatches.json")

        val map = HashMap<Int, Player>()

        playerList.forEach { map[it.id] = it }

        for (i in 0 until jsonObject.length()) {
            val json = jsonObject.getJSONObject(i)

            val matchDetails = Gson().fromJson(json.toString(), MatchedDetails::class.java)
            val player1 = map[matchDetails.player1.id]

            player1?.let { it.score += matchDetails.player1.score }

            val player2 = map[matchDetails.player2.id]
            player2?.let { it.score += matchDetails.player2.score }

            val matchedDetails = MatchDetails(
                player1Name = player1?.name!!,
                player1Score = matchDetails.player1.score,
                player2Name = player2?.name!!,
                player2Score = matchDetails.player2.score)

            player1.matchList.add(matchedDetails)
            player2.matchList.add(matchedDetails)


           map[player1.id] = player1
            map[player2.id] = player2
        }

        val list = map.values.toList()

        return Single.just(map.values.toList())
    }
}