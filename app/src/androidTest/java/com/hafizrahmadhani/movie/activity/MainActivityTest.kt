package com.hafizrahmadhani.movie.activity

import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.pressBack
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.hafizrahmadhani.movie.IdlingResource
import com.hafizrahmadhani.movie.R
import kotlinx.coroutines.InternalCoroutinesApi
import org.hamcrest.Matchers.not
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@InternalCoroutinesApi
@RunWith(AndroidJUnit4ClassRunner::class)
class MainActivityTest {

    @Before
    fun setUp(){
        ActivityScenario.launch(MainActivity::class.java)
        IdlingRegistry.getInstance().register(IdlingResource.countingIdlingResource)
    }

    @After
    fun tearDown(){
        IdlingRegistry.getInstance().unregister(IdlingResource.countingIdlingResource)
    }

    @Test
    fun loadMovieList(){
        onView(withId(R.id.rv_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_movie)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
    }

    @Test
    fun loadDetailMovie(){
        onView(withId(R.id.rv_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_movie)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        checkDetail()
    }

    @Test
    fun loadTvShowList(){
        onView(withText("TV Shows")).perform(click())
        onView(withId(R.id.rv_tvshow)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_tvshow)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
    }

    @Test
    fun loadDetailTvShow(){
        onView(withText("TV Shows")).perform(click())
        onView(withId(R.id.rv_tvshow)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_tvshow)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        checkDetail()
    }

    @Test
    fun loadFavoriteMovieIfEmpty(){
        val notifMessage = "Tidak Ada Data"
        onView(withId(R.id.favorite_menu)).perform(click())
        onView(withId(R.id.notif_message_mov_favorite)).apply {
            check(matches(isDisplayed()))
            check(matches(withText(notifMessage)))
        }
    }

    @Test
    fun loadFavoriteTvIfEmpty(){
        val notifMessage = "Tidak Ada Data"
        onView(withId(R.id.favorite_menu)).perform(click())
        onView(withText("TV Shows")).perform(click())
        onView(withId(R.id.notif_message_tv_favorite)).apply {
            check(matches(isDisplayed()))
            check(matches(withText(notifMessage)))
        }
    }

    @Test
    fun addToFavoriteMovie(){
        val notifMessage = "Tidak Ada Data"
        onView(withId(R.id.rv_movie)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(withId(R.id.fab_detail)).perform(click())
        onView(isRoot()).perform(pressBack())
        onView(withId(R.id.favorite_menu)).perform(click())
        onView(withId(R.id.notif_message_mov_favorite)).check(matches(not(isDisplayed())))
        onView(withId(R.id.rv_movie_favorite)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        checkDetail()
        onView(withId(R.id.fab_detail)).perform(click())
        onView(isRoot()).perform(pressBack())
        onView(withId(R.id.notif_message_mov_favorite)).apply {
            check(matches(isDisplayed()))
            check(matches(withText(notifMessage)))
        }
    }

    @Test
    fun addToFavoriteTvShow(){
        val notifMessage = "Tidak Ada Data"
        onView(withText("TV Shows")).perform(click())
        onView(withId(R.id.rv_tvshow)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(withId(R.id.fab_detail)).perform(click())
        onView(isRoot()).perform(pressBack())
        onView(withId(R.id.favorite_menu)).perform(click())
        onView(withText("TV Shows")).perform(click())
        onView(withId(R.id.notif_message_tv_favorite)).check(matches(not(isDisplayed())))
        onView(withId(R.id.rv_tvshow_favorite)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        checkDetail()
        onView(withId(R.id.fab_detail)).perform(click())
        onView(isRoot()).perform(pressBack())
        onView(withId(R.id.notif_message_tv_favorite)).apply {
            check(matches(isDisplayed()))
            check(matches(withText(notifMessage)))
        }
    }

    @Test
    fun loadFavoriteDetailMovie(){
        onView(withId(R.id.rv_movie)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(withId(R.id.fab_detail)).perform(click())
        onView(isRoot()).perform(pressBack())
        onView(withId(R.id.favorite_menu)).perform(click())
        onView(withId(R.id.rv_movie_favorite)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        checkDetail()
    }

    @Test
    fun loadFavoriteDetailTvShow(){
        onView(withText("TV Shows")).perform(click())
        onView(withId(R.id.rv_tvshow)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(withId(R.id.fab_detail)).perform(click())
        onView(isRoot()).perform(pressBack())
        onView(withId(R.id.favorite_menu)).perform(click())
        onView(withText("TV Shows")).perform(click())
        onView(withId(R.id.rv_tvshow_favorite)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        checkDetail()
    }


    private fun checkDetail(){
        onView(withId(R.id.mov_title)).check(matches(isDisplayed()))
        onView(withId(R.id.mov_poster)).check(matches(isDisplayed()))
        onView(withId(R.id.mov_poster2)).check(matches(isDisplayed()))
        onView(withId(R.id.mov_genre)).check(matches(isDisplayed()))
        onView(withId(R.id.mov_date)).check(matches(isDisplayed()))
        onView(withId(R.id.mov_description)).check(matches(isDisplayed()))
    }
}