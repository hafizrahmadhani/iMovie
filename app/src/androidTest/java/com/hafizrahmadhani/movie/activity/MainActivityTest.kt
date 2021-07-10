package com.hafizrahmadhani.movie.activity

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.hafizrahmadhani.movie.R
import com.hafizrahmadhani.movie.data.BankData
import org.junit.Rule
import org.junit.Test

class MainActivityTest {
    private val dummyMovie = BankData.dataMovie()
    private val dummyTvShow = BankData.dataTvShow()

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun loadMovie(){
        onView(withId(R.id.rv_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_movie)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dummyMovie.size))
        onView(withId(R.id.rv_movie)).perform(click())
    }

    @Test
    fun loadDetailMovie(){
        onView(withId(R.id.rv_movie)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(withId(R.id.mov_title)).check(matches(isDisplayed()))
        onView(withId(R.id.mov_poster)).check(matches(isDisplayed()))
        onView(withId(R.id.mov_poster2)).check(matches(isDisplayed()))
        onView(withId(R.id.mov_genre)).check(matches(isDisplayed()))
        onView(withId(R.id.mov_date)).check(matches(isDisplayed()))
        onView(withId(R.id.mov_description)).check(matches(isDisplayed()))

        onView(withId(R.id.mov_title)).check(matches(withText(dummyMovie[0].title)))
        onView(withId(R.id.mov_genre)).check(matches(withText(dummyMovie[0].genre)))
        onView(withId(R.id.mov_date)).check(matches(withText(dummyMovie[0].date)))
        onView(withId(R.id.mov_description)).check(matches(withText(dummyMovie[0].description)))
    }

    @Test
    fun loadTvShow(){
        onView(withText("TV Shows")).perform(click())
        onView(withId(R.id.rv_tvshow)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_tvshow)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dummyMovie.size))
        onView(withId(R.id.rv_tvshow)).perform(click())
    }

    @Test
    fun loadDetailTvShow(){
        onView(withText("TV Shows")).perform(click())
        onView(withId(R.id.rv_tvshow)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(withId(R.id.mov_title)).check(matches(isDisplayed()))
        onView(withId(R.id.mov_title)).check(matches(withText(dummyTvShow[0].title)))
        onView(withId(R.id.mov_poster)).check(matches(isDisplayed()))
        onView(withId(R.id.mov_poster2)).check(matches(isDisplayed()))
        onView(withId(R.id.mov_genre)).check(matches(isDisplayed()))
        onView(withId(R.id.mov_genre)).check(matches(withText(dummyTvShow[0].genre)))
        onView(withId(R.id.mov_date)).check(matches(isDisplayed()))
        onView(withId(R.id.mov_date)).check(matches(withText(dummyTvShow[0].date)))
        onView(withId(R.id.mov_description)).check(matches(isDisplayed()))
        onView(withId(R.id.mov_description)).check(matches(withText(dummyTvShow[0].description)))
    }
}