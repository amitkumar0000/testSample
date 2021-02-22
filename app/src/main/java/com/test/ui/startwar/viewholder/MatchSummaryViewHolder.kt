package com.test.ui.startwar.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.test.ui.databinding.MatchesRowBinding

class MatchSummaryViewHolder(binding: MatchesRowBinding): RecyclerView.ViewHolder(binding.root) {
    val player1TextView = binding.player1TextView
    val player2TextView = binding.player2TextView
    val scoreTextView = binding.matchScoreTextView
}