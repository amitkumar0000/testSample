package com.ecom.ui.startwar.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.ecom.ui.databinding.MatchesRowBinding

class MatchSummaryViewHolder(binding: MatchesRowBinding): RecyclerView.ViewHolder(binding.root) {
    val player1TextView = binding.player1TextView
    val player2TextView = binding.player2TextView
    val scoreTextView = binding.matchScoreTextView
    val matchRowLayout = binding.matchRowLayout
}