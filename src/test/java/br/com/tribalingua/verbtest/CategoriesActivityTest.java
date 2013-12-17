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
import org.robolectric.shadows.ShadowFrameLayout;
import org.robolectric.shadows.ShadowListView;

import br.com.tribalingua.verbtest.constants.ExtraConstants;

import android.content.Intent;
import android.widget.ListView;

@RunWith(RobolectricTestRunner.class)
public class CategoriesActivityTest {

	private CategoriesListActivity activity;
	private ListView lv;

	@Before
	public void setUp() {
		activity = Robolectric.buildActivity(CategoriesListActivity.class)
				.create().visible().get();
	}

	@Test
	public void shouldProperItemClickOpenClassListActivityPassingCategoryId() throws Exception {
		int position = 0;
		ShadowActivity shadowActivity = Robolectric.shadowOf(activity);
		lv = (ListView) shadowActivity.findViewById(R.id.list);
		System.out.print("list: " + lv.getChildCount() + "\n");
		ShadowListView listView = shadowOf(lv);

		listView.performItemClick(position);
		Intent startedIntent = shadowActivity.getNextStartedActivity();
		Intent expectedIntent = new Intent(activity, ClassListActivity.class);
		expectedIntent.putExtra(ExtraConstants.EXTRA_CATEGORY_ID, 2);

		assertEquals(expectedIntent, startedIntent);
	}

}
