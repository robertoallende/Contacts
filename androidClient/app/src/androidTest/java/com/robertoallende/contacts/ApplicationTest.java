package com.robertoallende.contacts;

import android.app.Application;
import android.os.SystemClock;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.ApplicationTestCase;

import com.robertoallende.contacts.view.ContactActivity;
import com.robertoallende.contacts.view.ContactListActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.TimeUnit;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.pressImeActionButton;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.CoreMatchers.anything;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasEntry;
import static org.hamcrest.core.IsEqual.equalTo;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
@RunWith(AndroidJUnit4.class)
public class ApplicationTest extends ApplicationTestCase<Application> {
    public ApplicationTest() {
        super(Application.class);
    }


    @Rule
    public ActivityTestRule<ContactListActivity> mActivityRule = new ActivityTestRule<>(
            ContactListActivity.class);

    @Test
    public void addAndViewUser() {

        onView(withId(R.id.fab)).perform(click());

        onView(withId(R.id.add_first_name)).perform(typeText("Michael"));
        onView(withId(R.id.add_first_name)).perform(pressImeActionButton());

        onView(withId(R.id.add_last_name)).perform(typeText("Jordan"));
        onView(withId(R.id.add_last_name)).perform(pressImeActionButton());

        onView(withId(R.id.add_city)).perform(typeText("Chicago"));
        onView(withId(R.id.add_city)).perform(pressImeActionButton());

        onView(withId(R.id.add_state)).perform(typeText("IL"));
        onView(withId(R.id.add_state)).perform(pressImeActionButton());

        onView(withId(R.id.add_mail)).perform(typeText("michael@jordan.com"));
        onView(withId(R.id.add_mail)).perform(pressImeActionButton());

        onView(withId(R.id.create_user_button)).perform(click());

        // Just in case animations are enabled on device.
        SystemClock.sleep(TimeUnit.SECONDS.toMillis(2));

        onData(anything())
                .inAdapterView(withId(R.id.contact_list)).atPosition(0)
                .perform(click());

        SystemClock.sleep(TimeUnit.SECONDS.toMillis(2));
    }


}