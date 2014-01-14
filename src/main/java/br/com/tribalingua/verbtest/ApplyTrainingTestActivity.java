package br.com.tribalingua.verbtest;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import br.com.tribalingua.verbtest.constants.ExtraConstants;
import br.com.tribalingua.verbtest.model.VerbContainer;
import br.com.tribalingua.verbtest.model.VerbTest;
import br.com.tribalingua.verbtest.repository.IApplyTestService;
import br.com.tribalingua.verbtest.repository.InMemoryApplyTestService;
import br.com.tribalingua.verbtest.repository.RepositoryFactory;

public class ApplyTrainingTestActivity extends FragmentActivity {

	private VerbTest verbTest;

	private VerbTestCollectionPagerAdapter adapter;

	ViewPager mViewPager;
	
	private int groupClassId;
	
	IApplyTestService service = (IApplyTestService)RepositoryFactory.get(InMemoryApplyTestService.KEY);

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.verb_test_activity);
		
		Bundle b = getIntent().getExtras();
		if (b != null) {
			groupClassId = b.getInt(ExtraConstants.EXTRA_GROUP_CLASS_ID);
		}

		verbTest = service.randomVerbTest();
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
		
		service.startNewTrainingTest();
		
	}
	
	public boolean onOptionsItemSelected(MenuItem item) {
		startActivity(ProfileActivity.class);
		return true;
	}
	
	private void startActivity(Class<? extends Activity> activityClass) {
		Intent	intent = new Intent();
        intent.setClassName(getPackageName(), activityClass.getName());
        startActivity(intent);
    }
	
	public VerbTest getVerbTest(){
		return this.verbTest;
	}
	
	public int getGroupTestId(){
		return this.groupClassId;
	}

	
	/**
	 * A {@link android.support.v4.app.FragmentStatePagerAdapter} that returns a
	 * fragment representing an object in the collection.
	 */
	public static class VerbTestCollectionPagerAdapter extends
			FragmentStatePagerAdapter {

		private final VerbTest verbTest;
		private int currentPosition = -1;

		public VerbTestCollectionPagerAdapter(FragmentManager fm, VerbTest verbTest) {
			super(fm);
			this.verbTest = verbTest;
		}

		@Override
		public Fragment getItem(int position) {
			Fragment fragment = null;
			this.currentPosition = position;
			
			if (verbTest.count() == position+1){
				fragment = new VertTestFinishFragment();
			}else{
				
				fragment = new VertTestObjectFragment();
			}
			
			Bundle args = new Bundle();
			
			args.putString(VertTestObjectFragment.ARG_OBJECT, transformToString(verbTest.getVerb(position))+";"+position);
			
			fragment.setArguments(args);
			
			return fragment;
		}

		@Override
		public int getCount() {
			return verbTest == null ? 0 : verbTest.count();
		}

		@Override
		public CharSequence getPageTitle(int position) {
			++position;
			return getCount() == position ? "" : Integer.toString(position);
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
		
		private int position = -1;
		private IApplyTestService service = (IApplyTestService)RepositoryFactory.get(InMemoryApplyTestService.KEY);

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			
			View rootView = inflater.inflate(R.layout.fragment_training_test_object, container, false);
			
			Bundle args = getArguments();
			
			String[] verbs = args.getString(ARG_OBJECT).split(";");
			
			((TextView) rootView.findViewById(R.id.txtInfinitive)).setText(verbs[0]);
			position = Integer.parseInt(verbs[3]);
			
			return rootView;
		}
		
		@Override
		public void onSaveInstanceState(Bundle outState) {
		    super.onSaveInstanceState(outState);
		    String infinitive = ((TextView)getView().findViewById(R.id.txtInfinitive)).getText().toString();
		    String pastSimple = ((EditText)getView().findViewById(R.id.edtTextPastSimple)).getText().toString();
		    String pastParticiple = ((EditText)getView().findViewById(R.id.edtTextPastParticiple)).getText().toString();
		    
		    Log.d("libras", position +" - "+ infinitive +" - "+ pastSimple +" - "+ pastParticiple);
		    
		    service.addAnswerToCurrentTest(position, infinitive, pastSimple, pastParticiple);
		}
	}
	
	/**
	 * A dummy fragment representing a section of the app, but that simply
	 * displays dummy text.
	 */
	public static class VertTestFinishFragment extends Fragment {

		public static final String ARG_OBJECT = "object";
		IApplyTestService service = (IApplyTestService)RepositoryFactory.get(InMemoryApplyTestService.KEY);
		
		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			
			View rootView = inflater.inflate(R.layout.verb_test_finish_fragment, container, false);
			Button btn = (Button)rootView.findViewById(R.id.finishTestBtn);
			btn.setOnClickListener(new OnClickListener(){

				public void onClick(View v) {
					service.finishTest();
					Intent intent = new Intent(getActivity(), StudentTrainingScoreActivity.class);
            		startActivity(intent);					
				}
				
			});
			
			return rootView;
		}
		
	}
	
	

}
