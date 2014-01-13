package br.com.tribalingua.verbtest;

import br.com.tribalingua.verbtest.constants.ExtraConstants;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class ProfileActivity extends Activity {

	private String[] mNamesArray;
	private Spinner mAccountTypesSpinner;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.accounts_activity);
		
		 mNamesArray = getAccountNames();
	     mAccountTypesSpinner = initializeSpinner(
	                R.id.accounts_tester_account_types_spinner, mNamesArray);
		
		findViewById(R.id.ok_button).setOnClickListener(new View.OnClickListener() {
			
					public void onClick(View view) {
						int accountIndex = mAccountTypesSpinner.getSelectedItemPosition();
						if (accountIndex < 0) {
							//Toast.makeText(this, "No account available. Please add an account to the phone first.", Toast.LENGTH_SHORT);
							return;
						}
						String email = mNamesArray[accountIndex];
						if(isTeacher(email)){
							startActivity(CategoriesListActivity.class, null);
						}else{
							startStudentModule(email);
						}
					}
	    });
	}
	
	private boolean isTeacher(String email){
		return email.contains("jon");
	}
	
	private void startStudentModule(String email){
		Intent intent = new Intent();
		intent.putExtra(ExtraConstants.EXTRA_STUDENT_ID, email);
		startActivity(StudentModulelActivity.class, intent);
	}
	
	private String[] getAccountNames() {
        //mAccountManager = AccountManager.get(this);
        //Account[] accounts = mAccountManager.getAccountsByType(GoogleAuthUtil.GOOGLE_ACCOUNT_TYPE);
        String[] names = {"jon.holloway@bravisoftware.com", "guilherme.branco@bravisoftware.com"};
        for (int i = 0; i < names.length; i++) {
            names[i] = names[i];
        }
        return names;
    }
	
	private Spinner initializeSpinner(int id, String[] values) {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, values);
        Spinner spinner = (Spinner) findViewById(id);
        spinner.setAdapter(adapter);
        return spinner;
    }
	
	private void startActivity(Class<? extends Activity> activityClass, Intent intent) {
		if(intent == null)
			intent = new Intent();
        intent.setClassName(getPackageName(), activityClass.getName());
        startActivity(intent);
    }
}
