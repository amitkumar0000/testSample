package com.test.ui.startwar.adapter

import androidx.recyclerview.widget.DiffUtil
import com.test.ui.startwar.models.Player

class PointTablesDiffUtils(private val oldPlayer: List<Player>, private val newPlayer: List<Player>): DiffUtil.Callback() {
    override fun getOldListSize() = oldPlayer.size
    override fun getNewListSize() = newPlayer.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldPlayer[oldItemPosition] == newPlayer[newItemPosition]
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldPlayer[oldItemPosition].id == newPlayer[newItemPosition].id
    }
}