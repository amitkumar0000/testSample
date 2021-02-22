package com.test.ui.startwar.repository

import com.test.ui.startwar.datasource.PointTableManager
import com.test.ui.startwar.models.MatchDetails
import com.test.ui.startwar.models.Player
import io.reactivex.Single

class PointTableRepository(private val manager: PointTableManager) {

    fun fetchPointsDetails(): Single<List<Player>> {
        return manager.fetchDetails()
    }

    fun fetchPointsDetails(id: Int): Single<List<MatchDetails>> {
        return manager.fetchMatchDetails(id)
    }


}