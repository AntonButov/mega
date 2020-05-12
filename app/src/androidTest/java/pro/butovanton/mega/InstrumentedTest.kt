package pro.butovanton.mega

import android.util.Log
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit

@RunWith(AndroidJUnit4::class)
@LargeTest
class InstrumentedTest {
    @Test
    fun useAppContext() {
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        Assert.assertEquals("pro.butovanton.mega", appContext.packageName)
    }

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Test
    @Throws(Throwable::class)
    fun ViewModel() {
        val count = CountDownLatch(1)
        val viewModelModel = ViewModelModel()
        viewModelModel.getModel().observeForever { model ->
            Log.d("DEBUG", "model size: $model")
            try {
                count.countDown()
            } catch (throwable: Throwable) {
                throwable.printStackTrace()
            }
        }
        if (!count.await(1, TimeUnit.MINUTES)) throw Throwable("no Data!")
    }

    @Test
    @Throws(Throwable::class)
    fun testDetails() {
        for (id in 1..15) if (id != 6 && id != 7) modelDetail(id)
    }

    @Throws(Throwable::class)
    fun modelDetail(id: Int) {
        val count = CountDownLatch(1)
        val viewModelDetail = ViewModelDetail()
        viewModelDetail.getModelDetail(id).observeForever { modelDetail ->
            Assert.assertFalse(modelDetail == null)
            Log.d("DEBUG", modelDetail.toString())
            count.countDown()
        }
        if (!count.await(1, TimeUnit.MINUTES)) throw Throwable("no Data id detail $id")
    }

    @get:Rule
    var activityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    @Before
    @Throws(InterruptedException::class)
    fun startLoad() {
        Thread.sleep(1000)
    }

    @Test
    @Throws(InterruptedException::class)
    fun testRecyclerClick() {
        Espresso.onView(ViewMatchers.withId(R.id.reciclerView)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
                .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(13))
                .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(13, ViewActions.click()))
        Thread.sleep(1000)
        Espresso.onView(ViewMatchers.withText("Жили-Были")).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }
}