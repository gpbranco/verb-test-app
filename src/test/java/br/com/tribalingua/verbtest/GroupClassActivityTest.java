package br.com.tribalingua.verbtest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.robolectric.Robolectric.shadowOf;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.shadows.ShadowActivity;
import org.robolectric.shadows.ShadowListView;

import br.com.tribalingua.verbtest.constants.ExtraConstants;

import android.content.Intent;
import android.widget.ListView;

@RunWith(RobolectricTestRunner.class)
public class GroupClassActivityTest {

	private ClassListActivity activity;
	private ListView lv;

	@Before
	public void setUp() {
		activity = Robolectric.buildActivity(ClassListActivity.class)
				.create().visible().get();
	}

	@Test
	public void shouldProperItemClickOpenApplyTestActivityPassingGroupClassId() throws Exception {
		int position = 0;
		ShadowActivity shadowActivity = Robolectric.shadowOf(activity);
		lv = (ListView) shadowActivity.findViewById(R.id.list);

		ShadowListView listView = shadowOf(lv);

		listView.performItemClick(position);
		Intent startedIntent = shadowActivity.getNextStartedActivity();
		Intent expectedIntent = new Intent(activity, ApplyTestActivity.class);
		expectedIntent.putExtra(ExtraConstants.EXTRA_GROUP_CLASS_ID, 2);

		assertEquals(expectedIntent, startedIntent);
		
	}

}
