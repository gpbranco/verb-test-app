package br.com.tribalingua.verbtest;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import br.com.tribalingua.verbtest.ApplyTrainingTestActivity.VerbTestCollectionPagerAdapter;
import br.com.tribalingua.verbtest.model.FinalTraining;
import br.com.tribalingua.verbtest.model.VerbContainerAnswer;
import br.com.tribalingua.verbtest.repository.IApplyTestService;
import br.com.tribalingua.verbtest.repository.InMemoryApplyTestService;
import br.com.tribalingua.verbtest.repository.RepositoryFactory;

public class StudentTrainingScoreActivity extends FragmentActivity {
	
	private VerbTestCollectionPagerAdapter adapter;
	
	private IApplyTestService service = (IApplyTestService)RepositoryFactory.get(InMemoryApplyTestService.KEY);
	
	ViewPager mViewPager;
	
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_training_score_activity);
        
        TextView score = (TextView)findViewById(R.id.txtScore);
        FinalTraining finalTrainingResult = service.getFinalTrainingResult();
        score.setText("Score: "+String.valueOf(finalTrainingResult.getScore()));
        
        adapter = new VerbTestCollectionPagerAdapter(
				getSupportFragmentManager(), finalTrainingResult);

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
    
    private void startActivity(Class<? extends Activity> activityClass, Intent intent) {
        intent.setClassName(getPackageName(), activityClass.getName());
        startActivity(intent);
    }
    
    public static class VerbTestCollectionPagerAdapter extends
	FragmentStatePagerAdapter {
    	
		private final FinalTraining finalTraining;
		
		public VerbTestCollectionPagerAdapter(FragmentManager fm, FinalTraining finalTrainingResult) {
			super(fm);
			this.finalTraining = finalTrainingResult;
		}
		
		@Override
		public Fragment getItem(int position) {
			
			Fragment fragment = null;
			
			fragment = new VertTestObjectFragment();
			
			Bundle args = new Bundle();
			
			args.putString(VertTestObjectFragment.ARG_OBJECT, transformToString(finalTraining.getWrongAnswer(position)));
			
			fragment.setArguments(args);
			
			return fragment;
		}
		
		@Override
		public int getCount() {
			return this.finalTraining.getCount();
		}
		
		@Override
		public CharSequence getPageTitle(int position) {
			return this.finalTraining.getWrongAnswer(position).getInfinite();
		}
		
		public String transformToString(VerbContainerAnswer answer){
			return answer.isPastSimpleWrong()
					+";"+answer.getPastSimple()
					+";"+answer.isPastParticipleWrong()
					+";"+answer.getPastParticiple()
					+";"+answer.getInfinite()
					+";"+answer.getCorrectPastSimple()
					+";"+answer.getCorrectPastParticiple();
		}
		
	}
    
    public static class VertTestObjectFragment extends Fragment {
    	
    	public static final String ARG_OBJECT = "object";
    	public static final int WRONG_PAST_SIMPLE = 0;
    	public static final int ANSWER_PAST_SIMPLE = 1;
    	public static final int WRONG_PAST_PARTICIPLE = 2;
    	public static final int ANSWER_PAST_PARTICIPLE = 3;
    	public static final int INFINITIVE = 4;
    	public static final int PAST_SIMPLE = 5;
    	public static final int PAST_PARTICIPLE = 6;
    	
		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			
			Bundle args = getArguments();
			
			String[] answer = args.getString(ARG_OBJECT).split(";");
			
			View rootView = inflater.inflate(R.layout.fragment_score_training, container, false);
			
			TextView edtRightInfinitive = (TextView)rootView.findViewById(R.id.edtRightInfinitive);
			TextView edtRightPastSimple = (TextView)rootView.findViewById(R.id.edtRightPastSimple);
			TextView edtRightPastParticiple = (TextView)rootView.findViewById(R.id.edtRightPastParticiple);
			
			edtRightInfinitive.setText(getAnswerOrDefault(answer[INFINITIVE]));
			edtRightPastSimple.setText(getAnswerOrDefault(answer[PAST_SIMPLE]));
			edtRightPastParticiple.setText(getAnswerOrDefault(answer[PAST_PARTICIPLE]));
			
			edtRightInfinitive.setTextColor(Color.GREEN);
			edtRightPastSimple.setTextColor(Color.GREEN);
			edtRightPastParticiple.setTextColor(Color.GREEN);
			
			TextView edtInfinitive = (TextView)rootView.findViewById(R.id.edtWrongInfinitive);
			edtInfinitive.setText(answer[INFINITIVE]);
			edtInfinitive.setTextColor(Color.GREEN);

			TextView edtPastSimple = (TextView)rootView.findViewById(R.id.edtWrongPastSimple);
			
			if(isWrong(answer, WRONG_PAST_SIMPLE))
				edtPastSimple.setTextColor(Color.RED);
			else
				edtPastSimple.setTextColor(Color.GREEN);
			
			TextView edtPastParticiple = (TextView)rootView.findViewById(R.id.edtWrongPastParticiple);

			if(isWrong(answer, WRONG_PAST_PARTICIPLE))
				edtPastParticiple.setTextColor(Color.RED);
			else
				edtPastParticiple.setTextColor(Color.GREEN);
			
			
			edtPastSimple.setText(getAnswerOrDefault(answer[ANSWER_PAST_SIMPLE]));
			edtPastParticiple.setText(getAnswerOrDefault(answer[ANSWER_PAST_PARTICIPLE]));
			
			return rootView;
		}
		
		private boolean isWrong(String[] answer, int column){
			return Boolean.parseBoolean(answer[column]);
		}
		
		private String getAnswerOrDefault(String value){
			return "".equals(value) ? "Not Informed" : value;
		}
    }

}
