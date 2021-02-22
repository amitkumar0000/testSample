package com.test.ui.startwar.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.test.ui.databinding.MatchesRowBinding
import com.test.ui.startwar.models.MatchDetails
import com.test.ui.startwar.viewholder.MatchSummaryViewHolder

class MatchDetailsAdapter : RecyclerView.Adapter<MatchSummaryViewHolder>() {

    private val matchDetailsList = arrayListOf<MatchDetails>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchSummaryViewHolder {
        return MatchSummaryViewHolder(MatchesRowBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: MatchSummaryViewHolder, position: Int) {
        matchDetailsList[position].apply {
            holder.player1TextView.text = player1Name
            holder.player2TextView.text = player2Name
            holder.scoreTextView.text = "$player1Score - $player2Score"
        }
    }

    override fun getItemCount(): Int {
        return matchDetailsList.count()
    }

    fun setData(newMatchDetailsList: List<MatchDetails>) {
        val diffCallback = MatchDetailsDiffUtils(matchDetailsList, newMatchDetailsList)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        matchDetailsList.clear()
        matchDetailsList.addAll(newMatchDetailsList)
        diffResult.dispatchUpdatesTo(this)
    }
}