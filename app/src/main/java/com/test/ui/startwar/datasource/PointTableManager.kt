package com.test.ui.startwar.datasource

import com.test.ui.startwar.models.MatchDetails
import com.test.ui.startwar.models.Player
import io.reactivex.Single

class PointTableManager(private val ljson: LocalJson) {

    fun fetchDetails(): Single<List<Player>> {
        return ljson.fetchPointTableDetails()
    }

    fun fetchMatchDetails(id: Int): Single<List<MatchDetails>> {
        return ljson.fetchMatchTableDetails(id)
    }
}