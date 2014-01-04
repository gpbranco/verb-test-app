package br.com.tribalingua.verbtest.repository;

import java.util.ArrayList;
import java.util.List;

import br.com.tribalingua.verbtest.R;
import br.com.tribalingua.verbtest.model.Category;

public class InMemoryCategoryRepository implements ICategoryrepository{
	
	private List<Category> categories;
	
	public InMemoryCategoryRepository(){
		categories = new ArrayList<Category>();
		categories.add(new Category(2, "Red Book", "RESOURCES:"+R.drawable.red_book, "Level 2"));
		categories.add(new Category(1, "Blue Book", "RESOURCES:"+R.drawable.blue_book, "Level 3"));
	}

	public List<Category> loadAllCategories() {
    	return categories;
	}

}
