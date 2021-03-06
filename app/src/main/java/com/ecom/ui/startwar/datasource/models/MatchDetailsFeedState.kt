package com.ecom.ui.startwar.datasource.models


sealed class MatchDetailsFeedState {
    object Loading: MatchDetailsFeedState()
    object Error: MatchDetailsFeedState()
    data class Content(val matchDetailsList: List<MatchDetails>): MatchDetailsFeedState()
}
