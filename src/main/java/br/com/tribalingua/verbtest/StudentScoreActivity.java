package br.com.tribalingua.verbtest;

import java.util.ArrayList;
import java.util.List;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.NumberPicker;
import br.com.tribalingua.verbtest.adapter.StudentScoreImageAdapter;
import br.com.tribalingua.verbtest.model.StudentScore;

public class StudentScoreActivity extends FragmentActivity {
	
	AlertDialog dialog;
	
	ListView list;
	StudentScoreImageAdapter adapter;
	
	private int currentPosition;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.class_categories_activity);
 
        list=(ListView)findViewById(R.id.list);
 
        adapter = new StudentScoreImageAdapter(this, loadStudents());
        list.setAdapter(adapter);
 
        // Click event for single list row
        list.setOnItemClickListener(new OnItemClickListener() {
 
            public void onItemClick(AdapterView<?> parent, View view,
                    int position, long id) {
            	currentPosition = position;
            	StudentScoreActivity.this.currentPosition = position;
            	StudentScore student = (StudentScore)adapter.getItem(position);
            	test();
            }
        });
    }
    
    private List<StudentScore> loadStudents(){
    	List<StudentScore> studentScores = new ArrayList<StudentScore>();
    	StudentScore student = new StudentScore("Guilherme", "gui", 0, 1);
    	studentScores.add(student);
    	
    	return studentScores;
    }
	public void test(){
		
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		
		// Add the buttons
		builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
		           public void onClick(DialogInterface dialog, int id) {
		        	   lala();
		           }
		       });
		builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
		           public void onClick(DialogInterface dialog, int id) {
		               // User cancelled the dialog
		           }
		       });
		
		final String[] nums = new String[10];
		for(int i=0; i<nums.length; i++) {
		   nums[i] = Integer.toString(i+1);
		}
		
		NumberPicker numberPicker = new NumberPicker(this);//(NumberPicker)getLayoutInflater().inflate(R.layout.score_dialog, null);
		numberPicker.setId(R.id.np);
		numberPicker.setMaxValue(10);
		numberPicker.setMinValue(0);
		numberPicker.setWrapSelectorWheel(false);
		numberPicker.setDisplayedValues(nums);
		
		builder.setView(numberPicker);
		dialog = builder.create();
		dialog.show();
	}
	
	private void lala(){
		NumberPicker numberPicker = (NumberPicker)dialog.findViewById(R.id.np);
		int num = numberPicker.getValue();
		StudentScore score = (StudentScore) adapter.getItem(currentPosition);
		score.setScore(num+1);
		adapter.notifyDataSetChanged();
	}

}
