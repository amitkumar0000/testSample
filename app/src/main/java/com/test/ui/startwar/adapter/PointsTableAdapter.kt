package com.test.ui.startwar.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.test.ui.databinding.PointRowBinding
import com.test.ui.startwar.activity.MatchDetailsActivity
import com.test.ui.startwar.models.Player
import com.test.ui.startwar.viewholder.PointTableViewHolder

internal class PointsTableAdapter: RecyclerView.Adapter<PointTableViewHolder>() {

    private val playerList = arrayListOf<Player>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PointTableViewHolder {
        return PointTableViewHolder(PointRowBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: PointTableViewHolder, position: Int) {
        playerList[position].apply {
            Glide.with(holder.icon).load(icon)
            holder.name.text =  name
            holder.scoreTextView.text = "$score"

            holder.name.setOnClickListener {
                it.context.startActivity(Intent(it.context, MatchDetailsActivity::class.java).apply {
                    putExtra("Player_id", id)
                })
            }
        }
    }

    override fun getItemCount(): Int {
        return playerList.count()
    }

    fun setData(newplayerlist: List<Player>) {
        val diffCallback = PointTablesDiffUtils(playerList, newplayerlist)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        playerList.clear()
        playerList.addAll(newplayerlist)
        diffResult.dispatchUpdatesTo(this)
    }
}