package com.android.vote.votesystem;


import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class VoteTopicTest {

    @Rule
    public ActivityTestRule<TopTopicListActivity> mActivityTestRule = new ActivityTestRule<>(TopTopicListActivity.class);

    @Test
    public void voteTopicTest() {
        ViewInteraction floatingActionButton = onView(
                allOf(withId(R.id.fab), isDisplayed()));
        floatingActionButton.perform(click());

        ViewInteraction appCompatEditText = onView(
                allOf(withId(R.id.topic),
                        withParent(allOf(withId(R.id.activity_post_topic),
                                withParent(withId(android.R.id.content)))),
                        isDisplayed()));
        appCompatEditText.perform(replaceText("Test01"), closeSoftKeyboard());

        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.post), withText("POST"),
                        withParent(allOf(withId(R.id.activity_post_topic),
                                withParent(withId(android.R.id.content)))),
                        isDisplayed()));
        appCompatButton.perform(click());

        ViewInteraction button = onView(
                allOf(withId(R.id.up), withText("UP"),
                        withParent(childAtPosition(
                                withId(R.id.list),
                                0)),
                        isDisplayed()));
        button.perform(click());

        ViewInteraction textView = onView(
                allOf(withId(R.id.upNum), withText("1"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.list),
                                        0),
                                3),
                        isDisplayed()));
        textView.check(matches(withText("1")));

        ViewInteraction button2 = onView(
                allOf(withId(R.id.down), withText("DOWN"),
                        withParent(childAtPosition(
                                withId(R.id.list),
                                0)),
                        isDisplayed()));
        button2.perform(click());

        ViewInteraction textView2 = onView(
                allOf(withId(R.id.downNum), withText("1"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.list),
                                        0),
                                5),
                        isDisplayed()));
        textView2.check(matches(withText("1")));

    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
