package com.test.ui.startwar.models


data class Player(val id: Int, val name: String, val icon: String, val score: Int = 0, var matchList: List<MatchDetails> = listOf())

