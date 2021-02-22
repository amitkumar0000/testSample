package com.test.ui.startwar.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.test.ui.databinding.ActivityMainBinding
import com.test.ui.startwar.adapter.PointsTableAdapter
import com.test.ui.startwar.datasource.LocalJson
import com.test.ui.startwar.datasource.PointTableManager
import com.test.ui.startwar.models.PointTableFeedState
import com.test.ui.startwar.repository.PointTableRepository
import com.test.ui.startwar.viewmodel.PointTableViewModel
import io.reactivex.android.schedulers.AndroidSchedulers

class StarWarPointsTableActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(LayoutInflater.from(this)) }
    private val pointTableAdapter by lazy { PointsTableAdapter() }

    private val viewModel by lazy {
        ViewModelProvider(this, object : ViewModelProvider.Factory{
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return PointTableViewModel(PointTableRepository((PointTableManager(LocalJson(this@StarWarPointsTableActivity))))) as T
            }
        }).get(PointTableViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initPointsList()
        attachPointTableObserver()
        viewModel.fetchPointTableDetails()

    }

    private fun attachPointTableObserver() {
        viewModel.state()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                     render(it)
            },{})
    }

    private fun render(pointTableFeedState: PointTableFeedState) {
        when(pointTableFeedState) {
            is PointTableFeedState.Content -> {
                pointTableAdapter.setData(pointTableFeedState.playerlist)
            }
            is PointTableFeedState.Loading -> {}
            is PointTableFeedState.Error -> {}
        }
    }

    private fun initPointsList() {
        binding.pointList.layoutManager = LinearLayoutManager(this)
        binding.pointList.adapter = pointTableAdapter
    }
}