package com.ecom.ui.startwar.models

sealed class PointTableFeedState {
    object Loading: PointTableFeedState()
    object Error: PointTableFeedState()
    data class Content(val playerlist: List<Player>): PointTableFeedState()
}
