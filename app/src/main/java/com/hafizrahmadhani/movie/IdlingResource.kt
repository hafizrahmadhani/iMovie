package com.hafizrahmadhani.movie

import androidx.test.espresso.idling.CountingIdlingResource

object IdlingResource {
    private const val RESOURCE = "GLOBAL"
    val countingIdlingResource = CountingIdlingResource(RESOURCE)

    fun idlingIncrement() {
        countingIdlingResource.increment()
    }

    fun idlingDecrement() {
        countingIdlingResource.decrement()
    }
}