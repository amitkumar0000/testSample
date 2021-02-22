package com.ecom.ui.startwar.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ecom.ui.databinding.PointRowBinding
import com.ecom.ui.startwar.datasource.models.Player
import com.ecom.ui.startwar.utils.Utils
import com.ecom.ui.startwar.utils.Utils.playerList
import com.ecom.ui.startwar.viewholder.PointTableViewHolder
import com.ecom.ui.startwar.viewmodel.PointTableViewModel

internal class PointsTableAdapter(private val viewModel: PointTableViewModel): RecyclerView.Adapter<PointTableViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PointTableViewHolder {
        return PointTableViewHolder(PointRowBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: PointTableViewHolder, position: Int) {
        Utils.playerList[position].apply {
            Glide.with(holder.icon.context)
                .load(icon)
                .into(holder.icon)
            holder.name.text =  name
            holder.scoreTextView.text = "$score"

            holder.name.setOnClickListener {
                viewModel.attachMatchDetailFragment(id)
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
        playerList.sortByDescending { it.score }
        diffResult.dispatchUpdatesTo(this)

    }
}