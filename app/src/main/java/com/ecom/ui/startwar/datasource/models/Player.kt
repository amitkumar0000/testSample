package com.ecom.ui.startwar.datasource.models


data class Player(val id: Int, val name: String, val icon: String, var score: Int = 0, var matchList: ArrayList<MatchDetails> = arrayListOf())

