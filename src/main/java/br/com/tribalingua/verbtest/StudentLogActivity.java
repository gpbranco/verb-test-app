package br.com.tribalingua.verbtest;

import java.util.List;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import br.com.tribalingua.verbtest.adapter.StudentLogImageAdapter;
import br.com.tribalingua.verbtest.constants.ExtraConstants;
import br.com.tribalingua.verbtest.repository.IStudentScoreLogRepository;
import br.com.tribalingua.verbtest.repository.IStudentScoreLogRepository.StudentLog;
import br.com.tribalingua.verbtest.repository.RepositoryFactory;

public class StudentLogActivity extends FragmentActivity {
	
	AlertDialog dialog;
	
	ListView list;
	StudentLogImageAdapter adapter;
	
	private int currentPosition;
	private int classId;
	private IStudentScoreLogRepository repository = (IStudentScoreLogRepository)RepositoryFactory.get(IStudentScoreLogRepository.KEY);
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.class_categories_activity);
        
        ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
 
        list=(ListView)findViewById(R.id.list);
        
        Bundle b = getIntent().getExtras();
        if(b!=null)
        {
            classId = b.getInt(ExtraConstants.EXTRA_GROUP_CLASS_ID);
        }
 
        adapter = new StudentLogImageAdapter(this, loadStudents());
        list.setAdapter(adapter);
       
        // Click event for single list row
        list.setOnItemClickListener(new OnItemClickListener() {
 
            public void onItemClick(AdapterView<?> parent, View view,
                    int position, long id) {
            	currentPosition = position;
            }
        });
    }
    
    private List<StudentLog> loadStudents(){
    	return this.repository.loadAllStudentLogs(classId);
    }
}
