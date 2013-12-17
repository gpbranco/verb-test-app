package br.com.tribalingua.verbtest;

import java.util.ArrayList;
import java.util.List;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import br.com.tribalingua.verbtest.constants.ExtraConstants;
import br.com.tribalingua.verbtest.model.VerbContainer;
import br.com.tribalingua.verbtest.model.VerbTest;

public class ApplyTestActivity extends FragmentActivity {

	private VerbTest verbTest;

	private VerbTestCollectionPagerAdapter adapter;

	ViewPager mViewPager;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.verb_test_activity);
		
		loadVerbTest();
		
		adapter = new VerbTestCollectionPagerAdapter(
				getSupportFragmentManager(), verbTest);

		// Set up action bar.
		final ActionBar actionBar = getActionBar();

		// Specify that the Home button should show an "Up" caret, indicating
		// that touching the
		// button will take the user one step up in the application's hierarchy.
		actionBar.setDisplayHomeAsUpEnabled(true);

		// Set up the ViewPager, attaching the adapter.
		mViewPager = (ViewPager) findViewById(R.id.pager);
		mViewPager.setAdapter(adapter);
	}
	
	public VerbTest getVerbTest(){
		return this.verbTest;
	}

	private void loadVerbTest() {
		List<VerbContainer> verbs = new ArrayList<VerbContainer>();
		
		for (int i = 0; i < 3; i++) {
			VerbContainer container = new VerbContainer("I - "+i, "PS - "+i, "PP - "+i);
			verbs.add(container);
		}
		
		VerbContainer dummy = new VerbContainer("Dummy", "", "");
		verbs.add(dummy);
		
		verbTest = new VerbTest(1);
		verbTest.addVerbs(verbs);
	}

	/**
	 * A {@link android.support.v4.app.FragmentStatePagerAdapter} that returns a
	 * fragment representing an object in the collection.
	 */
	public static class VerbTestCollectionPagerAdapter extends
			FragmentStatePagerAdapter {

		private final VerbTest verbTest;

		public VerbTestCollectionPagerAdapter(FragmentManager fm, VerbTest verbTest) {
			super(fm);
			this.verbTest = verbTest;
		}

		@Override
		public Fragment getItem(int position) {
			Fragment fragment = null;
			
			if (verbTest.count() == position+1){
				fragment = new VertTestFinishFragment();
			}else{
				
				fragment = new VertTestObjectFragment();
			}
			
			Bundle args = new Bundle();
			
			args.putString(VertTestObjectFragment.ARG_OBJECT, transformToString(verbTest.getVerb(position)));
			
			fragment.setArguments(args);
			
			return fragment;
		}

		@Override
		public int getCount() {
			return verbTest == null ? 0 : verbTest.count();
		}

		@Override
		public CharSequence getPageTitle(int position) {
			return Integer.toString(++position);
		}
		
		private String transformToString(VerbContainer container){
			return container.getInfinite() + ";" + container.getPastSimple() + ";" + container.getPastParticiple();
		}
	}
	

	/**
	 * A dummy fragment representing a section of the app, but that simply
	 * displays dummy text.
	 */
	public static class VertTestObjectFragment extends Fragment {

		public static final String ARG_OBJECT = "object";

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			
			View rootView = inflater.inflate(R.layout.fragment_collection_object, container, false);
			
			Bundle args = getArguments();
			
			String[] verbs = args.getString(ARG_OBJECT).split(";");
			
			((TextView) rootView.findViewById(android.R.id.text1)).setText(verbs[0]);
			
			((TextView) rootView.findViewById(android.R.id.text2)).setText(verbs[1]);
			
			((TextView) rootView.findViewById(R.id.text3)).setText(verbs[2]);
			
			return rootView;
		}
	}
	
	/**
	 * A dummy fragment representing a section of the app, but that simply
	 * displays dummy text.
	 */
	public static class VertTestFinishFragment extends Fragment {

		public static final String ARG_OBJECT = "object";

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			
			View rootView = inflater.inflate(R.layout.verb_test_finish_fragment, container, false);
			Button btn = (Button)rootView.findViewById(R.id.finishTestBtn);
			btn.setOnClickListener(new OnClickListener(){

				public void onClick(View v) {
					Intent intent = new Intent(getActivity(), StudentScoreActivity.class);
            		intent.putExtra(ExtraConstants.EXTRA_VERB_TEST_ID, ((ApplyTestActivity)getActivity()).getVerbTest().getId());
            		startActivity(intent);					
				}
				
			});
			
			return rootView;
		}
		
	}
	
	

}
