package com.ecom.ui.startwar.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ecom.ui.databinding.ActivityMatchDetailsBinding
import com.ecom.ui.startwar.adapter.MatchDetailsAdapter
import com.ecom.ui.startwar.datasource.LocalJson
import com.ecom.ui.startwar.datasource.PointTableManager
import com.ecom.ui.startwar.models.MatchDetailsFeedState
import com.ecom.ui.startwar.repository.PointTableRepository
import com.ecom.ui.startwar.viewmodel.MatchDetailsViewModel
import io.reactivex.android.schedulers.AndroidSchedulers

class MatchDetailsActivity : AppCompatActivity() {

    private val binding: ActivityMatchDetailsBinding by lazy { ActivityMatchDetailsBinding.inflate(LayoutInflater.from(this)) }
    private val matchDetailsAdapter by lazy { MatchDetailsAdapter() }

    private val viewModel by lazy {
        ViewModelProvider(this, object : ViewModelProvider.Factory{
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return MatchDetailsViewModel(PointTableRepository((PointTableManager(LocalJson(this@MatchDetailsActivity))))) as T
            }
        }).get(MatchDetailsViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val playerId = intent.getIntExtra("Player_id", -1)

        if(playerId != -1) {

            initMatchDetailsList()
            attachMatchDetailsObserver()
            viewModel.fetchMatchDetails(id = playerId)
        }
    }

    private fun attachMatchDetailsObserver() {
        viewModel.state()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                render(it)
            },{})
    }

    private fun render(matchDetailsFeedState: MatchDetailsFeedState) {
        when(matchDetailsFeedState) {
            is MatchDetailsFeedState.Content -> {
                matchDetailsAdapter.setData(matchDetailsFeedState.matchDetailsList)
            }
            is MatchDetailsFeedState.Loading -> {}
            is MatchDetailsFeedState.Error -> {}
        }
    }

    private fun initMatchDetailsList() {
        binding.matchDetailsList.layoutManager = LinearLayoutManager(this)
        binding.matchDetailsList.adapter = matchDetailsAdapter
    }
}