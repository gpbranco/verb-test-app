package br.com.tribalingua.verbtest;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.robolectric.Robolectric.shadowOf;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.shadows.ShadowActivity;
import org.robolectric.shadows.ShadowIntent;

import android.content.Intent;
import android.widget.Button;

@RunWith(RobolectricTestRunner.class)
public class ProfileActivityTest {
	
	private ProfileActivity activity;
	private Button teacherProfileButton;
	
	@Before
	public void setUp(){
		activity = Robolectric.buildActivity(ProfileActivity.class).create().visible().get();
		teacherProfileButton = (Button)activity.findViewById(R.id.teacherProfileButton);
	}
	
	@Test
	public void shouldHaveAButtonThatSaysTeacher(){
		assertThat((String) teacherProfileButton.getText(), equalTo("Teacher"));
	}

	@Test
	public void pressingTeacherButtonShouldStartClassCategoriesListActivity(){
		teacherProfileButton.performClick();
		
		ShadowActivity shadowActivity = shadowOf(activity);
		Intent startedIntent = shadowActivity.getNextStartedActivity();
		ShadowIntent shadowIntent = shadowOf(startedIntent);
		assertThat(shadowIntent.getComponent().getClassName(), equalTo(CategoriesListActivity.class.getName()));
	}
}
