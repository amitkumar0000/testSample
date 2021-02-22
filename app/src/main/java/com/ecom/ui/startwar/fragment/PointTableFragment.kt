package com.ecom.ui.startwar.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ecom.ui.databinding.FragmentPointTableBinding
import com.ecom.ui.startwar.adapter.PointsTableAdapter
import com.ecom.ui.startwar.datasource.LocalJson
import com.ecom.ui.startwar.datasource.PointTableManager
import com.ecom.ui.startwar.models.PointTableFeedState
import com.ecom.ui.startwar.repository.PointTableRepository
import com.ecom.ui.startwar.viewmodel.PointTableViewModel
import io.reactivex.android.schedulers.AndroidSchedulers

class PointTableFragment : Fragment() {
    private lateinit var binding: FragmentPointTableBinding


    private val viewModel by lazy {
        ViewModelProvider(requireActivity(), object : ViewModelProvider.Factory{
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return PointTableViewModel(PointTableRepository((PointTableManager(LocalJson(requireContext()))))) as T
            }
        }).get(PointTableViewModel::class.java)
    }

    private val pointTableAdapter by lazy { PointsTableAdapter(viewModel) }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = FragmentPointTableBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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
        binding.pointList.layoutManager = LinearLayoutManager(requireContext())
        binding.pointList.adapter = pointTableAdapter
    }

    companion object {
        @JvmStatic
        fun newInstance() =
                PointTableFragment()
    }
}