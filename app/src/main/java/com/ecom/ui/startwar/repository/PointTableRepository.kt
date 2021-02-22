package com.ecom.ui.startwar.repository

import com.ecom.ui.startwar.datasource.PointTableManager
import com.ecom.ui.startwar.models.MatchDetails
import com.ecom.ui.startwar.models.Player
import io.reactivex.Single

class PointTableRepository(private val manager: PointTableManager) {

    fun fetchPointsDetails(): Single<List<Player>> {
        return manager.fetchDetails()
    }

    fun fetchMatchDetailsList(id: Int): Single<List<MatchDetails>> {
        return manager.fetchMatchDetailsList(id)
    }

    fun fetchMatchDetails(playerList: List<Player>): Single<List<Player>> {
        return manager.fetchMatchDetailsScoreWithPlayersInfo(playerList)
    }


}