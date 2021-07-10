package com.hafizrahmadhani.movie

import androidx.test.espresso.idling.CountingIdlingResource

object IdlingResource {
    private const val RESOURCE: String = "GLOBAL"
    val espressoTestIdlingResource = CountingIdlingResource(RESOURCE)

    fun increment() {
        espressoTestIdlingResource.increment()
    }

    fun decrement() {
        espressoTestIdlingResource.decrement()
    }
}