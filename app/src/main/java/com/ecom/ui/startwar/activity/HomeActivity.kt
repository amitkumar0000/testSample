package com.ecom.ui.startwar.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ecom.ui.databinding.ActivityMainBinding
import com.ecom.ui.startwar.datasource.LocalJson
import com.ecom.ui.startwar.datasource.PointTableManager
import com.ecom.ui.startwar.fragment.MatchDetailsFragment
import com.ecom.ui.startwar.fragment.PointTableFragment
import com.ecom.ui.startwar.models.PointTableFeedState
import com.ecom.ui.startwar.repository.PointTableRepository
import com.ecom.ui.startwar.viewmodel.PointTableViewModel
import io.reactivex.android.schedulers.AndroidSchedulers


class HomeActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(LayoutInflater.from(this)) }

    private val viewModel by lazy {
        ViewModelProvider(this, object : ViewModelProvider.Factory{
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return PointTableViewModel(PointTableRepository((PointTableManager(LocalJson(this@HomeActivity))))) as T
            }
        }).get(PointTableViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        supportFragmentManager.beginTransaction()
                .replace(binding.container.id, PointTableFragment.newInstance(), "PointTableFragment")
                .addToBackStack("pointTable")
                .commit()


        viewModel.state().
                observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                           when(it) {
                               is PointTableFeedState.LoadMatchDetails -> {
                                   attachMatchDetailsPage(it.id)
                               }
                               else -> {}
                           }

                },{

                })

    }

    private fun attachMatchDetailsPage(id: Int) {
        supportFragmentManager.beginTransaction()
                .replace(binding.container.id, MatchDetailsFragment.newInstance(id), "PointTableFragment")
                .addToBackStack("pointTable")
                .commit()
    }
}