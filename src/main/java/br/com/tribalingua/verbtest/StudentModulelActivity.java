package br.com.tribalingua.verbtest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import br.com.tribalingua.verbtest.constants.ExtraConstants;

public class StudentModulelActivity extends Activity {
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_model);
        TextView txtName = (TextView)findViewById(R.id.lblGreeting);
        
        Bundle b = getIntent().getExtras();
        String email = "";
        if(b!=null)
        {
        	email = b.getString(ExtraConstants.EXTRA_STUDENT_ID);
        }
        
        txtName.setText(getName(email));
        
        Button btn = (Button)findViewById(R.id.btnStartTest);
        btn.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				Intent intent = new Intent();
        		intent.putExtra(ExtraConstants.EXTRA_GROUP_CLASS_ID, 1);
        		startActivity(ApplyTrainingTestActivity.class, intent);
			}
		});
	}
	
	private void startActivity(Class<? extends Activity> activityClass, Intent intent) {
        intent.setClassName(getPackageName(), activityClass.getName());
        startActivity(intent);
    }
	
	private String getName(String email){
		if(email.contains("gui")){
			return "Hi, Guilherme!";
		}
		return "Welcome!";
	}

}
