package com.ecom.ui.startwar.adapter

import androidx.recyclerview.widget.DiffUtil
import com.ecom.ui.startwar.models.MatchDetails

class MatchDetailsDiffUtils(private val oldDetails: List<MatchDetails>, private val newDetails: List<MatchDetails>): DiffUtil.Callback() {
    override fun getOldListSize() = oldDetails.size
    override fun getNewListSize() = newDetails.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldDetails[oldItemPosition] == newDetails[newItemPosition]
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldDetails[oldItemPosition].player1Name == newDetails[newItemPosition].player1Name &&
                oldDetails[oldItemPosition].player2Name == newDetails[newItemPosition].player2Name
    }
}