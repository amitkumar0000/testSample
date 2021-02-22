package com.ecom.ui.startwar.datasource.models

sealed class PointTableFeedState {
    object Loading: PointTableFeedState()
    object Error: PointTableFeedState()
    data class Content(val playerlist: List<Player>): PointTableFeedState()
    data class LoadMatchDetails(val id: Int): PointTableFeedState()
}
