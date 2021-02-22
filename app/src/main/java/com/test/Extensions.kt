package com.test

import com.test.ui.startwar.activity.StarWarPointsTableActivity
import okio.buffer
import okio.source

fun String.toFileContents(): String {
    return StarWarPointsTableActivity::class.java.classLoader.getResourceAsStream(removePrefix("/"))!!.use { inputStream ->
        inputStream.source().buffer().readUtf8()
    }
}