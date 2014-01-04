package br.com.tribalingua.verbtest.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.tribalingua.verbtest.R;
import br.com.tribalingua.verbtest.model.StudentScore;

public class InMemoryStudentScoreLogRepository implements
		IStudentScoreLogRepository {
	
	Map<Integer, List<StudentLog>> classes = new HashMap<Integer, List<StudentLog>>();
	
	public InMemoryStudentScoreLogRepository(){
	
		List<StudentLog> turkeywolf = new ArrayList<IStudentScoreLogRepository.StudentLog>();
		List<StudentLog> monkeysInTshirts = new ArrayList<IStudentScoreLogRepository.StudentLog>();
		
		turkeywolf.add(new StudentLog(0, 0, "Mari", "RESOURCES:"+R.drawable.avatar_mari));
		turkeywolf.add(new StudentLog(0, 0, "Guilherme", "RESOURCES:"+R.drawable.avatar_guigo));
		turkeywolf.add(new StudentLog(0, 0, "Daniel", "RESOURCES:"+R.drawable.avatar_daniel));
		turkeywolf.add(new StudentLog(0, 0, "Priscila", "RESOURCES:"+R.drawable.avatar_pri));
		turkeywolf.add(new StudentLog(0, 0, "William", "RESOURCES:"+R.drawable.avatar_will));
		
		monkeysInTshirts.add(new StudentLog(0, 0, "Gabi", "RESOURCES:"+R.drawable.avatar_gabi));
		monkeysInTshirts.add(new StudentLog(0, 0, "Matheus", "RESOURCES:"+R.drawable.avatar_mateus));
		monkeysInTshirts.add(new StudentLog(0, 0, "Cris", "RESOURCES:"+R.drawable.avatar_cris));
		
		classes.put(1, turkeywolf);
		classes.put(2, monkeysInTshirts);
	}

	public void addLog(List<StudentScore> scores, int groupId) {
		for (StudentScore studentScore : scores) {
			List<StudentLog> logs = classes.get(groupId);
			for (StudentLog log : logs) {
				if(log.isSame(studentScore)){
					log.add(studentScore);
					break;
				}
			}
		}
	}

	public List<StudentLog> loadAllStudentLogs(int groupClassId) {
		return this.classes.get(groupClassId);
	}

}
