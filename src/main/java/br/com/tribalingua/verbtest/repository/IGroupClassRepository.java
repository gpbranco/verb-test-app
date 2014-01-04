package br.com.tribalingua.verbtest.repository;

import java.util.List;

import br.com.tribalingua.verbtest.model.GroupClass;

public interface IGroupClassRepository {
	
	public static String KEY = "GroupClass";
	
	List<GroupClass> loadAllGroupClass(int categoryId);

}
