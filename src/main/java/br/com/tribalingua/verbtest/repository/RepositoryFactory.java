package br.com.tribalingua.verbtest.repository;

import java.util.HashMap;
import java.util.Map;

public class RepositoryFactory {
	
	private static Map<String, Object> repositories;
	private static boolean loaded = false;
	
	public static Object get(String key){
		if(!loaded){
			loadRepositories();
		}
		return repositories.get(key);
	}
	
	private static void loadRepositories() {

		repositories = new HashMap<String, Object>();
		repositories.put(ICategoryrepository.KEY, new InMemoryCategoryRepository());
		repositories.put(IStudentRepository.KEY, new InMemoryStudentRepository());
		repositories.put(IGroupClassRepository.KEY, new InMemoryGroupClassRepository());
		repositories.put(IStudentScoreLogRepository.KEY, new InMemoryStudentScoreLogRepository());
		repositories.put(InMemoryApplyTestService.KEY, new InMemoryApplyTestService());
		loaded = true;

	}

}
