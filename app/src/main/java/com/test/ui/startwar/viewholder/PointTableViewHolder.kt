package com.test.ui.startwar.viewholder

import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.test.ui.databinding.PointRowBinding

internal class PointTableViewHolder(binding: PointRowBinding): RecyclerView.ViewHolder(binding.root) {
    var name: TextView = binding.playerName
    val icon: ImageView = binding.playerImage
    val scoreTextView: TextView = binding.scoreText
}