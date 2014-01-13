package br.com.tribalingua.verbtest;

import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.NumberPicker;
import br.com.tribalingua.verbtest.adapter.StudentScoreImageAdapter;
import br.com.tribalingua.verbtest.constants.ExtraConstants;
import br.com.tribalingua.verbtest.model.StudentScore;
import br.com.tribalingua.verbtest.repository.IStudentRepository;
import br.com.tribalingua.verbtest.repository.IStudentScoreLogRepository;
import br.com.tribalingua.verbtest.repository.RepositoryFactory;

public class StudentScoreActivity extends FragmentActivity {
        
        AlertDialog dialog;
        
        ListView list;
        StudentScoreImageAdapter adapter;
        
        private int currentPosition;
        private int classId;
        private IStudentRepository repository = (IStudentRepository)RepositoryFactory.get(IStudentRepository.KEY);
        private IStudentScoreLogRepository studentLogRepository = (IStudentScoreLogRepository)RepositoryFactory.get(IStudentScoreLogRepository.KEY);
        List<StudentScore> scores;
        
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_log_activity);
 
        list=(ListView)findViewById(R.id.list);
        
        Bundle b = getIntent().getExtras();
        if(b!=null)
        {
            classId = b.getInt(ExtraConstants.EXTRA_GROUP_CLASS_ID);
        }
 
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
        
        Button btn =  (Button)findViewById(R.id.doneBtn);
        btn.setOnClickListener(new OnClickListener() {
                        
                        public void onClick(View v) {
                                studentLogRepository.addLog(scores, classId);
                                Intent intent = new Intent();
                        intent.putExtra(ExtraConstants.EXTRA_GROUP_CLASS_ID, classId);
                        startActivity(StudentLogActivity.class, intent);
                        }
                });
    }
    
    private void startActivity(Class<? extends Activity> activityClass, Intent intent) {
        intent.setClassName(getPackageName(), activityClass.getName());
        startActivity(intent);
    }
    
    private List<StudentScore> loadStudents(){
             scores = this.repository.loadAllStudentsScore(classId);
             return scores;
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
                numberPicker.setMaxValue(9);
                numberPicker.setMinValue(0);
                numberPicker.setValue(9);
                numberPicker.setWrapSelectorWheel(true);
                numberPicker.setDisplayedValues(nums);
                
                enableNumberPickerManualEditing(numberPicker, false);
                
                builder.setView(numberPicker);
                dialog = builder.create();
                dialog.show();
        }
        
        public static void enableNumberPickerManualEditing(NumberPicker numPicker,
                boolean enable) {
            int childCount = numPicker.getChildCount();

            for (int i = 0; i < childCount; i++) {
                View childView = numPicker.getChildAt(i);

                if (childView instanceof EditText) {
                    EditText et = (EditText) childView;
                    et.setFocusable(enable);
                    return;
                }
            }
        }
        
        private void lala(){
                NumberPicker numberPicker = (NumberPicker)dialog.findViewById(R.id.np);
                int num = numberPicker.getValue();
                StudentScore score = (StudentScore) adapter.getItem(currentPosition);
                score.setScore(num+1);
                adapter.notifyDataSetChanged();
        }

}