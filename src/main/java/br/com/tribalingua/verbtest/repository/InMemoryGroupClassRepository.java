package br.com.tribalingua.verbtest.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.tribalingua.verbtest.R;
import br.com.tribalingua.verbtest.model.GroupClass;

public class InMemoryGroupClassRepository implements IGroupClassRepository{

	Map<Integer, List<GroupClass>> categories = new HashMap<Integer, List<GroupClass>>();
	
	public InMemoryGroupClassRepository(){
		List<GroupClass> classes = new ArrayList<GroupClass>();	
		classes.add(new GroupClass(1, "Turkeywolf", "RESOURCES:"+R.drawable.turkeywolf));
		categories.put(1, classes);
		
		List<GroupClass> classes2 = new ArrayList<GroupClass>();	
		classes2.add(new GroupClass(2, "Monkeys in T-shirts", "RESOURCES:"+R.drawable.monkeys_in_tshirts));
		categories.put(2, classes2);
	}
	
	public List<GroupClass> loadAllGroupClass(int categoryId) {
		return categories.get(categoryId);
	}

}
