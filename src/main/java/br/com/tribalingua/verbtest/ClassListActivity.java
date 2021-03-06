package br.com.tribalingua.verbtest;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import br.com.tribalingua.verbtest.adapter.ClassDefaultImageAdapter;
import br.com.tribalingua.verbtest.constants.ExtraConstants;
import br.com.tribalingua.verbtest.model.GroupClass;
import br.com.tribalingua.verbtest.repository.IGroupClassRepository;
import br.com.tribalingua.verbtest.repository.RepositoryFactory;

public class ClassListActivity extends Activity {

	private ListView list;
    private ClassDefaultImageAdapter adapter;
    private IGroupClassRepository repository = (IGroupClassRepository)RepositoryFactory.get(IGroupClassRepository.KEY);
    private int categoryId;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.class_categories_activity);
 
        list=(ListView)findViewById(R.id.list);
 
        Bundle b = getIntent().getExtras();
        if(b!=null)
        {
        	categoryId = b.getInt(ExtraConstants.EXTRA_CATEGORY_ID);
        }
        
        adapter= new ClassDefaultImageAdapter(this, loadClasses());
        list.setAdapter(adapter);
 
        // Click event for single list row
        list.setOnItemClickListener(new OnItemClickListener() {
 
            public void onItemClick(AdapterView<?> parent, View view,
                    int position, long id) {
            		GroupClass groupClass = (GroupClass)adapter.getItem(position);
            		Intent intent = new Intent();
            		intent.putExtra(ExtraConstants.EXTRA_GROUP_CLASS_ID, groupClass.getId());
            		startActivity(ApplyTestActivity.class, intent);
            }
        });
    }
    
    private void startActivity(Class<? extends Activity> activityClass, Intent intent) {
        intent.setClassName(getPackageName(), activityClass.getName());
        startActivity(intent);
    }
    
    private List<GroupClass> loadClasses(){
    	return this.repository.loadAllGroupClass(categoryId);
    }
   	
}
