package br.com.tribalingua.verbtest.repository;

import java.util.List;

import br.com.tribalingua.verbtest.model.Category;

public interface ICategoryrepository {
	
	public static String KEY = "Category";
	
	List<Category> loadAllCategories();

}
