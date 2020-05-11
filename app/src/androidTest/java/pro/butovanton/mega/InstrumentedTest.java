package pro.butovanton.mega;

import android.content.Context;
import android.util.Log;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.Observer;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.ViewAction;
import androidx.test.espresso.ViewAssertion;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class InstrumentedTest {
    @Test
    public void useAppContext() {
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("pro.butovanton.mega", appContext.getPackageName());
    }

    @Rule
    public InstantTaskExecutorRule instantExecutorRule = new InstantTaskExecutorRule();

    @Test
    public void ViewModel() throws Throwable {
        CountDownLatch count = new CountDownLatch(1);
        ViewModelModel viewModelModel = new ViewModelModel();
        viewModelModel.getModel().observeForever(new Observer<List<MModel>>() {
            @Override
            public void onChanged(List<MModel> model) {
                Log.d("DEBUG", "model size: " + model.toString());
                try {
                    count.countDown();
                } catch (Throwable throwable) {
                    throwable.printStackTrace();
                 }
            }
        });
        if (!count.await(1, TimeUnit.MINUTES)) throw new Throwable("no Data!");
    }

    @Test
    public void testDetails() throws Throwable {
        for (int id = 1; id <= 15; id++)
           if ( id != 6 && id != 7) modelDetail(id);
    }

    public void modelDetail(int id) throws Throwable {
        CountDownLatch count = new CountDownLatch(1);
        ViewModelDetail viewModelDetail = new ViewModelDetail();
        viewModelDetail.getModelDetail(id).observeForever(new Observer<ModelDetail>() {
            @Override
            public void onChanged(ModelDetail modelDetail) {
                assertFalse(modelDetail == null);
                Log.d("DEBUG", modelDetail.toString());
                count.countDown();
                }
        });
       if (!count.await(1, TimeUnit.MINUTES)) throw new Throwable("no Data id detail " + id);
   }

    @Rule public ActivityScenarioRule<MainActivity> activityScenarioRule =
            new ActivityScenarioRule<MainActivity>(MainActivity.class);

    @Test
    public void testRecicler() {
        Espresso.onView(withId(R.id.reciclerView)).check(ViewAssertions.matches(isDisplayed()));
        Espresso.onView(withId(R.id.reciclerView)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
   }

    @Test
    public void testRecyclerClick() {
        Espresso.onView(withId(R.id.reciclerView)).perform(RecyclerViewActions.actionOnItemAtPosition(10,click()));
    }
}
