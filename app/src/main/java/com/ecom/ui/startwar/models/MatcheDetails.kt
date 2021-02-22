package com.ecom.ui.startwar.models

data class MatchedDetails(val match: Int, val player1: Player1, val player2: Player2)

data class Player1(val id: Int, val score: Int)
data class Player2(val id: Int, val score: Int)