package com.test.ui.startwar.datasource

import com.test.ui.startwar.models.MatchDetails
import com.test.ui.startwar.models.Player
import com.test.ui.startwar.utils.Utils
import io.reactivex.Single

class PointTableManager(private val ljson: LocalJson) {

    fun fetchDetails(): Single<List<Player>> {
        return ljson.fetchPointTableDetails()
    }

    fun fetchMatchDetailsList(id: Int): Single<List<MatchDetails>> {
        return Single.just(Utils.playerList[id].matchList)
    }

    fun fetchMatchDetails(): Single<List<MatchDetails>> {
        return Single.just(listOf())
    }

    fun fetchMatchDetailsScore(playerList: List<Player>): Single<List<Player>> {
        return ljson.fetchMatchTableDetails(playerList)
    }
}