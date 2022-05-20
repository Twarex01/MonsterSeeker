package com.example.monsterseeker

import androidx.lifecycle.Lifecycle
import androidx.test.core.app.ActivityScenario
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class DetailedActivityInjectionTest {

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @Test
    fun verifyInjection() {
        ActivityScenario.launch(DetailedActivity::class.java).use {
            it.moveToState(Lifecycle.State.STARTED)
            it.onActivity { activity ->
                assertThat(activity.model).isNotNull()
            }
        }
    }
}
