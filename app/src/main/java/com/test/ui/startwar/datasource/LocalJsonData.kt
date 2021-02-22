package com.test.ui.startwar.datasource

import com.test.ui.startwar.models.MatchDetails
import com.test.ui.startwar.models.Player
import io.reactivex.Single

class LocalJsonData {
    fun fetchPointTableDetails(): Single<List<Player>> {
        //TODO read from Json
        return Single.just(listOf(Player(1,"name", icon = "http://icons.iconarchive.com/icons/creativeflip/starwars-longshadow-flat/128/Boba-Fett-icon.png")))
    }

    fun fetchMatchTableDetails(id: Int): Single<List<MatchDetails>> {
        //TODO read from Json
        return Single.just(listOf(MatchDetails("Name1",90, "name2", 80)))
    }
}