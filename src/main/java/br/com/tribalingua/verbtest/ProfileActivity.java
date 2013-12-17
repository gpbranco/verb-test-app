package br.com.tribalingua.verbtest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ProfileActivity extends Activity {

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.profile_activity);
		
		findViewById(R.id.teacherProfileButton).setOnClickListener(new View.OnClickListener() {
			
	        public void onClick(View view) {
	            startActivity(CategoriesListActivity.class);
	        }
	    });
	}
	
	private void startActivity(Class<? extends Activity> activityClass) {
        Intent intent = new Intent();
        intent.setClassName(getPackageName(), activityClass.getName());
        startActivity(intent);
    }
}
