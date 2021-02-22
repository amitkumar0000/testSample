package com.ecom.ui.startwar.datasource

import com.ecom.ui.startwar.models.MatchDetails
import com.ecom.ui.startwar.models.Player
import com.ecom.ui.startwar.utils.Utils
import io.reactivex.Single

class PointTableManager(private val ljson: LocalJson) {

    fun fetchDetails(): Single<List<Player>> {
        return ljson.fetchPointTableDetails()
    }

    fun fetchMatchDetailsList(id: Int): Single<List<MatchDetails>> {
        return Single.just(Utils.findPlayer(id).matchList)
    }


    fun fetchMatchDetailsScoreWithPlayersInfo(playerList: List<Player>): Single<List<Player>> {
        return ljson.fetchMatchTableDetails(playerList)
    }
}