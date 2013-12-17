package br.com.tribalingua.verbtest;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import br.com.tribalingua.verbtest.adapter.DefaultImageAdapter;
import br.com.tribalingua.verbtest.constants.ExtraConstants;
import br.com.tribalingua.verbtest.model.Category;

public class CategoriesListActivity extends Activity {

	
	ListView list;
    DefaultImageAdapter adapter;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.class_categories_activity);
 
        list=(ListView)findViewById(R.id.list);
 
        adapter= new DefaultImageAdapter(this, loadCategories());
        list.setAdapter(adapter);
 
        // Click event for single list row
        list.setOnItemClickListener(new OnItemClickListener() {
 
            public void onItemClick(AdapterView<?> parent, View view,
                    int position, long id) {
            		Category category = (Category)adapter.getItem(position);
            		Intent intent = new Intent();
            		intent.putExtra(ExtraConstants.EXTRA_CATEGORY_ID, category.getId());
            		startActivity(ClassListActivity.class, intent);
            }
        });
    }
    
    private void startActivity(Class<? extends Activity> activityClass, Intent intent) {
        intent.setClassName(getPackageName(), activityClass.getName());
        startActivity(intent);
    }
    
    private List<Category> loadCategories(){
    	List<Category> categories = new ArrayList<Category>();
    	
    	categories.add(new Category(2, "Test", "http://test.com", "description"));
    	
    	return categories;
    }
}
