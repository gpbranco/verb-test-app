package br.com.tribalingua.verbtest.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.tribalingua.verbtest.R;
import br.com.tribalingua.verbtest.model.StudentScore;

public class InMemoryStudentRepository implements IStudentRepository{
	
	
	Map<Integer, List<StudentScore>> classes = new HashMap<Integer, List<StudentScore>>();
	
	public InMemoryStudentRepository(){
		List<StudentScore> scores = new ArrayList<StudentScore>();
		scores.add(new StudentScore("Mari", "RESOURCES:"+R.drawable.avatar_mari, 0, 1));
		scores.add(new StudentScore("Guilherme", "RESOURCES:"+R.drawable.avatar_guigo, 0, 1));
		scores.add(new StudentScore("Daniel", "RESOURCES:"+R.drawable.avatar_daniel, 0, 1));
		scores.add(new StudentScore("Priscila", "RESOURCES:"+R.drawable.avatar_pri, 0, 1));
		scores.add(new StudentScore("William", "RESOURCES:"+R.drawable.avatar_will, 0, 1));
		classes.put(1, scores);
		
		List<StudentScore> scores2 = new ArrayList<StudentScore>();
		scores2.add(new StudentScore("Gabi", "RESOURCES:"+R.drawable.avatar_gabi, 0, 2));
		scores2.add(new StudentScore("Matheus", "RESOURCES:"+R.drawable.avatar_mateus, 0, 2));
		scores2.add(new StudentScore("Cris", "RESOURCES:"+R.drawable.avatar_cris, 0, 2));
		classes.put(2, scores2);
		
	}

	public List<StudentScore> loadAllStudentsScore(int classId) {
		return this.classes.get(classId);
	}

}
