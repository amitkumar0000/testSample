package com.ecom.ui.startwar.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.ecom.ui.databinding.MatchesRowBinding
import com.ecom.ui.startwar.datasource.models.MatchDetails
import com.ecom.ui.startwar.viewholder.MatchSummaryViewHolder

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

            changeColor(holder, player1Score, player2Score)

        }
    }

    @SuppressLint("ResourceAsColor")
    private fun changeColor(holder: MatchSummaryViewHolder, score1: Int, score2: Int) {
        when {
            score1 > score2 ->  holder.matchRowLayout.setBackgroundColor(holder.player1TextView.context.getResources().getColor(android.R.color.holo_green_light))
            score1 < score2 ->  holder.matchRowLayout.setBackgroundColor(holder.player1TextView.context.getResources().getColor(android.R.color.holo_red_light))
            else -> {
                holder.matchRowLayout.setBackgroundColor(holder.player1TextView.context.getResources().getColor(android.R.color.white))
            }
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