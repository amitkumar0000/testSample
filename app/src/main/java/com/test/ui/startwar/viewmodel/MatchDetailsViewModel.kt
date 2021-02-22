package com.test.ui.startwar.viewmodel

import androidx.lifecycle.ViewModel
import com.test.ui.startwar.models.MatchDetailsFeedState
import com.test.ui.startwar.models.PointTableFeedState
import com.test.ui.startwar.repository.PointTableRepository
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject

class MatchDetailsViewModel(private val respository: PointTableRepository): ViewModel() {

    private var state = BehaviorSubject.create<MatchDetailsFeedState>()

    private var disposable = CompositeDisposable()

    fun state(): Observable<MatchDetailsFeedState> {
        return state.hide()
    }

    fun fetchMatchDetails(id: Int) {
        state.onNext(MatchDetailsFeedState.Loading)
        disposable.add(
            respository.fetchPointsDetails(id)
                .subscribeOn(Schedulers.io())
                .subscribe({
                    state.onNext(MatchDetailsFeedState.Content(it))
                },{
                    state.onNext(MatchDetailsFeedState.Error)
                })
        )
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}