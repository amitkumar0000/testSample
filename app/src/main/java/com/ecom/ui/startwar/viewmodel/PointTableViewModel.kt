package com.ecom.ui.startwar.viewmodel

import androidx.lifecycle.ViewModel
import com.ecom.ui.startwar.models.PointTableFeedState
import com.ecom.ui.startwar.repository.PointTableRepository
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject

class PointTableViewModel(private val respository: PointTableRepository): ViewModel() {

    private var state = BehaviorSubject.create<PointTableFeedState>()

    private var disposable = CompositeDisposable()

    fun state(): Observable<PointTableFeedState> {
      return state.hide()
    }

    fun fetchPointTableDetails() {
        state.onNext(PointTableFeedState.Loading)
        disposable.add(
            respository.fetchPointsDetails()
                .flatMap { respository.fetchMatchDetails(it) }
                .subscribeOn(Schedulers.io())
                .subscribe({
                    state.onNext(PointTableFeedState.Content(it))
                },{
                    state.onNext(PointTableFeedState.Error)
                })
        )
    }

    fun attachMatchDetailFragment(id: Int) {
        state.onNext(PointTableFeedState.LoadMatchDetails(id))
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }

}