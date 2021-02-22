package com.test

import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.test.ui.startwar.activity.StarWarPointsTableActivity
import okio.buffer
import okio.source
import javax.inject.Provider


fun String.toFileContents(): String {
    return StarWarPointsTableActivity::class.java.classLoader.getResourceAsStream(removePrefix("/"))!!.use { inputStream ->
        inputStream.source().buffer().readUtf8()
    }
}

inline fun<reified T : ViewModel> FragmentActivity.getViewModel(provider: Provider<T>): T {
    return ViewModelProvider(this, object: ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            @Suppress("UNCHECKED_CAST")
            return provider.get() as T
        }

    }).get(T::class.java)
}