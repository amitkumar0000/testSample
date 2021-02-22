package com.ecom.ui.startwar.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ecom.ui.databinding.FragmentMatchDetailsBinding
import com.ecom.ui.startwar.adapter.MatchDetailsAdapter
import com.ecom.ui.startwar.datasource.LocalJson
import com.ecom.ui.startwar.datasource.PointTableManager
import com.ecom.ui.startwar.datasource.models.MatchDetailsFeedState
import com.ecom.ui.startwar.repository.PointTableRepository
import com.ecom.ui.startwar.viewmodel.PointTableViewModel
import io.reactivex.android.schedulers.AndroidSchedulers

private const val ARG_ID = "ID"

class MatchDetailsFragment : Fragment() {
    private var playerId: Int? = null
    private lateinit var binding: FragmentMatchDetailsBinding

    private val matchDetailsAdapter by lazy { MatchDetailsAdapter() }

    private val viewModel by lazy {
        ViewModelProvider(requireActivity(), object : ViewModelProvider.Factory{
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return PointTableViewModel(PointTableRepository((PointTableManager(LocalJson(requireContext().applicationContext))))) as T
            }
        }).get(PointTableViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            playerId = it.getInt(ARG_ID)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        playerId?.let {
            initMatchDetailsList()
            attachMatchDetailsObserver()
            viewModel.fetchMatchDetails(id = it)
        }
    }

    private fun attachMatchDetailsObserver() {
        viewModel.matchDetailState()
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
        binding.matchDetailsList.layoutManager = LinearLayoutManager(requireContext())
        binding.matchDetailsList.adapter = matchDetailsAdapter
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMatchDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance(id: Int) =
            MatchDetailsFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_ID, id)
                }
            }
    }
}