package com.ecom.ui.startwar.datasource

import android.content.Context
import com.google.gson.Gson
import com.ecom.ui.startwar.datasource.models.MatchDetails
import com.ecom.ui.startwar.datasource.models.TournamentDetails
import com.ecom.ui.startwar.datasource.models.Player
import com.ecom.ui.startwar.utils.Utils
import io.reactivex.Single
import org.json.JSONArray


class LocalJson(val context: Context) {

    fun fetchPointTableDetails(): Single<List<Player>> {
        val jsonObject: JSONArray = Utils.loadFromJson(context, "StarWarsPlayers.json")
        val list = ArrayList<Player>()
        for (i in 0 until jsonObject.length()) {
            list.add(Gson().fromJson(jsonObject.getJSONObject(i).toString(), Player::class.java))
        }

        return Single.just(list)
    }

    fun fetchMatchTableDetails(playerList: List<Player>): Single<List<Player>> {
        val jsonObject: JSONArray = Utils.loadFromJson(context, "StarWarsMatches.json")

        val map = HashMap<Int, Player>()

        playerList.forEach { map[it.id] = it }

        for (i in 0 until jsonObject.length()) {
            val json = jsonObject.getJSONObject(i)

            val matchDetails = Gson().fromJson(json.toString(), TournamentDetails::class.java)

            val player1 = map[matchDetails.player1.id]?.apply { score += matchDetails.player1.score }
            val player2 = map[matchDetails.player2.id]?.apply { score += matchDetails.player2.score }

            if(player1 == null || player2 == null)
                continue

            player1.matchList.apply {
                add(MatchDetails(
                        player1Name = player1.name,
                        player1Score = matchDetails.player1.score,
                        player2Name = player2.name,
                        player2Score = matchDetails.player2.score)
                )
            }

            player2.matchList.apply {
                add( MatchDetails(
                        player1Name = player2.name,
                        player1Score = matchDetails.player2.score,
                        player2Name = player1.name,
                        player2Score = matchDetails.player1.score)
                )
            }

            player1.apply { map[id] = player1 }
            player2.apply { map[id] = player2 }

        }

        return Single.just(map.values.toList())
    }

}